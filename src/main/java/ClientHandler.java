import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;

    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;

    private BufferedWriter bw;
    private Server server;




    public ClientHandler(Socket socket, Server server) {
        this.server = server;

        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            bw.write("Добро пожаловать в чат!");
            bw.newLine();
            bw.flush();

            while (true) {
//                System.out.println(br.readLine());
                String msg = br.readLine();
                server.sendMessageToAll(msg);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        try {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
