import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class EchoServer {
    ServerSocket serverSocket;
    int port = 5000;

    public EchoServer(){
    }

    public void start() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            serverSocket = new ServerSocket(port, 50, inetAddress);

            while(true){
                System.out.println("waiting for connection...");
                Thread clientThread = new Thread(new ClientHandler(serverSocket.accept()));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("closing server socket");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
