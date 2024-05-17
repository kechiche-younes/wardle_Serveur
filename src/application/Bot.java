package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {

public static String[] botFac(String mot, int longueur){
String fichierSource="src/mots/liste_francais.txt";
String fichierDestination = "src/mots/liste_modifie.txt";

String[] result = new String[6];
boolean isvalid=false;
String motTest="";
char[] tab=new char[longueur];
for (int i = 0; i < longueur; i++) {
   tab[i] = '0';
}


while(isvalid==false) {
motTest = randomWordFromFile(fichierSource, longueur);
int i=0;
while( i<longueur) {
if(motTest.charAt(i)==mot.charAt(i)) {
tab[i]=motTest.charAt(i);
isvalid=true;
}
i++;
}
}


result[0]=motTest;
int i=1;

filtrerMots(fichierSource,fichierDestination, tab, longueur);


while( i<6 && !result[i-1].equals(mot)) {
try {
           Thread.sleep(7000); // Pause de 7
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
if(i==5) {
motTest=mot;
result[5]=mot;
}else {
isvalid=false;
while(isvalid==false && !result[i-1].equals(mot)) {
int nbrTrue=0;
int nbrLtr=0;
motTest = randomWordFromFile( fichierDestination, longueur);



for (int j=0; j<longueur;j++) {
if (tab[j]!='0') {
nbrTrue++;
if (motTest.charAt(j)==tab[j]) {
nbrLtr++;
}
}
}
boolean repete=true;
for(int h =0;h<i;h++) {
if (result[h].equals(motTest)){
repete=false;
}
}
if (nbrLtr==nbrTrue && repete == true) {


isvalid=true;
result[i]=motTest;


for(int h=0;h<longueur;h++) {
if(motTest.charAt(h)==mot.charAt(h)) {
tab[h]=motTest.charAt(h);
}

}
}



}

filtrerMots(fichierDestination,fichierDestination, tab, longueur);


}
i++;


}



return result;
}





public static String[] botDif(String mot, int longueur) {

   String fichierDestination = "src/mots/liste_modifie.txt";
   saveWordsToFile(WordsExtraction.extractWords(),fichierDestination);

String[] result = new String[6];
boolean isvalid=false;
String motTest="";
char[] tab=new char[longueur];
for (int i = 0; i < longueur; i++) {
   tab[i] = '0';
}


while(isvalid==false) {
motTest = randomWordFromFile(fichierDestination, longueur);
int i=0;
while( i<longueur) {
if(motTest.charAt(i)==mot.charAt(i)) {
tab[i]=motTest.charAt(i);
isvalid=true;
}
i++;
}
}


result[0]=motTest;
int i=1;

filtrerMots(fichierDestination,fichierDestination, tab, longueur);


while( i<6 && !result[i-1].equals(mot)) {
try {
           Thread.sleep(4000); // Pause de 7
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
if(i==5) {
motTest=mot;
result[5]=mot;
}else {
isvalid=false;
while(isvalid==false && !result[i-1].equals(mot)) {
int nbrTrue=0;
int nbrLtr=0;
motTest = randomWordFromFile( fichierDestination, longueur);



for (int j=0; j<longueur;j++) {
if (tab[j]!='0') {
nbrTrue++;
if (motTest.charAt(j)==tab[j]) {
nbrLtr++;
}
}
}
boolean repete=true;
for(int h =0;h<i;h++) {
if (result[h].equals(motTest)){
repete=false;
}
}
if (nbrLtr==nbrTrue && repete == true) {


isvalid=true;
result[i]=motTest;


for(int h=0;h<longueur;h++) {
if(motTest.charAt(h)==mot.charAt(h)) {
tab[h]=motTest.charAt(h);
}

}
}



}

filtrerMots(fichierDestination,fichierDestination, tab, longueur);


}
i++;


}
















return result;
}



public static void filtrerMots(String fichierSource, String fichierDestination, char[] lettres, int longueur) {
       List<String> motsFiltres = new ArrayList<>();
       

       try (BufferedReader reader = new BufferedReader(new FileReader(fichierSource))) {
           String mot;
           
           while ((mot = reader.readLine()) != null) {
           
               if (mot.length() == longueur && contientLettres(mot, lettres)) {
               
               
                   motsFiltres.add(mot);
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

       // Ã‰crire les mots filtrÃ©s dans le fichier destination
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierDestination))) {
           for (String motFiltre : motsFiltres) {
               writer.write(motFiltre);
               writer.newLine();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    private static boolean contientLettres(String mot, char[] lettres) {
        for (char lettre : lettres) {
        if (lettre!='0') {
            if (mot.indexOf(lettre) == -1) {
                return false;
            }
        }
        }
        return true;
    }
   
   
    public static String randomWordFromFile(String filePath, int length) {
        List<String> wordList = readWordsFromFile(filePath); // Lire les mots à partir du fichier
        if (wordList.isEmpty()) {
            return null; // Retourne null si le fichier est vide ou s'il y a une erreur de lecture
        }

        Random random = new Random();
        String word;
        int wordLength;
        boolean isValid;

        do {
            int index = random.nextInt(wordList.size()); // Générer un index aléatoire
            word = wordList.get(index); // Récupérer le mot à l'index généré
            wordLength = word.length();
            isValid = true;

            if (wordLength == length) {
                int i = 0;
                while (i < wordLength && isValid) {
                    char c = word.charAt(i);
                    if (!RandomChoice.isBasicLetter(c)) {
                        isValid = false;
                    }
                    i++;
                }
            }
        } while (wordLength != length || !isValid);

        return word;
    }

    public static List<String> readWordsFromFile(String filePath) {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim()); // Ajouter les mots lus à la liste
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
   
    public static void saveWordsToFile(List<String> wordList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String word : wordList) {
                writer.write(word);
                writer.newLine(); // Ajouter un saut de ligne après chaque mot
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
}
