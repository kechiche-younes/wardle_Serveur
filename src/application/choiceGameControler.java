

/**
 * @author: Kechiche Younes 
 * 
 * Controller class to handle user interactions in the choice game window.
 * Manages the selection of difficulty levels and open the corresponding window for each choice 
 * Uses JavaFX annotations for linking with FXML components and handling events.
 */


package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
public class choiceGameControler {



    @FXML
    private RadioButton Facile,Difficile,Moyen,impossible;
    @FXML
    private ToggleGroup difficultyToggleGroup ;

    @FXML
    private Button start;


    @FXML
    private TextField nombre;


    @FXML
    private Text text ,diff;
    @FXML
    private ImageView help;
    Text diffText = diff;
    
 // Initialize method called when the controller is loaded
	public void initialize() {
		 Moyen.setSelected(true); // Set the default selection to 'Moyen'to avoid accessing to the game without choosing a difficulty
		 setHoverEffect(help);
		 diffText = diff;
	        // Initialisation de diffText ici
	        // Ajoutez les gestionnaires d'événements pour le survol pour chaque RadioButton
	        setHoverEffectForDifficultyButton(Facile, 18.0 );
	        setHoverEffectForDifficultyButton(Moyen, 23.0);
	        setHoverEffectForDifficultyButton(Difficile, 26.0);
	        setHoverEffectForDifficultyButton(impossible, 30.0);
	        // Ajoutez l'effet de zoom lors du survol
	        setZoomEffectOnButton(start);
	    }

	    private void setHoverEffectForDifficultyButton(RadioButton button, double fontSize) {
	        button.setOnMouseEntered(event -> {
	            diffText.setFont(Font.font("System Bold", fontSize));
	        });
	        button.setOnMouseExited(event -> {
	            // Remettez la taille par défaut lorsqu'on quitte le bouton
	            diffText.setFont(Font.font("System Bold", 18.0)); // Ajustez la taille par défaut selon vos besoins
	        });
	    }
    @FXML
    
   /*
    * This method Handles the action when the 'Start' button is clicked.
    *  checks the selected difficulty level and loads the corresponding game scene.
    *
    * @param event The ActionEvent triggered by the 'Start' button click.
    * @throws IOException If an error occurs while loading the FXML file.
    */
    void onClickStart(ActionEvent event) throws IOException {
		if(nombre != null){
			System.out.println(nombre.getText());
		}
    	// Check which difficulty level is selected and load the corresponding FXML file
    	
 	if (Facile.isSelected()) {
    		
    		// Load FXML for the 'Facile' difficulty level
 		  diffText.setFont(Font.font("System Bold", 23.0));
			URL url = new File("src/vus/window5.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setTitle("Play time");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			((Node) event.getSource()).getScene().getWindow().hide();
		}else if(Moyen.isSelected()){
			// Load FXML for the 'Facile' difficulty level
	 		  diffText.setFont(Font.font("System Bold", 18.0));
			// Load FXML for the 'moyen' difficulty level
			URL url = new File("src/vus/window6.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setTitle("Play time");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			((Node) event.getSource()).getScene().getWindow().hide();
		}else if(Difficile.isSelected()) {
			// Load FXML for the 'Facile' difficulty level
	 		  diffText.setFont(Font.font("System Bold", 10.0));
			// Load FXML for the 'Difficile' difficulty level
			URL url = new File("src/vus/window7.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setTitle("Play time");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			((Node) event.getSource()).getScene().getWindow().hide();
		}else if(impossible.isSelected()){
			// Load FXML for the 'Facile' difficulty level
	 		  diffText.setFont(Font.font("System Bold", 5.0));
			// Load FXML for the 'impossible' difficulty level
			URL url = new File("src/vus/window8.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setTitle("Play time");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			((Node) event.getSource()).getScene().getWindow().hide();
		}else{

		}


    }
    private void setZoomEffectOnButton(Button button) {
        double originalScaleX = button.getScaleX();
        double originalScaleY = button.getScaleY();

        button.setOnMouseEntered(event -> {
            // Effet de zoom lors du survol
            button.setScaleX(originalScaleX * 1.1);
            button.setScaleY(originalScaleY * 1.1);
        });

        button.setOnMouseExited(event -> {
            // Réinitialiser l'échelle lorsque la souris quitte le bouton
            button.setScaleX(originalScaleX);
            button.setScaleY(originalScaleY);
        });
    }
    
    private void setHoverEffect(ImageView imageView) {
        // Configurer l'événement de survol pour l'image
        imageView.setOnMouseEntered(this::zoomIn);
        imageView.setOnMouseExited(this::zoomOut);
    }

    private void zoomIn(MouseEvent event) {
        // Animation pour zoomer légèrement lors du survol
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), (ImageView) event.getSource());
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);
        scaleTransition.play();
    }

    private void zoomOut(MouseEvent event) {
        // Animation pour revenir à la taille normale lorsque le curseur quitte l'image
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), (ImageView) event.getSource());
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }
    @FXML
    void onJouer(MouseEvent event) throws IOException {

        URL url = new File("src/vus/Jouer.fxml").toURI().toURL();

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
