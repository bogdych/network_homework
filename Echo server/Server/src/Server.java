import java.net.*;

public class Server {
    public static final int THREADS = 8;

    public Server() {
    }

    public void init() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        ServerSocket socket = new ServerSocket(5000, 50, inetAddress);
        System.out.println(socket.getLocalSocketAddress().toString());
        System.out.println(socket.getInetAddress().getHostAddress());
    }

}
