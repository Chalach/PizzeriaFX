package pizzeria;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
    TODO:
    - GUI
    - User kann sein eigenes Restaurant erstellen
    - Der Inhaber kann Mitarbeiter einstellen und feuern
    - Pizza - Karte muss noch erstellt werden
    - evlt. wird die Übung "PizzabringDienst" noch implementiert
    - Ofen Levelsystem erstellen (mit mehr Kapital kann der Inhaber den Ofen verbessern
      und mehrere Pizzen gleichzeitig zubereiten...)
    - Entscheiden was mit dem Kundenstamm passieren soll
    - Kunde kann zum Mittarbeiter werden?
    - Mitarbeiter kann zum Inhaber werden?
    - Pizzaiolo ist für die Zubereitung in der pizzeria zuständig
    - Finanzen können vom Kelner und vom Inhaber verwaltet werden
    - Simulation von Kunden erstellen, Kunden kommen und gehen (Simulation -> Threads)
*/


public class StartPizzeria extends Application {
    @FXML private Pizzeria myRestaurante;
    @FXML private Kundenstamm kundenstamm;
    @FXML private TextField namePizzeria;
    @FXML private TextField nameInhaber;
    @FXML private TextField nachnameInhaber;
    @FXML private TextField alterInhaber;
    @FXML private CheckBox kundenStammErstellen;
    @FXML private CheckBox mitArbeiterEinstellen;
    @FXML private CheckBox ofenErstellen;
    @FXML private AnchorPane pizzeriaCreate;
    private String pizzeriaName;
    private String inhaberName;
    private String inhaberNachname;
    private int inhaberAlter;

    private void createPizzeria(Stage primaryStage) throws IOException {
        pizzeriaName = namePizzeria.getText();
        inhaberName = nameInhaber.getText();
        inhaberNachname = nachnameInhaber.getText();
        inhaberAlter = Integer.parseInt(alterInhaber.getText());

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
        myRestaurante = new Pizzeria(pizzeriaName, new Inhaber(inhaberName, inhaberNachname, inhaberAlter));
        System.out.println(myRestaurante);

        if(kundenStammErstellen.isSelected()){
            //createKundenstamm();
        }
        if(mitArbeiterEinstellen.isSelected()){
            Stage mitarbeiterStage = new Stage();
//                Parent root2 = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
//                mitarbeiterStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
//                mitarbeiterStage.setTitle("Mitarbeiter");
//                mitarbeiterStage.setScene(new Scene(root2, 640, 360));
//                mitarbeiterStage.show();
//                //mitarbeiterEinstellen();
            mitarbeiterStage.close();
        }
        if(ofenErstellen.isSelected()){
            Ofen ofen = new Ofen();
        }
        primaryStage.close();
        Stage secondaryStage = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getResource("Pizzeria.fxml"));
        secondaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
        secondaryStage.setTitle(pizzeriaName);
        secondaryStage.setScene(new Scene(root3, 1280, 720));
        secondaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Fenster laden
        Parent root = FXMLLoader.load(getClass().getResource("CreatePizzeria.fxml"));
        primaryStage.getIcons().add(new Image("file:icons\\pizzeria.png"));
        primaryStage.setTitle("Erstelle deine eigene Pizzeria");
        primaryStage.setScene(new Scene(root, 500, 340));
        primaryStage.show();
        createPizzeria(primaryStage);
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        // Test Main
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