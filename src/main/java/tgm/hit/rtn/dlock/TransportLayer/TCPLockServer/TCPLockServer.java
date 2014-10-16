package tgm.hit.rtn.dlock.TransportLayer.TCPLockServer;

import tgm.hit.rtn.dlock.DLock;
import tgm.hit.rtn.dlock.GotLock;
import tgm.hit.rtn.dlock.PeerManagers.PeerManager;
import tgm.hit.rtn.dlock.TransportLayer.util.StoppableRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 9.11.2012
 * @deprecated tcp is not implemented yet
 */
@Deprecated
public class TCPLockServer implements StoppableRunnable {

    private DLock dLock;
    private GotLock gotLock;
    private PeerManager peerManager;
    private final ServerSocket serverSocket;
    private LinkedList<Thread> servers;
    private boolean continueWork = true;

    public TCPLockServer(GotLock gotLock, PeerManager manager, ServerSocket serverSocket) {
        servers = new LinkedList<Thread>();
        this.gotLock = gotLock;
        peerManager = manager;
        this.serverSocket = serverSocket;
    }

    private void waitForConnection() throws IOException {
        showMessage("Waiting for connection...");
        Socket sc = serverSocket.accept();
        establishConnection(sc);
        showMessage("Now connected to " + sc.getInetAddress().getHostName());
    }

    //displays messages
    private void showMessage(final String message) {
        System.out.println("Debug:" + " - " + new Date(System.currentTimeMillis()).toString() + " : " + message);
    }

    public void establishConnection(Socket sc) {
        try {
            ThreadedConnection t = new ThreadedConnection(sc);
            servers.addLast(t);
            servers.getLast().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @see Runnable#run()
     */
    public void run() {
        while (continueWork) {
            try {
                waitForConnection();
            } catch (SocketException e) {
                //Expected when calling .close()
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        this.continueWork = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            //This should be no problem
        }
    }
}
