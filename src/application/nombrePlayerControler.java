package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class nombrePlayerControler {

    @FXML
    private Text diff;

    @FXML
    private ImageView help;

    @FXML
    private Button multi, rejoint;

    @FXML
    private Button seul;



    @FXML
    public void initialize() {
        
        // Ajouter des effets de survol aux boutons
        addHoverEffect(multi);
        addHoverEffect(seul);
        addHoverEffect(rejoint);
    }

 // Méthode pour ajouter l'effet de survol à un bouton
 private void addHoverEffect(Button button) {
    // Créer une transition d'échelle
    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
    scaleTransition.setToX(1.1);
    scaleTransition.setToY(1.1);
    scaleTransition.play();

    // Définir l'action lorsqu'on survole le bouton
    button.setOnMouseEntered(event -> {
        button.setTextFill(Color.GREEN); // Changer la couleur du texte en vert
        button.setStyle("-fx-background-color: green;"); // Changer la couleur de fond en vert clair
        scaleTransition.setByZ(-20); // Changer la profondeur du zoom
        scaleTransition.playFromStart(); // Démarrer l'animation de zoom
    });

    // Définir l'action lorsque la souris quitte le bouton
    button.setOnMouseExited(event -> {
        button.setTextFill(Color.BLACK); // Rétablir la couleur du texte à sa valeur initiale
        button.setStyle("-fx-background-color: gray"); // Rétablir la couleur de fond initiale
        scaleTransition.stop(); // Arrêter l'animation de zoom
        button.setScaleX(1); // Rétablir l'échelle horizontale
        button.setScaleY(1); // Rétablir l'échelle verticale
    });
}

    @FXML
    void onJouer(MouseEvent event) {

    }
    
    @FXML
    void onMulti(ActionEvent event) throws IOException {
        URL url = new File("src/vus/playerWith.fxml").toURI().toURL();

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

    
    // si on veux jouer classiquement on passe a la page de défficulté 
    @FXML
    void onSeul(ActionEvent event) throws IOException {
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

    
    @FXML
    void onRetour(ActionEvent event) throws IOException {
        URL url = new File("src/vus/choice.fxml").toURI().toURL();

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
    
    @FXML
    void onRejoint(ActionEvent event) throws IOException {
        URL url = new File("src/vus/connexion.fxml").toURI().toURL();

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
