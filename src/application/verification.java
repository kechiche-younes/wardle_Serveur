package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @authors AIT MEDDOUR Nadjim, AOUDJIT Lyes
 * 
 * The verification class provides a method for checking if a given word exists
 * in the specified file containing a list of words that are in the French dictionary .
 */
public class verification {
	
	
	/**
     * Checks if a given word exists in the file.
     *
     * @param wordToCheck The word to be checked for existence in the list.
     * @return True if the word exists, false otherwise.
     */

    public static boolean checkWord(String wordToCheck) {
    	String path = "C:/Users/younes/Documents/java_project/javafx/src/mots/liste_francais.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.trim().equals(wordToCheck)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }
        return false;
    }

}
