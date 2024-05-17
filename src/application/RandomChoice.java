

/**
 * @author :AOUDJIT Lyes
 * 
 * The RandomChoice class is responsible for generating a random word from the given list
 * with a specified length. It ensures that the selected word is valid, containing only
 * basic letters (A-Z, a-z)
 */
package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomChoice {
    static String word;
    
    /**
     * Checks if a character is a basic letter (A-Z or a-z) to avoid special character like é è à ....
     * 
     * @author AIT MEDDOUR Nadjim
     * @param ch The character to be checked.
     * @return True if the character is a basic letter, false otherwise.
     */
    
    public static boolean isBasicLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * Generates a random word from the provided list with the specified length.
     *
     * @param wordList a list of words from which to choose.
     * @param length   The desired length of the random word that we need.
     * @return A randomly chosen word that meets the specified length and don't have special character.
     */
    
     public static String randomWord(List<String> wordList ,int length ) 
     {
            if (wordList.isEmpty()){
                return null;        // if we have an empty list to avoid errors
            }

            
            Random random = new Random();
            int index = random.nextInt(wordList.size()); // Generate a random index 
            int wordLeng=0;
            boolean isValide;
            
            do{
                isValide=true;
                word=wordList.get(index); // get the word
                wordLeng=word.length();
                char c;
                
                if (wordLeng == length){
                    int i=0;
                    while (i<wordLeng && isValide==true){
                        c=word.charAt(i);
                        if (!isBasicLetter(c)) {//verification
                            isValide=false;
                        }
                        i++;
                    }
                }
                random = new Random();
                index = random.nextInt(wordList.size()); // Generate a random index
            }while(wordLeng != length || isValide==false);
            
            return word; // return a valid word
            
        }


        public static List<String> fillWordList(List<String> wordList, int length, int numberOfWords) {
            List<String> filledList = new ArrayList<>(); // Créer une nouvelle liste pour stocker les mots remplis

        for (int i = 0; i < numberOfWords; i++) {
            String word = randomWord(wordList, length); // Appeler la méthode randomWord() pour obtenir un mot
            filledList.add(word); // Ajouter le mot à la liste remplie
        }

            return filledList; // Retourner la liste remplie
        }

}

