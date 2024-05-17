package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private static String username;
    private Server server;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    public static ArrayList<String> clientConnectedListe = new ArrayList<>();
    private static String nomPlayer ;
    public static String nom1;
   
    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            setUsername();
            //run(); //sendMessage();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        //setUsername();
        startSendMessageLoop();
    }
 
    static boolean send = false;
    // server to client
    public void recieveMessage(ClientHandler sender, String message) {
   

        System.out.println("le start Player button " + send);
        if(sender == this && send == false)
        {
            return;
        }
    try {
  
                System.out.println("on est la dan le client handle ligne 50");
                bufferedWriter.write(ClientHandler.nomPlayer + "-" + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                send = false;
        
          
        }catch (IOException e) {}
    }


    public void recieveMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {}
    }

    // il faut modifier cette fonction
    public void recieveServerMessage(String message) {
        try {
            
            if(Server.clientConnectServer.size() > 1){ 
                bufferedWriter.write(Server.clientConnectServer.get(0));
            }else{
                bufferedWriter.write("SERVERERR  . " + message + " . " + Server.clientConnectServer.size() + " . " + Server.clientConnectServer.get(0));
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {}
    }

 
    private void setUsername() {
        try {
            username = bufferedReader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int startPlayer = 0;

    //client to server
    private void startSendMessageLoop() {
        System.out.println("2: start Send message Loop Client handle 86 ");
        try {
            while (socket.isConnected()) {
                System.out.println("en Attand le message ... ");
                String message = bufferedReader.readLine();
                System.out.println("message envoyer est : " + message  );
                // afficher le clinethandler.this
                Server.broadcastMessage(ClientHandler.this, message);
                //Server.EnvoieDuTableau(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Getter pour le champ nomPlayer
    public static String getNomPlayer() {
        return nomPlayer;
    }
 
    // Setter pour le champ nomPlayer
    public static void setNomPlayer(String nom) {
        nomPlayer = nom;
    }
    public static String getUsername() {
        return username;
    }


    // Méthode pour retourner la taille de la liste clientConnectedListe
    public static int getClientConnectedListSize() {
        return clientConnectedListe.size();
    }
 
    // Méthode pour retourner la liste clientConnectedListe
    public static ArrayList<String> getClientConnectedList() {
        return clientConnectedListe;
    }

}