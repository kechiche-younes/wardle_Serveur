package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
/**
 * The `window7Controler` class is the controller for the game window with the length of 7.
 * Authors: KECHADI Farid, KECHICHE Younes, TAKIA Mohamed
 */
public class window7Controler {
 	 SimilarWordsFinder similarWordsFinder;
     @FXML
     private ImageView indice1;
    @FXML
    private TextField text1, text11, text111, text112, text12, text121, text122, text13, text131, text132,
            text14, text141, text142, text15, text151, text152, text16, text161, text162, text17, text171,
            text172, text18, text19, text2, text21, text22, text3, text31, text32, text4, text41, text42,
            text5, text51, text52, text6, text61, text62, text7, text71, text72,mots,hh,mm,ss;
    @FXML
    private Button ok,Sup;
    private TextField[][] textFields;
    private Scene scene;
    @FXML
    private Button A;
    @FXML
    private Button Z;
    static int incrimentX = 0;
    static int incrimentY = 0;
    static char[] targetWord;
    final int wordLength = 7;
    int greenCounter = 0;
    private TimerInc timer;
    static int attempts;
    private String time;
    private int orangeColor = 0;
    @FXML
    private void initialize() {
        // Initialize the 2D array of TextFields
        textFields = new TextField[][]{
            {text1, text2, text3, text4, text5, text6, text7},
            {text11, text12, text13, text14, text15, text16, text17},
            {text18, text21, text31, text41, text51, text61, text71},
            {text111, text121, text131, text141, text151, text161, text171},
            {text19, text22, text32, text42, text52, text62, text72},
            {text112, text122, text132, text142, text152, text162, text172}
        };

        RandomChoice.randomWord(WordsExtraction.extractWords(), wordLength);
        System.out.println(RandomChoice.word);
        targetWord = RandomChoice.word.toCharArray();

        // Timer
        timer = new TimerInc();
        timer.startTimer(ss, mm, hh);
        attempts = 0;

        Thread thread = new Thread(() -> {
            String[] result = Bot.botFac(RandomChoice.word, 7);
            for (String res : result) {
                int i = 1;
                System.out.println(res);
                if (res.equals(RandomChoice.word)) {
                    System.out.println("trouveeee");

                    // Stop the timer
                    Platform.runLater(() -> timer.stopTimer());

                    // Calculate the score
                    int h = Integer.parseInt(hh.getText());
                    int m = Integer.parseInt(mm.getText());
                    int s = Integer.parseInt(ss.getText());
                    int temps = h * 3600 + m * 60 + s;
                    double scoreBot = (700 - i * 20) / temps;
                    System.out.println("scoreee ==== " + scoreBot);

                    // Show success animation
                    Platform.runLater(this::showSuccessAnimation);

                    // Show an alert message
                    Platform.runLater(this::showAlertMessage);

                    break;
                }
                i++;
            }
        });

        thread.start();
    
     
          // Ajoutez l'effet de zoom lors du survol
          setZoomEffectOnImageView(indice1);
      }


      private void showAlertMessage() {
        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bot Finished");
        alert.setHeaderText(null);
        alert.setContentText("The bot has successfully found the word!");

        // Show the alert and wait for it to be closed
        alert.showAndWait();
    }
    private void showSuccessAnimation() {
        // Use FadeTransition to create a fade-out animation
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), textFields[incrimentY][incrimentX]);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);

        // Alternatively, use ScaleTransition to create a scaling animation
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), textFields[incrimentY][incrimentX]);
        scaleTransition.setFromX(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);

        // Play both animations
        fadeTransition.play();
        scaleTransition.play();
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
          Sup.setOnAction(event -> {
              retourButton();
          });
      }

      public void retourButton() {
          System.out.println("retourButon executed");
          System.out.println("incX = " + incrimentX);
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
              System.out.println(motUtilisateur.toString());

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
                                      for (int d = i + 1; d < targetWord.length; d++) {
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
                    
                   double score =(greenCounter*100 + orangeColor*50 - incrimentY*20)/temps   ;
                   System.out.println("scoreee ==== " + score);
    
                }
                if (greenCounter == wordLength && incrimentX != 6 ) { 

                      //timer.stopTimer();
                      //time = hh.getText()+":"+mm.getText()+":"+ss.getText();

                      int h= Integer.parseInt(hh.getText());
                      int m= Integer.parseInt(mm.getText());
                      int s= Integer.parseInt(ss.getText());
                              
                      int temps=h*3600+m*60+s   ;
                      
                     double score =(greenCounter*100 + orangeColor*50 - incrimentY*20)/temps   ;
                     System.out.println("scoreee ==== " + score);
      
 

                      try {

                      // Vérifier si le fichier existe

                      File file = new File("src/mots/stat.txt");



                      if (!file.exists()) {

                      // Si le fichier n'existe pas, le créer

                      file.createNewFile();

                      }



                      // Écrire l'information dans le fichier

                      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                      writer.write(time+" "+attempts);

                      writer.newLine(); // Ajouter une nouvelle ligne

                      writer.close();



                      System.out.println("L'information a été enregistrée dans le fichier avec succès.");



                      } catch (IOException e) {

                      e.printStackTrace();

                      System.err.println("Erreur lors de l'enregistrement de l'information dans le fichier.");

                      }


                  }
                  greenCounter = 0;
                  for (int i = 0; i < targetWord.length; i++) {
                      coloredLetter[i] = false;
                  }
              }
          }
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
          URL url = new File("src/vus/window7.fxml").toURI().toURL();

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
