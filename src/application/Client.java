package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private Scanner scanner;

    private static String userName = ""; 


 

    public Client(final String serverIP, final int port, String username) {
        try {
            socket = new Socket(serverIP, port);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.scanner = new Scanner(System.in);
            Client.userName = username; // Définir le nom d'utilisateur

            // Appeler la méthode pour définir le nom d'utilisateur sur le serveur
            setUsername(username);
 
            
            startReadMessageLoop();
            startSendMessageLoop();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }




    private void setUsername(String username) {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Sent username information: " + username);
    }

    

        // Getter pour userName
        public static String getUserName() {
            return userName;
        }
    
        // Setter pour userName
        public static void setUserName(String newUserName) {
            userName = newUserName;
            System.out.println("dans setUserName "+ userName);
        }
    
    public void startSendMessageLoop() {
        try {
            while (socket.isConnected()) {

                bufferedWriter.write(userName + " - " +scanner.nextLine());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            System.out.println("Send message loop done 1.");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    static boolean startPlayButton = false;

    public static void startSendMessageLoop(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("Send message loop done 2.");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }






    static int cmp = 0;
    static boolean forName = false;
    static boolean forStart = false;
    static boolean forMessage = true;

    private void startReadMessageLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        forMessage = true;
                        String message = bufferedReader.readLine();

                        System.out.println("lire le message " + message );
                        String[] parties = message.split("/");
                        String[] valeur = message.split("@");
                        String[] Time = message.split("!");

                        String[] motListe = message.split("\\|");


                        
                        if(Time.length == 2){
                            window5Controler.settemps(message);
                        }if(motListe.length > 1){

                            String motFinal = motListe[1].trim();
                            System.out.println("le mot final "+ motFinal);
                            window5Controler.setMot(motFinal);
                        } if(valeur.length == 4){
                            window5Controler.setValeur(message);
                        } if(valeur.length == 2){
                            String mot = parties[0].trim();
                            String[] nomAffiche = mot.split("-");
                            window5Controler.setMot(nomAffiche[1].trim());

                        } if (parties.length == 2 ) {
                            String avant = parties[0].trim();
                            System.out.println("-Avant: " + avant);
                            String[] nomAffiche = avant.split("-");
                            Server.addToClientToStart(parties[0].trim());
                            Server.displayClientToStart();

                            System.err.println("la taille " + Server.ClientToStart.size());
                            forStart = false;
                            forMessage = false;
                        } if(forName == false){
                            loubiControler.setpAnom(message);
                            forName = true;
                            forMessage = false;
                        } if(Server.ClientToStart.size() == 2 ){
                            loubiControler.setMessageText("preDeux");
                            Server.addToClientToStart("fin");

                            startSendMessageLoop("preDeux");
                            forMessage = false;
                        } if(forMessage == true || message.equals("preDeux")){
                            System.out.println("dans le else");
                            loubiControler.setMessageText(message);
                        }
                    }
                    System.out.println("Read message loop done.");
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Demande à l'utilisateur de saisir l'adresse IP du serveur
            System.out.print("Enter the server IP address: ");
            String serverIP = scanner.nextLine();

            // Utilise l'adresse IP saisie par l'utilisateur et le port du serveur
            new Client(serverIP, Server.port,userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












    /*private void setUsername() {
        System.out.print("Enter your username: ");
        try {
            userName = scanner.nextLine();
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (final IOException e) {
            e.printStackTrace();
        }

        System.out.println("Sent username information.");
    }*/
}
