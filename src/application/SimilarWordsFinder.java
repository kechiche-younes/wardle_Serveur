package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SimilarWordsFinder {

	public static String findSimilarWord(String targetWord) {
	    String similarWord = "";  // Initialisez la chaîne pour stocker le mot similaire

	    try {
	        // Exécution du script Python pour extraire les mots similaires
	    	ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/mots/word2Vec.py", targetWord);

	        processBuilder.redirectErrorStream(true);  // Redirige la sortie d'erreur vers la sortie standard
	        Process process = processBuilder.start();

	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

	        // Lire la première ligne de la sortie (premier mot similaire)
	        String line = reader.readLine();
	        if (line != null) {
	            similarWord = line.trim();
	        }


	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erreur lors de l'exécution du script Python : " + e.getMessage());
	    }

	    return similarWord;
	}
}
