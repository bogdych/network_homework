import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class EchoClient {
    public static final int THREADS = 30000;
    public static final int PORT = 5000;
    public static InetAddress inetAddress;

    public static void main(String[] args) {
        try {
            inetAddress = InetAddress.getLocalHost();
            Thread[] tr = new Thread[THREADS];
            for (int i = 0; i < THREADS; i++) {
                tr[i] = new Thread(new Client());
                tr[i].start();
            }
            while (true){

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Client implements Runnable {
    private static int id;
    private int currId = 0;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;


    public Client() {
        currId = id++;
    }

    @Override
    public void run(){
        try {
            try {
                clientSocket = new Socket(EchoClient.inetAddress, 5000);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String serverWord;
                String str;

                str = "msg1";
                out.write("Client" + currId + ": sent " + str  + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println("Client" + currId + ": received " + serverWord);

                str = "msg2";
                out.write("Client" + currId + ": sent " + str  + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println("Client" + currId + ": received " + serverWord);

                str = "msg3";
                out.write("Client" + currId + ": sent " + str + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println("Client" + currId + ": received " + serverWord);

                System.out.println("Client" + currId + ": i'm done");

            } finally {
                System.out.println("Client" + currId + ": socket closed");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}