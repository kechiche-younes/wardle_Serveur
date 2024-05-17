package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;

/**
 * The `window5Controler` class is the controller for the game window with the length of 5.
 * Authors: KECHADI Farid, KECHICHE Younes, TAKIA Mohamed
 */
public class window5Controler {
     SimilarWordsFinder similarWordsFinder;

    @FXML
    private TextField text1, text11, text111, text112, text12, text121, text122, text13, text131, text132,
            text14, text141, text142, text15, text151, text152, text16, text161, text162, text17, text171,
            text172, text18, text19, text2, text21, text22, text3, text31, text32, text4, text41, text42,
            text5, text51, text52, text6, text61, text62, text7, text71, text72, mots, hh, mm, ss,ligne,orange,green;


    @FXML
    private Text advers;
    @FXML
    private Text temps;
    @FXML
    private Button ok, Sup;
    private TextField[][] textFields;
    private Scene scene;
    @FXML
    private Button A;
    @FXML
    private Button Z;
    int incrimentX = 0;
    int incrimentY = 0;
    static char[] targetWord;
    final static int wordLength = 5;
    int greenCounter = 0;
    private TimerInc timer;
    static int attempts;
    private String time;
    private int orangeColor = 0;
    private int nombreDeMot = 0;
    @FXML
    private ImageView indice;
    static List<String> motsListe = new ArrayList<>();
    static List<String> motListSend = null ;
    private int indiceMot = 0;
    double score=0;


    /**
     * Initializes the controller.
     */
        static String mot ;
        public static void setMot(String word) {
            System.out.println("============================================> " + word);
            motsListe.add(word);
            mot = word;
        }
        public static String getMot() {
            System.out.println("le mot dans le get");
            return mot;
        }

     private static  void choiceMot(){
       // RandomChoice.randomWord(WordsExtraction.extractWords(), wordLength);
        //String motword = RandomChoice.word;
        if(playerWithControler.getMoiServer() == true){
            motListSend = RandomChoice.fillWordList(WordsExtraction.extractWords(), wordLength, 3);
            System.out.println("Les mots choisis sont : ");
            for (String mot : motListSend) {
                Client.startSendMessageLoop("mot | "+mot);
                motsListe.add(mot);
                System.out.println(mot);
            }
        }
           
     }
    @FXML
    private void initialize() {
                // Initialize the array

        textFields = new TextField[][]{
            {text1, text2, text3, text4, text5},
            {text11, text12, text13, text14, text15},
            {text18, text21, text31, text41, text51},
            {text111, text121, text131, text141, text151},
            {text19, text22, text32, text42, text52},
            {text112, text122, text132, text142, text152}};

      
            textFields[incrimentY][incrimentX].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
            
               // Timer
            nombreDeMot = nombreDeMot+1;
            if(nombreDeMot == 1) {
                choiceMot();
                timer = new TimerInc();
                timer.startTimer(ss, mm, hh);
                attempts = 0 ;
            }
            incrimentX = incrimentY = 0;
        System.out.println("le mot choisi est -->: " + motsListe.get(indiceMot));


        advers.setText(ClientHandler.getNomPlayer());


        if(Client.getUserName().equals(loubiControler.getpAnom())){
            advers.setText(loubiControler.getpBnom());
        }else{
            advers.setText(Server.ClientToStart.get(0));
        }


            // Split the word into letters
            targetWord =motsListe.get(indiceMot).toCharArray();
            System.out.println("la taille  erst "+ targetWord.length);
    
     
  


        
        // Ajoutez l'effet de zoom lors du survol
        setZoomEffectOnImageView(indice);


        ValeurProperty.addListener((observable, oldValue, newValue) -> {

            String[] valeur = newValue.split("@");
            System.out.println("dans window5 " + newValue);
            ligne.setText(valeur[1].trim().substring(0, 1));
            green.setText(valeur[2].trim().substring(0, 1));
            orange.setText(valeur[3].trim().substring(0, 1));
                
            
        });




        TempsProperty.addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'interface utilisateur avec le nouveau message

                temps.setText(newValue);
                timer.stopTimer();

                for (TextField[] row : textFields) {
                    for (TextField textField : row) {
                        textField.setDisable(true);
                        textField.setStyle("-fx-border-color: black ; -fx-background-color: gray; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
                    }
                }
                
            
            
        });

    }
    private void setZoomEffectOnImageView(ImageView imageView) {
        double originalScale = imageView.getScaleX();

        imageView.setOnMouseEntered(event -> {
            // Effet de zoom lors du survol
            imageView.setScaleX(originalScale * 1.3);
            imageView.setScaleY(originalScale * 1.3);
        });

        imageView.setOnMouseExited(event -> {
            // Réinitialiser l'échelle lorsque la souris quitte l'image
            imageView.setScaleX(originalScale);
            imageView.setScaleY(originalScale);
        });
    }
    @FXML
    void lettre(ActionEvent ae) {
        boolean[] coloredLetter = new boolean[targetWord.length];
        for (int i = 0; i < targetWord.length; i++) {
            coloredLetter[i] = false;
        }

        String no = ((Button) ae.getSource()).getText();
        textFields[incrimentY][incrimentX].setText(no);

        incrimentX++;
        if (incrimentX < wordLength) {
            textFields[incrimentY][incrimentX].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
            textFields[incrimentY][incrimentX - 1].setStyle("-fx-border-color:#C5C5C5 ; -fx-background-color: black;  -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
        }

        scene = text1.getScene();
        ok.requestFocus();
        scene.setOnKeyTyped(event -> {
            String ourWord = event.getCharacter();

            if (ourWord.equals("\b")) {
                retourButton();
            } else if (ourWord.equals("\n")) {
                handleOkButton(coloredLetter);
            } else {
                lettreButton(ourWord);
            }
        });

        ok.setOnAction(event -> handleOkButton(coloredLetter));
        Sup.setOnAction(event -> { retourButton(); });
    }

    public void retourButton() {
       
        if (incrimentX > 0) {
            incrimentX--;
            textFields[incrimentY][incrimentX].setText("");
            textFields[incrimentY][incrimentX].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
            textFields[incrimentY][incrimentX + 1].setStyle("-fx-border-color:#C5C5C5 ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
        }
    }

    private void lettreButton(String lettre) {
        textFields[incrimentY][incrimentX].setText(lettre);
        incrimentX++;

        if (incrimentX < wordLength) {
            textFields[incrimentY][incrimentX].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
            textFields[incrimentY][incrimentX - 1].setStyle("-fx-border-color:#C5C5C5 ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
        } else {
            // Disable the event handler after reaching the limit
            scene.setOnKeyTyped(null);
        }
    }

    private void handleOkButton(boolean[] coloredLetter) {
        if (incrimentX == wordLength) {
            // Build the word from the TextField
            StringBuilder motUtilisateur = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                motUtilisateur.append(textFields[incrimentY][j].getText());
            }
            System.out.println("voir le mot "+ motUtilisateur.toString());

            // Check if the user's word exists in the text file
            boolean motExiste = verification.checkWord(motUtilisateur.toString().toLowerCase());
            if (!motExiste) {
                for (int j = 0; j < wordLength; j++) {
                    textFields[incrimentY][j].setText("");
                    incrimentX = 0;
                    textFields[incrimentY][incrimentX].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
                    textFields[incrimentY][wordLength - 1].setStyle("-fx-border-color:#C5C5C5 ; -fx-background-color: black;  -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
                }
            } else {
                attempts++;
                for (int i = 0; i < (wordLength - 1); i++) {
                    if (!textFields[incrimentY][i].getStyle().contains("green")) {
                        for (int j = 0; j < wordLength; j++) {
                            textFields[incrimentY][j].setDisable(true);
                        }
                    }
                }
                // After the animation, set the border color to blue
                for (int R = 0; R < wordLength; R++) {
                    if (!textFields[incrimentY][R].getStyle().contains("green")) {
                        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), textFields[incrimentY][R]);
                        rotateTransition.setByAngle(360); // 360-degree rotation
                        rotateTransition.play(); // Start the animation
                    }
                }
                int incY = incrimentY;
                incrimentY++;
                greenCounter = 0;
                if (incrimentY < 6) {
                    textFields[incrimentY][0].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
                } else {
                    textFields[incrimentY - 1][0].setStyle("-fx-border-color: blue ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");
                }
                incrimentX = 0;
                for (int z = 0; z < wordLength; z++) {
                    String textFieldText = textFields[incY][z].getText();

                    if (textFieldText != null && !textFieldText.isEmpty()) {
                        if (textFieldText.toLowerCase().charAt(0) == targetWord[z]) {
                            for (int c = 0; c < targetWord.length; c++) {
                                if (coloredLetter[z] == true && textFields[incY][c].getText().toLowerCase().charAt(0) == targetWord[z]) {
                                    textFields[incY][c].setStyle("-fx-control-inner-background: black;");

                                    coloredLetter[z] = false;
                                }
                            }
                            
                            textFields[incY][z].setStyle("-fx-control-inner-background: green;");
              
                            coloredLetter[z] = true;
                            greenCounter++;


  


                        } else {
                            for (int i = 0; i < targetWord.length; i++) {
                                if (targetWord[i] == textFields[incY][z].getText().toLowerCase().charAt(0) && coloredLetter[i] == false) {
                                    coloredLetter[i] = true;
                                    textFields[incY][z].setStyle("-fx-control-inner-background: orange;");
                                    orangeColor++ ;

                                    for (int d = i +1 ; d < targetWord.length; d++) {
                                        if (textFields[incY][d].getText().toLowerCase().charAt(0) == textFields[incY][i].getText().toLowerCase().charAt(0)) {
                                            coloredLetter[i] = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(incrimentX == 6){

                    int h= Integer.parseInt(hh.getText());
                    int m= Integer.parseInt(mm.getText());
                    int s= Integer.parseInt(ss.getText());
                            
                    int temps=h*3600+m*60+s   ;
                    
                   score+=(greenCounter*100 + orangeColor*50 - incrimentY*20)/temps   ;
                   System.out.println("scoreee ==== " + score);
    
                }if (greenCounter == wordLength && incrimentX != 6 ) {   
                    
                    // on prend un comptaire (cpt) on vérifier s'il est == 3 
                    //  sinon on a appelle choiceMot 
                     // la question comment relancer la procedure ?
        

                     int h= Integer.parseInt(hh.getText());
                     int m= Integer.parseInt(mm.getText());
                     int s= Integer.parseInt(ss.getText());
                             
                     int temps=h*3600+m*60+s   ;
                     
                    score+=(greenCounter*100 + orangeColor*50 - incrimentY*20)/temps   ;
                    System.out.println("scoreee ==== " + score);
     

                     if(nombreDeMot < 3){


                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                        indiceMot = indiceMot + 1;

               
  
                        for (int i = 0; i < textFields.length; i++) {
                            for (int j = 0; j < textFields[i].length; j++) {
                                textFields[i][j].setText("");
                                textFields[i][j].setStyle("-fx-border-color: white ; -fx-background-color: black; -fx-text-fill:white ;-fx-border-radius:10px;-fx-border-width:2px");    	

                            }
                        }
                        
                        incrimentX = 0;
                        incrimentY = 0;
                        initialize();
                    }else{
                        System.out.println("bravo vous avez ganger ");
                        timer.stopTimer();
                        time = hh.getText()+":"+mm.getText()+":"+ss.getText();

                        Client.startSendMessageLoop("Le temps est ! "+ time );

                        try {

                        // Vérifier si le fichier existe

                        File file = new File("src/mots/stat.txt");



                        if (!file.exists()) {

                        // Si le fichier n'existe pas, le créer

                        file.createNewFile();

                        }



                        // Écrire l'information dans le fichier

                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                        writer.write(time+" "+score);

                        writer.newLine(); // Ajouter une nouvelle ligne

                        writer.close();



                        System.out.println("L'information a été enregistrée dans le fichier avec succès.");



                        } catch (IOException e) {

                        e.printStackTrace();

                        System.err.println("Erreur lors de l'enregistrement de l'information dans le fichier.");

                        }

                }
                }


                // la il faut envoyer combien de bon lettre 
                // aussi envoyer combien de laiitre mal placer 
                // il faut envoyer le nombre de tentative 


                    Client.startSendMessageLoop("ligne @ "+ incrimentY +" green @ " + greenCounter +" orange @ "+ orangeColor);
                



                greenCounter = 0;
                for (int i = 0; i < targetWord.length; i++) {
                    coloredLetter[i] = false;
                }
            }
        }
    }



//-------------------------- pour envoyer le temps ---------------------------


        private static final StringProperty TempsProperty = new SimpleStringProperty();

        // Méthode pour obtenir la valeur actuelle du message
        public static String gettemps() {
        return TempsProperty.get();
        }
        // Méthode pour définir la valeur du message
        public static void settemps(String newTime) {

        // Définir le message extrait
        TempsProperty.set(newTime);
        }



        // Méthode pour accéder à la propriété observable
        public static StringProperty messageTimeProperty() {
        return TempsProperty;
        }

////////////////////////////////////////////////////////////////////////////


    private static final StringProperty ValeurProperty = new SimpleStringProperty();

            // Méthode pour obtenir la valeur actuelle du message
    public static String getValeur() {
        return ValeurProperty.get();
    }
    // Méthode pour définir la valeur du message
    public static void setValeur(String newValuer) {

        // Définir le message extrait
        ValeurProperty.set(newValuer);
    }
    


    // Méthode pour accéder à la propriété observable
    public static StringProperty messageTextProperty() {
        return ValeurProperty;
    }


    @FXML
    void onIndice(MouseEvent event) {
        similarWordsFinder = new SimilarWordsFinder();
        String similaire = similarWordsFinder.findSimilarWord(RandomChoice.word); 
        mots.setText(similaire);
        System.out.printf(" %s", similarWordsFinder.findSimilarWord(RandomChoice.word));
    }
    
    @FXML
    void onStat(MouseEvent event) throws IOException {
        URL url = new File("src/vus/stat.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        stage.setTitle("Wordle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // Enable the ability to resize the window.
        stage.setResizable(true);

    }
    
    @FXML
    void onRejouer(MouseEvent event) throws IOException {
        incrimentX = 0;
        incrimentY = 0;
        URL url = new File("src/vus/window5.fxml").toURI().toURL();

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
        incrimentX = 0;
        incrimentY = 0;
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


