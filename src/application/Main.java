package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 * @author TAKIA Mohamed
 * The Main class is the access point for our application.
 * It extends the JavaFX Application class and initializes the primary stage.()
 */
public class Main extends Application {

    /**
     * The start method is called when the application is launched.
     * It loads the FXML file for the choice game window and sets up the primary stage.
     *
     * @param primaryStage The primary stage for the application.
     * @throws Exception If an error occurs during the loading of the FXML file.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	

        // Load the FXML file for the choice game window
        Parent root = FXMLLoader.load(getClass().getResource("/vus/choice.fxml"));

        // Set up the primary stage
        primaryStage.setTitle("Wordle");
        primaryStage.setScene(new Scene(root));

        // Display the primary stage
        primaryStage.show();
    }

    /**
     * The main method is the entry point for the Java application.
     * It launches the JavaFX application by calling the launch method.
     *
     * @param args Command-line arguments 
     */
    public static void main(String[] args) {

        launch(args);
    }
}
