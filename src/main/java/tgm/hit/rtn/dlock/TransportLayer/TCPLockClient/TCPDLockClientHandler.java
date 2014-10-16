package tgm.hit.rtn.dlock.TransportLayer.TCPLockClient;

import jdk.nashorn.internal.runtime.Version;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.protocol.requests.Hallo;
import tgm.hit.rtn.dlock.protocol.requests.Request;

import java.io.*;
import java.net.*;

/**
 * @author Ari Michael Ayvazyan
 * @version 9.11.2012
 *          //This class needs a cleanup
 * @deprecated tcp is not implemented yet
 */
@Deprecated
public class TCPDLockClientHandler extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket connection;
    private int port;
    private String address;
    //constructor

    public TCPDLockClientHandler(Peer peer) {
        this.port = peer.getPort();
        this.address = peer.getHost();
    }

    public void run() {
        this.startRunning();
    }

    public void sendRequest(Request requestToTransmit) {
        try {
            out.writeObject(requestToTransmit);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //displays messages
    private void showMessage(final String message) {
        System.out.println(message);
    }

    //Connect to server
    public void startRunning() {
        try {
            connect();
        } catch (EOFException eofException) {
            showMessage("connection terminated");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    //connect to server
    private void connect() throws IOException {
        try {

            connection = new Socket(InetAddress.getByName(address), port);
            in = new ObjectInputStream(connection.getInputStream());
            out = new ObjectOutputStream(connection.getOutputStream());
            showMessage("Contacting peer...");
            sendRequest(new Hallo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        showMessage("Connected to peer" + connection.getInetAddress().getHostName());
    }

    //closes connection
    public void close() {
        showMessage("Closing Connection...");
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}