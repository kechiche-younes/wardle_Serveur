package application;

import java.io.IOException;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class connexionControler {

	@FXML
	private Button cofirmeNom;
	@FXML
	private Text diff;
	@FXML
	private ImageView help;
	@FXML
	private TextField ip, nom;
	@FXML
	private Button retour;

	private static boolean connexion = false;
	@FXML
	void onConfirme(ActionEvent event) throws IOException {
		connexion = true;
		String username = nom.getText();
		String ipText = ip.getText();
		// Vérifier si le champ nom est vide
		if (username.isEmpty() || ipText.isEmpty()) {
			System.out.println("Erreur : Le champ nom est vide.");
		}else {
			System.out.println("Nom : " + username);
			System.out.println("Adresse IP : " + ipText);


			loubiControler.setpBnom(username);
			Thread clientThread = new Thread(() -> {
				ClientHandler.setNomPlayer(username);

				
				ClientHandler.clientConnectedListe.add(username);
				new Client(ipText, Server.port,username);
				
	

			});clientThread.start();

		}
		Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(5), evt -> {
        try {
            	loubiControler.loadLoubiPage(event,username);
            }catch (IOException e) {
                e.printStackTrace();
            }}));timeline.play();
        }
	 

		static boolean getConnexion(){
			return connexion;
		}

	@FXML
	void onJouer(MouseEvent event) {

	}

	@FXML

	void onRetour(ActionEvent event) {

	}

}