package sanq.ractrl.core;

import sanq.ractrl.executor.Executor;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: sanq
 * Date: 04.05.13
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class RequestProcessed implements Runnable {
    Socket socket;

    public RequestProcessed(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("IP: " + socket.getInetAddress().toString());
            String inputStream = readRequest(socket.getInputStream());
            System.out.println(inputStream);
            Command cmd = parseRequest(inputStream);
            Executor.execute(cmd);
            System.out.println("-------------------------------------------------------");
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Command parseRequest(String request) {
        Command res = new Command();
        try {
            res.setCommandCode(Integer.parseInt(request));
            return res;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Wrong command code: '" + request + "'");
        }
    }

    private static String readRequest(InputStream is) throws IOException {
        String cmd = "";
        while (!cmd.endsWith(Const.EOC)) {
            cmd += (char) is.read();
        }
        cmd = cmd.substring(0, cmd.indexOf(Const.EOC));
        return cmd;
    }

}
