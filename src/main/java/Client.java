import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    // todo BufferedReader, BufferedWriter
    // todo socket chat google

    BufferedReader br;
    BufferedWriter bw;
    Scanner sc;

    public Client() {       // todo optional separate method init()
        try {
            socket = new Socket("localhost", 8080);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            sc = new Scanner(System.in);

            printMessage();
            startClient();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startClient() {

        new Thread(() -> {
            while (true){
                printMessage();
            }
        }).start();


        while (true) {
            try {
                bw.write(sc.nextLine());
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printMessage(){
        try {
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void setUp() {
//        try {
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}
