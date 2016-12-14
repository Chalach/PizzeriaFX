package pizzeria;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller extends Application implements Initializable, Serializable {
    @FXML private Pizzeria myRestaurante;
    @FXML private Kundenstamm kundenstamm;
    @FXML private ArrayList<Mensch> mitarbeiter = new ArrayList<>();
    @FXML private ArrayList<String> namenListe = new ArrayList<>();
    @FXML private static ArrayList<Pizza> pizzen = new ArrayList<>();
    @FXML private static ArrayList<String> belag = new ArrayList<>();
    @FXML private String pizzeriaName;
    @FXML private static Stage mainStage;
    @FXML private static Stage mitArbeiterStage;
    @FXML private static Stage pizzeriaMainStage;
    @FXML private TextField namePizzeria;
    @FXML private TextField nameInhaber;
    @FXML private TextField nachnameInhaber;
    @FXML private TextField alterInhaber;
    @FXML private CheckBox kundenStammErstellen;
    @FXML private CheckBox mitArbeiterEinstellen;
    @FXML private CheckBox ofenErstellen;
    @FXML private TextField anzahlKellner;
    @FXML private CheckBox pizzaioloSelected;
    @FXML private ImageView picture1 = new ImageView();
    @FXML private ImageView picture2 = new ImageView();
    @FXML private ListView<String> listView;

     /*
        TODO
        Ablauf:
            - pizzeria kann vom Inhaber erstellt werden -> OK
            - Zu Karrierebeginn muss er Mitarbeiter einstellen -> OK
            - Danach entschließt er sich die pizzeria zu öffnen:
                - evtl. Auswahl, welche Pizzen er verkaufen möchte?
            - Simulation beginnt:
                - Kunden kommen und gehen nun zur pizzeria
                    - werden vom Kellner/in bediehnt
                    - Bargeld wird vom Kellner/in und Inhaber verwaltet
                - Inhaber kann neue Mitarbeiter einstellen
                - Inhaber kann Mitarbeiter feuern
                - Inhaber kann die Gerätschaften (Ofen) upgraden lassen
    */


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("Window opened");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // TODO: Bilder einfügen

        // Serialize, um die schon vorhanden Pizzas ins Programm zu laden
        //writeOut();
        readIn();

        for (Pizza i : pizzen) {
            System.out.println(i);
        }

        // Start - Fenster
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("CreatePizzeria.fxml"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        primaryStage.getIcons().add(new Image("file:icons\\Pizzeria.png"));
        primaryStage.setTitle("Erstelle deine eigene Pizzeria");
        primaryStage.setScene(new Scene(root, 500, 340));

        // Bild einfügen
        try{
            Image image = new Image("file:icons\\Pizzeria.png");
            picture1.setImage(image);
            picture2.setImage(image);
        }catch (Exception ex){
            System.err.println("error");
        }

        // Fenster instanzieren, um es zu einem später Zeitpunkt zu schließen
        mainStage = primaryStage;
        primaryStage.show();
    }

    // Für die ganzen Fehler, die der User machen kann, zuständig
    private void errorDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void errorDialog(ArrayList<Mensch> msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(msg.toString());
        alert.showAndWait();
    }

    // Liest Namen aus einer Textdatei aus
    private void generateNamenListe() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("namen\\namen.txt"))) {
            for (String c; (c = br.readLine()) != null; ) {
                namenListe.add(c);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Konnte die Datei nicht finden!");
        }
    }


    /*
                            <---------->
                              CONTROLS
                            <---------->
                                 ---
                          <-------------->
                            Vorprogramme
                          <-------------->
                                 ---
                          <--------------->
                            Hauptprogramm
                          <--------------->
                                 ---
                          <--------------->
                              Serialize
                          <---------------->
     */


    /*
        <-------------->
          Vorprogramme
        <-------------->
     */

    @FXML
    private void createPizzeria() throws IOException {
        // TODO: Fehlerbehegung, sollte der User falsche Daten eingeben
        boolean error = true;
        pizzeriaName = "";
        String inhaberName = "";
        String inhaberNachname = "";
        int inhaberAlter = 0;

        while (error){
            error = false;
            pizzeriaName = namePizzeria.getText();
            if(namePizzeria.getText().equals("")){
                errorDialog("Name der Pizzeria wurde nicht angegeben!");
                error = true;
            }

            inhaberName = nameInhaber.getText();
            if(nameInhaber.getText().equals("")){
                errorDialog("Name des Inhabers wurde nicht angegeben!");
                error = true;
            }

            inhaberNachname = nachnameInhaber.getText();
            if(nachnameInhaber.getText().equals("")){
                errorDialog("Nachname des Inhabers wurde nicht angegeben!");
                error = true;
            }
            try {
                inhaberAlter = Integer.parseInt(alterInhaber.getText());
            }catch (NumberFormatException ex){
                errorDialog("Das war keine Nummer!");
            }
            if(namePizzeria.getText().equals("") && nameInhaber.getText().equals("") && nachnameInhaber.getText().equals("") && alterInhaber.getText().equals("")){
                errorDialog("Keine eingabe wurde getätigt!");
                error = true;
            }
        }

        myRestaurante = new Pizzeria(pizzeriaName, new Inhaber(inhaberName, inhaberNachname, inhaberAlter));
        System.out.println(myRestaurante);

        if(kundenStammErstellen.isSelected()){
            createKundenstamm();
            //showKundenstamm();
        }
        if(mitArbeiterEinstellen.isSelected()){
            mainStage.close();
            Stage mitarbeiterStage = new Stage();
            mitArbeiterStage = mitarbeiterStage;
            Parent root = FXMLLoader.load(getClass().getResource("CreateMitarbeiter.fxml"));
            mitarbeiterStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
            mitarbeiterStage.setTitle("Mitarbeiter");
            mitarbeiterStage.setScene(new Scene(root, 500, 340));
            mitarbeiterStage.showAndWait();
        }
        if(ofenErstellen.isSelected()){
            Ofen ofen = new Ofen();
        }
        workPizzeria();
    }

    @FXML
    private void createKundenstamm() throws IOException {
        kundenstamm = new Kundenstamm();
    }

    @FXML
    private void showKundenstamm(){
        for (Kunde i : kundenstamm.getKundenListe()) {
            System.out.print(i);
        }
    }

    private boolean kostenMitarbeiter(double kapital) {
//        if(myRestaurante.getKapital() + kapital < 0){
//            System.err.println("Sie können keine neuen Mitarbeiter einstellen!\nIhnen fehlt das Geld!");
//            return false;
//        }
//        else{
//            myRestaurante.addKapital(kapital);
//        }
        myRestaurante.addKapital(kapital);
        return true;
    }

    @FXML
    private void mitarbeiterEinstellen() throws IOException, NotEnoughMoneyException {
        /*
            TODO: Fehlerbehegung, sollte der User falsche Daten eingeben
             - Namen der Angestellten muss noch random erstellt werden -> OK
             - Bei jedem neu angestellten Mitarbeiter wird ein gewisser Betrag abgezogen -> OK
             - Überprüfung, wenn der Kontostand leer ist. Danach kann kein neuer Mitarbeiter eingestellt werden
        */

        int input = Integer.parseInt(anzahlKellner.getText());
        System.out.println("Kellner: " + input);
        System.out.println("Pizzaiolo: " + 1);
        Random randomGenerator = new Random();
        generateNamenListe();

        // Kosten macht Probleme!
        for (int i = 0; i < input; i++) {
//            if (kostenMitarbeiter(-300)){
                Kellner kellner = new Kellner(namenListe.get(randomGenerator.nextInt(70)));
                mitarbeiter.add(kellner);
//            }
//            else{
//                throw new NotEnoughMoneyException();
//            }
        }

        if(pizzaioloSelected.isSelected()){
            if(kostenMitarbeiter(-500)){
                Pizzaiolo pizzaiolo = new Pizzaiolo(namenListe.get(randomGenerator.nextInt(70)));
                mitarbeiter.add(pizzaiolo);
            }
            else{
                //throw new NotEnoughMoneyException();
            }
        }
        else{
            errorDialog("Sind sie sicher, dass sie keinen Pizzaiolo angestellt haben wollen?");
        }
        mitArbeiterStage.close();
    }

    @FXML
    private void mitarbeiterFeuern(Mensch mitarbeiter){
        this.mitarbeiter.remove(mitarbeiter);
    }

    private ArrayList<Mensch> getMitarbeiter(){
        //errorDialog(mitarbeiter);
        //System.out.println("Mitarbeiter");

        return mitarbeiter;
    }

    /*
        <--------------->
          Hauptprogramm
        <--------------->
     */

    // Menu - Bar

    @FXML
    private void workPizzeria() throws IOException{
        //Simulation simulation = new Simulation();
        //System.out.println(myRestaurante.getKapital());
        Stage secondaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
        secondaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
        secondaryStage.setTitle(pizzeriaName);
        secondaryStage.setScene(new Scene(root, 1280, 720));
        getMitarbeiter();
        for (Mensch i : mitarbeiter) {
            System.out.println("Test");
            System.out.println(i);
        }
        pizzeriaMainStage = secondaryStage;
        listView = new ListView<>();
        listView.getItems().addAll("Hans", "Peter", "Wurst");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        secondaryStage.show();
    }

    @FXML
    public void closeWindow(){
        pizzeriaMainStage.close();
    }

    @FXML
    public void helpWindow(){
        errorDialog("Sie haben, Probleme?\nLösen Sie sie selbst!");
    }
    @FXML
    public void mitarbeiterEinstellenNachtraeglich() throws IOException, NotEnoughMoneyException {
        /*
            TODO: Fehlerbehegung, sollte der User falsche Daten eingeben
             - Namen der Angestellten muss noch random erstellt werden -> OK
             - Bei jedem neu angestellten Mitarbeiter wird ein gewisser Betrag abgezogen -> OK
             - Überprüfung, wenn der Kontostand leer ist. Danach kann kein neuer Mitarbeiter eingestellt werden
        */

        int input = Integer.parseInt(anzahlKellner.getText());
        System.out.println("Kellner: " + input);
        System.out.println("Pizzaiolo: " + 1);
        Random randomGenerator = new Random();
        generateNamenListe();

        for (int i = 0; i < input; i++) {
            Kellner kellner = new Kellner(namenListe.get(randomGenerator.nextInt(70)));
            mitarbeiter.add(kellner);
        }

        if(pizzaioloSelected.isSelected()){
            Pizzaiolo pizzaiolo = new Pizzaiolo(namenListe.get(randomGenerator.nextInt(70)));
            mitarbeiter.add(pizzaiolo);
        }
        else{
            errorDialog("Sind sie sicher, dass sie keinen Pizzaiolo angestellt haben wollen?");
        }
        mitArbeiterStage.close();
    }


    /*
        <----------->
          Serialize
        <----------->
     */

    private static void readIn() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pizzen\\pizzen.ser"));
        ArrayList<Pizza> pizzern = (ArrayList<Pizza>) ois.readObject();
        System.out.println(pizzern);
        ois.close();
    }

    @FXML
    public static void writeOut() throws Exception {
        addPizza();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pizzen\\pizzen.ser"));
        oos.writeObject(pizzen);
        oos.close();
    }

    private static void addPizza() throws IOException, ClassNotFoundException {
        String o;
        String p;
        boolean test = true;
        boolean test2 = true;

        while(test2){
            o = JOptionPane.showInputDialog("Name Pizza:");
            if(o.equals("exit")){
                test2 = false;
            }
            while (test){
                p = JOptionPane.showInputDialog("Belager Pizza:");
                if (p.equals("exit")){
                    test = false;
                }
                else{
                    belag.add(p);
                }
            }
            Pizza pizza = new Pizza(o, belag);
            if(!o.equals("exit")){
                pizzen.add(pizza);
            }
            System.out.println(pizza);
            belag = new ArrayList<>();
            test = true;
        }
    }
}
