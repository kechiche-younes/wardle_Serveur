package application;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class Server {
    public static final int port = 1234;
    public static List<ClientHandler> connectedClients = new ArrayList<>();
    private final ServerSocket serverSocket;
    static ArrayList<String> clientConnectServer = new ArrayList<>();
    private static ArrayList<ClientHandler> clientHandlers;
    public static Object getClientConnectServer;
    
    

    public static void main(final String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        server.startAcceptClientLoop();
    }



    public Server(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        clientHandlers = new ArrayList<>();
    }



     static ArrayList<String> ClientToStart = new ArrayList<>();

    // Méthode pour ajouter un élément à la liste
    public static void addToClientToStart(String item) {
        ClientToStart.add(item);
    }

    // Méthode pour afficher les éléments de la liste
    public static void displayClientToStart() {
        System.out.println("ClientToStart:");
        for (String item : ClientToStart) {
            System.out.println(item);
        }
    }

    // Méthode pour obtenir la liste des clients à démarrer
    public static ArrayList<String> getClientToStart() {
        return ClientToStart;
    }

    // Méthode pour définir la liste des clients à démarrer
    public static void setClientToStart(ArrayList<String> clientToStart) {
        ClientToStart = clientToStart;
    }

        // sender client to server to every client
        public static void EnvoieDuTableau(final String message) {
            for (final ClientHandler clientHandler : clientHandlers) {
                clientHandler.recieveMessage(message);
            }
            System.out.println("Message has been broadcasted.");
        }

    // sender client to server to every client
    public static void broadcastMessage(final ClientHandler sender, final String message) {
        for (final ClientHandler clientHandler : clientHandlers) {
            
            clientHandler.recieveMessage(sender, message);
        }
        System.out.println("Message has been broadcasted.");
    }

    private static void sendServerMessage(final String message) {
        for (final ClientHandler clientHandler : clientHandlers) {
            clientHandler.recieveServerMessage(message);
        }
        System.out.println("Server message has been broadcasted.");
    }

    

    public void startAcceptClientLoop() {
        try {
            while (!serverSocket.isClosed() ) {
                System.out.println("start server ..");
                final Socket socket = serverSocket.accept();
                final ClientHandler clientHandler = new ClientHandler(this, socket);
                
                new Thread(clientHandler).start();
                connectedClients.add(clientHandler);
                // Attendre que le nom d'utilisateur soit défini dans le ClientHandler
                while (ClientHandler.getUsername() == null) {
                    Thread.sleep(100); // Attendre un petit moment
                }
    
                // Une fois que le nom d'utilisateur est défini, ajoutez le ClientHandler à la liste
                clientHandlers.add(clientHandler);
                clientConnectServer.add(ClientHandler.getUsername());

    
                //broadcastMessage(connectedClients.get(0), "heeloo " + clientConnectServer.get(0));
                sendServerMessage("");
                //printConnectedClients() ;
            }
        } catch (final IOException e) {
            System.out.println("There was an IOException.");
        } catch (final InterruptedException e) {
            System.out.println("Thread interrupted while waiting for username.");
        }
    }
    

    public void printConnectedClients() {
        System.out.println("Connected clientsss:");
        for (String clientName : clientConnectServer) {
            System.out.println("- " + clientName);
        }
    }







    public static void setText(String string) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setText'");
    }


}



