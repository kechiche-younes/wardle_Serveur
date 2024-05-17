package application;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

import javafx.animation.TranslateTransition;



public class choiceControler {

	@FXML
	private ImageView imageView, imageView1, imageView2;

	@FXML
	private Text titleText;

	@FXML
	void initialize() {
	    // Initialiser l'événement de survol pour chaque image
	    setHoverEffect(imageView, "wordle");
	    setHoverEffect(imageView1, "tic tac toe");
	    setHoverEffect(imageView2, "autre");
	}

	private void setHoverEffect(ImageView imageView, String gameName) {
	    // Configurer l'événement de survol pour chaque image
	    imageView.setOnMouseEntered(event -> zoomIn(event, gameName));
	    imageView.setOnMouseExited(event -> zoomOut(event, "Les jeux"));
	}

	private void zoomIn(MouseEvent event, String gameName) {
	    // Animation pour zoomer légèrement lors du survol
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), (ImageView) event.getSource());
	    scaleTransition.setToX(1.1);
	    scaleTransition.setToY(1.1);
	    scaleTransition.play();

	    // Changer le texte du titre
	    titleText.setText(gameName);

	    // Placer le titre juste au-dessus de l'image
	    titleText.setLayoutX(event.getX() + ((ImageView) event.getSource()).getLayoutX() - titleText.getBoundsInLocal().getWidth() / 2);
	    titleText.setLayoutY(((ImageView) event.getSource()).getLayoutY() - 20); // Ajustez cette valeur en fonction de votre mise en page
	}

	private void zoomOut(MouseEvent event, String defaultTitle) {
	    // Arrêter les transitions actives
	    titleText.getTransforms().clear();

	    // Animation pour revenir à la taille normale lorsque le curseur quitte l'image
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), (ImageView) event.getSource());
	    scaleTransition.setToX(1.0);
	    scaleTransition.setToY(1.0);
	    scaleTransition.play();

	    // Transition de déplacement pour le texte du titre (retour à la position initiale)
	    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), titleText);
	    translateTransition.setToX(0);
	    translateTransition.setToY(0);
	    translateTransition.play();

	    // Rétablir le texte du titre par défaut
	    titleText.setText(defaultTitle);

	    // Réinitialiser la position du titre par rapport à l'image
	    titleText.setLayoutX(imageView2.getLayoutX() /2.5);
	    titleText.setLayoutY(imageView.getLayoutY() - 150); // Ajustez cette valeur en fonction de votre mise en page
	}

    @FXML
    void onAutre(ActionEvent event) {

    }

    @FXML
    void onWordle(MouseEvent event) throws IOException {


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
