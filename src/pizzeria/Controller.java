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

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller extends Application implements Initializable {
    @FXML private Pizzeria myRestaurante;
    @FXML private Kundenstamm kundenstamm;
    @FXML private ArrayList<Mensch> mitarbeiter = new ArrayList<>();
    private ArrayList<String> errorList = new ArrayList<>();
    @FXML private static Stage mainStage;
    @FXML private TextField namePizzeria;
    @FXML private TextField nameInhaber;
    @FXML private TextField nachnameInhaber;
    @FXML private TextField alterInhaber;
    @FXML private CheckBox kundenStammErstellen;
    @FXML private CheckBox mitArbeiterEinstellen;
    @FXML private CheckBox ofenErstellen;
    @FXML private TextField anzahlKellner;
    @FXML private CheckBox pizzaioloSelected;
    @FXML private Button einstellenMitarbeiter;
    @FXML private ImageView picture1;
    @FXML private ImageView picture2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("Programm has been started");
    }

    private void errorDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    public void createPizzeria() throws IOException {
        // TODO: Fehlerbehegung, sollte der User falsche Daten eingeben
        boolean error = true;
        String pizzeriaName = "";
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
            showKundenstamm();
        }
        if(mitArbeiterEinstellen.isSelected()){
            mainStage.close();
            Stage mitarbeiterStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("CreateMitarbeiter.fxml"));
            mitarbeiterStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
            mitarbeiterStage.setTitle("Mitarbeiter");
            mitarbeiterStage.setScene(new Scene(root, 500, 340));
            mitarbeiterStage.showAndWait();
        }
        if(ofenErstellen.isSelected()){
            Ofen ofen = new Ofen();
        }

        Stage secondaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
        secondaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
        secondaryStage.setTitle(pizzeriaName);
        secondaryStage.setScene(new Scene(root, 1280, 720));
        secondaryStage.showAndWait();
    }

    @FXML
    public void createKundenstamm() throws IOException {
        kundenstamm = new Kundenstamm();
    }

    @FXML
    public void showKundenstamm(){
        for (Kunde i : kundenstamm.getKundenListe()) {
            System.out.print(i);
        }
    }

    private boolean kostenMitarbeiter(double kapital) {
        if(myRestaurante.getKapital() + kapital < 0){
            System.err.println("Sie können keine neuen Mitarbeiter einstellen!\nIhnen fehlt das Geld!");
            return false;
        }
        else{
            myRestaurante.addKapital(kapital);
        }
        return true;
    }

    @FXML
    public void mitarbeiterEinstellen() throws IOException {
        Random randomGenerator = new Random();
        int input = Integer.parseInt(anzahlKellner.getText());
        ArrayList<String> namenListe = kundenstamm.getNamenListe();
        showKundenstamm();
        /*
            TODO: Fehlerbehegung, sollte der User falsche Daten eingeben
             - Namen der Angestellten muss noch random erstellt werden -> OK
             - Bei jedem neu angestellten Mitarbeiter wird ein gewisser Betrag abgezogen -> OK
             - Überprüfung, wenn der Kontostand leer ist. Danach kann kein neuer Mitarbeiter eingestellt werden
        */

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
            // TODO: FehFehlerbehegung, sollte der User falsche Daten eingeben
        }
    }

    @FXML
    public void mitarbeiterFeuern(Mensch mitarbeiter){
        this.mitarbeiter.remove(mitarbeiter);
    }

    @FXML
    public ArrayList<Mensch> getMitarbeiter(){
        return mitarbeiter;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CreatePizzeria.fxml"));
//        try{
//            Image image = new Image("file:Y:\\Informatik\\Projekt\\PizzeriaFX\\Pizzeria.png");
//            System.err.println("no error");
//            picture1.setImage(image);
//            //picture2.setImage(image);
//        }catch (Exception ex){
//            System.err.println("error");
//        }
        primaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
        primaryStage.setTitle("Erstelle deine eigene Pizzeria");
        primaryStage.setScene(new Scene(root, 500, 340));
        mainStage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
//        Pizzen pizzen = new Pizzen();
//        pizzen.writePizza();
//        pizzen.readPizzas();
        launch(args);
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
    }

}
