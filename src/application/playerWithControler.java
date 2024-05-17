package application;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class playerWithControler {
	

    @FXML
    private TextField nom;

    @FXML
    private Text diff ,serveur;

    @FXML
    private ImageView help;

    @FXML
    private Button player;

    @FXML
    private Button robot;



    private static String Word;
    @FXML
    void onJouer(MouseEvent event) {

    }

    @FXML
    private void initialize() {

        serveur.setVisible(false);
        // Ajoutez un écouteur à votre champ de texte pour détecter les changements
        nom.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifiez si le nouveau texte n'est pas vide
            if (!newValue.isEmpty()) {
                // Si le nouveau texte n'est pas vide, changez la couleur du fond du bouton en vert
                player.setStyle("-fx-background-color: green;");
                robot.setStyle("-fx-background-color: green;");
            } else {
                // Si le nouveau texte est vide, laissez la couleur du fond du bouton par défaut
                player.setStyle("-fx-background-color: red;");
                robot.setStyle("-fx-background-color: red;"); // Réinitialisez le style pour utiliser celui par défaut
            }
        });
    }  

    private static boolean serverRunning = false;

    private static boolean moiServer = false;
    public static void setMoiServer(boolean moiServer) {
        playerWithControler.moiServer = moiServer;
    }

    public static boolean getMoiServer() {
        System.out.println("moi le server player with " + moiServer);
        return moiServer;
    }
    
    

    @FXML
    void onPlayer(ActionEvent event) throws IOException {
        if (!nom.getText().isEmpty()) {

            
            setMoiServer(true);

            String username = nom.getText();

            loubiControler.settingsChoice(true);
            ClientHandler.clientConnectedListe.add(username);
            ClientHandler.setNomPlayer(username);
            if (!serverRunning) {
                Thread serverThread = new Thread(() -> {
                    try {
                        Server.main(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                serverThread.start();
                serverRunning = true;
            } else {
                System.out.println("Server is already running.");
            }
            
         // Créer et démarrer le client dans un thread séparé
            Thread clientThread = new Thread(() -> {
            	String serverIP = "localhost"; // Ou obtenez l'adresse IP du serveur de votre interface graphique

                new Client(serverIP, Server.port,username);
                
            });
            clientThread.start();

            
            serveur.setVisible(true);
                // Afficher le texte "Lancement du serveur" pendant 5 secondes
                serveur.setText("Lancement du serveur...");
                serveur.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(5), evt -> {
                    // Charger l'autre page une fois le délai écoulé
                    try {
                        loubiControler.loadLoubiPage(event ,username);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
                timeline.play();
                }
    }




    @FXML
    void onRobot(ActionEvent event) throws IOException {
        if (!nom.getText().isEmpty()) {

            URL url = new File("src/vus/choicegame.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();

            stage.setTitle("Wordle");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // Enable the ability to resize the window.
            stage.setResizable(true);
            // Hide the player name input window before showing the main game window.
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    
    @FXML
    void onRetour(ActionEvent event) throws IOException {
    	
        URL url = new File("src/vus/nombrePlayer.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        stage.setTitle("Wordle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // Enable the ability to resize the window.
        stage.setResizable(true);
        // Hide the player name input window before showing the main game window.
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}
