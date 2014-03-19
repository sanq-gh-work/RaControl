package sanq.ractrl.broadcast;

import sanq.ractrl.core.Const;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Sanq
 * Date: 19.03.14
 * Time: 12:57
 * from: http://docs.oracle.com/javase/tutorial/networking/datagrams/clientServer.html
 */

public class BroadcastReceiver extends Thread {
    String fileName = "d:\\BroadcastReceiver.data";
    protected DatagramSocket socket = null;
    protected BufferedReader bufferReader = null;
    protected boolean moreQuotes = false;

    public BroadcastReceiver() {
        super("BroadcastReceiverThread");
        try {
            socket = new DatagramSocket(Const.PORT);
            bufferReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote-file " + fileName + ". Serving time instead");
        } catch (SocketException e) {
            System.err.println("Error create socket on port " + Const.PORT);
        }
    }

    @Override
    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String dataResponse = bufferReader == null ? new Date().toString() : getNextQuote();
                buf = dataResponse.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                moreQuotes = false;
                e.printStackTrace();
            }
        }
        socket.close();
    }

    private String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = bufferReader.readLine()) == null) {
                bufferReader.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}
