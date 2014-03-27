package sanq.ractrl.launcher;

import sanq.ractrl.core.Const;
import sanq.ractrl.core.RequestProcessed;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Launcher {

    public static void main(String[] args) throws LineUnavailableException, InterruptedException, IOException {

        //TODO: implements with command queue
        ServerSocket serverSocket = new ServerSocket( Const.PORT, 256);
        System.out.println("Server started successfully..." );
        while (true) {
          Socket socket = serverSocket.accept();
          new RequestProcessed(socket).run();
        }
    }

}
