package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loubiControler {


    @FXML
    private TextField A;

    @FXML
    private TextField D;

    @FXML
    private TextField L;

    @FXML
    private TextField O;


    @FXML
    private Text pA, pB ,message, message1;

    @FXML
    private Button retour, start;
    private static String nom1 , nom2;

    @FXML
    private ImageView settings;

    @FXML
    private TextField input;

    static String messagepA ;
    static boolean partire = false;

    // Propriété observable pour le message
    private static final StringProperty messageTextProperty = new SimpleStringProperty();
    public loubiControler(){}

    @FXML
    private void initialize() throws IOException {
        start.setStyle("-fx-background-color: red;");
        pA.setText(nom1);
        pB.setText(nom2);
        settings.setVisible(false);
        L.setVisible(false);
        O.setVisible(false);
        A.setVisible(false);
        D.setVisible(false);
        
        new Thread(() -> {
            while (true) {
                if (Server.clientConnectServer.size() >= 1) {
                    String nouveauNom1 = Server.clientConnectServer.get(0);
                    settings.setVisible(true);
                    Platform.runLater(() -> pA.setText(nouveauNom1));
                    try {
                        Thread.sleep(1000); // Attendre 1 seconde
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (Server.clientConnectServer.size() >= 2) {       
                        start.setStyle("-fx-background-color: green;");

                        Platform.runLater(() -> pB.setText(Server.clientConnectServer.get(1)));
                    }
                }


                // Attendre un petit moment avant de vérifier à nouveau
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}}).start();


                if(pA.getText().isEmpty()){
                    settings.setVisible(false);
                    pA.setText(getpAnom());
                }
                if(pB.getText().isEmpty()){
                    pB.setText(getpBnom());
                }



                messageTextProperty.addListener((observable, oldValue, newValue) -> {
                    // Mettre à jour l'interface utilisateur avec le nouveau message
                    if(newValue.equals("preDeux")){
                        partire = true;
                        message.setText("- c'est partie");

                    }if(!message.getText().isEmpty()){
                        message1.setText("- " +message.getText());
                    }
                        message.setText("- "+newValue);
                        
                    
                    
                });



                
    }
    
    public static void setpAnom(String message){
        messagepA = message; 
    }
    public static String getpAnom(){
        return messagepA;
    }

    private static String pBnom;
    public static String getpBnom() {
        return pBnom;
    }
    public static void setpBnom(String pBnom) {
        loubiControler.pBnom = pBnom;
    }


    // Méthode pour obtenir la valeur actuelle du message
    public static String getMessageText() {
        return messageTextProperty.get();
    }
    // Méthode pour définir la valeur du message
    public static void setMessageText(String messageText) {
        // Extraire le message en découpant la chaîne après ":"
        String message = messageText.substring(messageText.indexOf("-") + 1).trim();
        // Définir le message extrait
        messageTextProperty.set(message);
    }

    // Méthode pour accéder à la propriété observable
    public static StringProperty messageTextProperty() {
        return messageTextProperty;
    }

@FXML
void onSend(ActionEvent event) {
    Client.startSendMessageLoop(input.getText());   
}
@FXML
void onStart(ActionEvent event) throws IOException {
    Client.forStart = true;
    L.setVisible(true);
    O.setVisible(true);
    A.setVisible(true);
    D.setVisible(true);
    Client.startPlayButton = true;
    ClientHandler.send = true;
    Client.startSendMessageLoop(ClientHandler.getNomPlayer() + "/Start");

    // Démarrez une tâche pour gérer le changement de couleur
    Task<Void> colorTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (!partire) {
                // Mettez à jour l'interface utilisateur dans le thread de l'application JavaFX
                Platform.runLater(() -> {
                    L.setStyle("-fx-background-color: orange;");
                    O.setStyle("-fx-background-color: black;");
                    A.setStyle("-fx-background-color: black;");
                    D.setStyle("-fx-background-color: black;");
      
                });

                try {
                    Thread.sleep(1000); // Attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    L.setStyle("-fx-background-color: green;");
                    O.setStyle("-fx-background-color: orange;");
                    A.setStyle("-fx-background-color: black;");
                    D.setStyle("-fx-background-color: black;");
  
                });

                try {
                    Thread.sleep(1000); // Attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    L.setStyle("-fx-background-color: black;");
                    O.setStyle("-fx-background-color: green;");
                    A.setStyle("-fx-background-color: orange;");
                    D.setStyle("-fx-background-color: black;");
   
                });

                try {
                    Thread.sleep(1000); // Attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    L.setStyle("-fx-background-color: black;");
                    O.setStyle("-fx-background-color: black;");
                    A.setStyle("-fx-background-color: green;");
                    D.setStyle("-fx-background-color: orange;");
  
                });

                try {
                    Thread.sleep(1000); // Attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    L.setStyle("-fx-background-color: orange;");
                    O.setStyle("-fx-background-color: black;");
                    A.setStyle("-fx-background-color: black;");
                    D.setStyle("-fx-background-color: green;");
  
                });

                try {
                    Thread.sleep(1000); // Attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    };

    // Démarrez la tâche dans un nouveau thread
    Thread colorThread = new Thread(colorTask);
    colorThread.start();

    // Ouvrir la nouvelle fenêtre une fois que partire est true
    colorTask.setOnSucceeded(eventHandler -> {
        try {
            loadNombrePlayerPage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}

public void loadNombrePlayerPage(ActionEvent event) throws IOException {
    // Ouvrir la nouvelle fenêtre
    URL url = new File("src/vus/window5.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(url);
    Stage stage = new Stage();
    stage.setTitle("Wordle");
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    // Activer la possibilité de redimensionner la fenêtre.
    stage.setResizable(true);
    // Cacher la fenêtre parente avant d'afficher la nouvelle fenêtre
    ((Node) (event.getSource())).getScene().getWindow().hide();
}
    public static void loadLoubiPage(ActionEvent event, String username) throws IOException {

        FXMLLoader loader = new FXMLLoader(loubiControler.class.getResource("/vus/loubi.fxml"));
        Parent root = loader.load();
        loubiControler controllerInstance = loader.getController();
        // Définir l'instance du contrôleur dans le PageManager
        PageManager.setLoubiController(controllerInstance);
        Stage stage = new Stage();
        stage.setTitle("loubi");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Cacher la fenêtre parente avant d'afficher la nouvelle fenêtre
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();

    }


    static  boolean  settingsChoice ;

    public static boolean gettingsChoice(){
        return settingsChoice;
    }
    public static void settingsChoice(boolean settings){
        settings = settingsChoice;
    }




    @FXML
    void onSettings(MouseEvent event) throws IOException {
        // si le le nom de client == le deuxieme cas de tableau alors je peux pas clicker 
        
        if(gettingsChoice() == true ){
            URL url = new File("src/vus/choiceGame.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
    
            stage.setTitle("comment jouer ?");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // Enable the ability to resize the window.
            stage.setResizable(true);
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
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }



}
