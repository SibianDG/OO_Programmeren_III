import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Netwerkprogrammatie {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345, 2);
            Socket connection = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
