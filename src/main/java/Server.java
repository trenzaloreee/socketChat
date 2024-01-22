import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket ss;

    private Map<Integer, ClientHandler> clientsMap;
    private int counter;

    public Server() {
    clientsMap = new HashMap<>();

        try {
            ss = new ServerSocket(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while (true) {
            try {

                Socket socket = ss.accept();

                new Thread(() -> {
                    System.out.println("creating new clienthandler for new client");
                    ClientHandler handler = new ClientHandler(socket, this);
                    clientsMap.put(++counter, handler);
                }).start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeServer() {
        try {
//            is.close();
//            isr.close();
//            br.close();
//            bw.close();
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAll(String message){
        for (Integer id : clientsMap.keySet()){
            clientsMap.get(id).sendMessage(message);
        }
    }



    public static void main(String[] args) {
        System.out.println("Ожидание подключения");
        Server server = new Server();


        server.start();
//        server.closeServer();
    }

}
