package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class communicationControler {

    @FXML
    private TextField input;

    @FXML
    private Text message;



        // Propriété observable pour le message
    private static final StringProperty messageTextProperty = new SimpleStringProperty();

    // Méthode pour obtenir la valeur actuelle du message
    public static String getMessageText() {
        return messageTextProperty.get();
    }

    // Méthode pour définir la valeur du message
    public static void setMessageText(String messageText) {
                // Extraire le message en découpant la chaîne après ":"
                String message = messageText.substring(messageText.indexOf(":") + 1).trim();
                // Définir le message extrait
                messageTextProperty.set(message);
    }

    // Méthode pour accéder à la propriété observable
    public static StringProperty messageTextProperty() {
        return messageTextProperty;
    }

    // Ajouter un écouteur à la propriété observable lors de l'initialisation
    public void initialize() {
        messageTextProperty.addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'interface utilisateur avec le nouveau message
            message.setText(newValue);
        });
    }

    
    @FXML
    void onRetour(ActionEvent event) {

    }

    @FXML
    void onSend(ActionEvent event) {
        
        String message =input.getText();

        Client.startSendMessageLoop(message);

    }

}
