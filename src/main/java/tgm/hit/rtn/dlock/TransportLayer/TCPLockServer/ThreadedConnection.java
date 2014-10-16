package tgm.hit.rtn.dlock.TransportLayer.TCPLockServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.packageHandlers.*;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.responses.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @author Ari Michael Ayvazyan
 * @version 9.11.2012
 * @deprecated tcp is not implemented yet
 */
@Deprecated
public class ThreadedConnection extends Thread implements RTNConnection{
    private static final Logger logger = LoggerFactory.getLogger(ThreadedConnection.class);

    private ObjectInputStream in;
    private Socket connection;
    private String serverName = "";
    private PeerManager peerManager;
    private LinkedList<PackageListener> packageListener;


    public ThreadedConnection(Socket sc)//allows multiple connections
    {
        this.connection = sc;
        this.initializeRequestHandlers();
    }

    private void initializeRequestHandlers() {
        this.addRequestHandler(ByePackageHandler.getInstance());
        this.addRequestHandler(GetPeerListPackageHandler.getInstance());
        this.addRequestHandler(HalloPackageHandler.getInstance());
        this.addRequestHandler(LockPackageHandler.getInstance());
        this.addRequestHandler(UnlockPackageHandler.getInstance());
    }

    public void run() {
        this.startRunning();
    }

    //set up and run the server
    public void startRunning() {
        try {
            try {
                this.serverName = this.connection.getInetAddress().getHostName() + " - " + this.connection.getInetAddress().getHostAddress();
                setupStreams();
                whileChatting();
            } catch (Exception e) {
                e.printStackTrace();
                showMessage("Exception");
            } finally {
                close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showMessage("Quitted Connection");
    }

    //displays messages
    private void showMessage(final String message) {
        logger.debug(message);
    }

    //Sets up the connections
    private void setupStreams() throws IOException {
        in = new ObjectInputStream(connection.getInputStream());
        showMessage("Streams are now setup!");
    }

    //during the chat
    private void whileChatting() throws IOException {
        long errors = 0;
        showMessage("Connected");
        //ableToType(true);
        do {
            //have conversation
            try {
                errors=0;
                RTNPackage req = (RTNPackage) in.readObject();
                showMessage("Nachricht Empfangen ");
                packageRequest(req);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("Invalid Input");
                errors++;
                if (errors > 20) break; //stop this connection after 20 errors
            }
        } while (true);
    }


    public Peer getPartner() {
        Peer partner = new Peer(connection.getPort(), connection.getInetAddress().toString());
        return partner;
    }

    /**
     * Adds a RequestListener to listen for Requests.
     *
     * @param rq New request listener.
     */
    public void addRequestHandler(PackageListener rq) {
        if(packageListener == null) packageListener = new LinkedList<PackageListener>();
        packageListener.add(rq);
    }

    /**
     * Handles the requests
     *
     * @param req Request to be handled.
     */
    private void packageRequest(RTNPackage req) {
        for(PackageListener handler: this.packageListener){
            handler.handlePackage(req, this);
        }
    }

    public void answer(Response response) {
        //TODO send the response
    }

    private void close() {
        showMessage("Closing Connection...");
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public PeerManager getPeerManager() {
        return peerManager;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }
}
