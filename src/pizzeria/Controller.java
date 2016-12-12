package pizzeria;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller extends Application implements Initializable {
    @FXML private Pizzeria myRestaurante;
    @FXML private Kundenstamm kundenstamm;
    @FXML private ArrayList<Mensch> mitarbeiter = new ArrayList<>();
    @FXML private TextField namePizzeria;
    @FXML private TextField nameInhaber;
    @FXML private TextField nachnameInhaber;
    @FXML private TextField alterInhaber;
    @FXML private CheckBox kundenStammErstellen;
    @FXML private CheckBox mitArbeiterEinstellen;
    @FXML private CheckBox ofenErstellen;
    @FXML private static Stage mainStage;
    @FXML private static ImageView picture1;
    @FXML private static ImageView picture2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Programm has been started");
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Programm has been started!");
//
//        alert.showAndWait();
    }

    @FXML
    public void createPizzeria() throws IOException {
        String pizzeriaName = namePizzeria.getText();
        String inhaberName = nameInhaber.getText();
        String inhaberNachname = nachnameInhaber.getText();
        int inhaberAlter;
        try {
             inhaberAlter = Integer.parseInt(alterInhaber.getText());
        }catch (NumberFormatException ex){
            System.err.println("Das war keine Nummer!");
            inhaberAlter = 0;
        }

        if(namePizzeria.getText().equals("") && nameInhaber.getText().equals("") && nachnameInhaber.getText().equals("") && alterInhaber.getText().equals("")){
            System.err.println("Keine eingabe wurde getätigt!");
        }
        else if(namePizzeria.getText().equals("")){
            System.err.println("Name der Pizzeria wurde nicht angegeben!");
        }
        else if(nameInhaber.getText().equals("")){
            System.err.println("Name des Inhabers wurde nicht angegeben!");
        }
        else if(nachnameInhaber.getText().equals("")){
            System.err.println("Nachname des Inhabers wurde nicht angegeben!");
        }
        else if(alterInhaber.getText().equals("")){
            System.err.println("Alter des Inhabers wurde nicht angegeben!");
        }
        else{
            myRestaurante = new Pizzeria(pizzeriaName, new Inhaber(inhaberName, inhaberNachname, inhaberAlter));
            System.out.println(myRestaurante);

            if(kundenStammErstellen.isSelected()){
                createKundenstamm();

            }
            if(mitArbeiterEinstellen.isSelected()){
                Stage mitarbeiterStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
                mitarbeiterStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
                mitarbeiterStage.setTitle("Mitarbeiter");
                mitarbeiterStage.setScene(new Scene(root, 640, 360));
                mitarbeiterStage.show();
                mitarbeiterEinstellen();
                mitarbeiterStage.close();
            }
            if(ofenErstellen.isSelected()){
                Ofen ofen = new Ofen();
            }

            Stage secondaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
            secondaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
            secondaryStage.setTitle(pizzeriaName);
            secondaryStage.setScene(new Scene(root, 1280, 720));
            secondaryStage.show();

            mainStage.close();
        }
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
        Scanner sc = new Scanner(System.in);
        ArrayList<String> namenListe = kundenstamm.getNamenListe();
        int input;

        /*
            TODO:
             - Namen der Angestellten muss noch random erstellt werden -> OK
             - Bei jedem neu angestellten Mitarbeiter wird ein gewisser Betrag abgezogen -> OK
             - Überprüfung, wenn der Kontostand leer ist. Danach kann kein neuer Mitarbeiter eingestellt werden
        */

        System.out.println("Welchen Mitarbeiter möchten sie einstellen?");

        System.out.println("Stelle nun deine Mitarbeiter ein: ");
        System.out.println("Zur Auswahl stehen:");
        System.out.println("1) Kellner/in");
        System.out.println("2) Pizzaiolo");

        do {
            input = sc.nextInt();
            if (input == 1 && kostenMitarbeiter(-200)) {
                Kellner kellner = new Kellner(namenListe.get(randomGenerator.nextInt(70)));
                mitarbeiter.add(kellner);
                System.out.println("Kellner eingestellt");
            } else if (input == 2 && kostenMitarbeiter(-300)) {
                Pizzaiolo pizzaiolo = new Pizzaiolo(namenListe.get(randomGenerator.nextInt(70)));
                mitarbeiter.add(pizzaiolo);
                System.out.println("Pizzaiolo eingestellt");
            } else {
                System.out.println("Falsche Eingabe");
            }
            System.out.println(myRestaurante.getKapital());
            System.out.println(mitarbeiter);
        } while (input != 0);
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
