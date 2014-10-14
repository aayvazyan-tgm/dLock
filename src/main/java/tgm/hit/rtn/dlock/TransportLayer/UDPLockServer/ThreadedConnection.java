package tgm.hit.rtn.dlock.TransportLayer.UDPLockServer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.RequestHandlers.*;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.*;
import tgm.hit.rtn.dlock.protocol.responses.Response;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.LinkedList;
/**
 * @author Ari Michael Ayvazyan
 * @version 9.11.2012
 */
//TODO This class needs a cleanup
public class ThreadedConnection extends Thread implements RTNConnection{

    private ObjectInputStream in;
    private Socket connection;
    private String serverName = "";
    private PeerManager peerManager;
    private LinkedList<RequestListener> requestListener;


    public ThreadedConnection(Socket sc)//allows multiple connections
    {
        this.connection = sc;
        initializeRequestHandlers();
    }

    private void initializeRequestHandlers() {
        addRequestHandler(ByeRequestHandler.INSTANCE);
        addRequestHandler(GetPeerListRequestHandler.INSTANCE);
        addRequestHandler(HalloRequestHandler.INSTANCE);
        addRequestHandler(LockRequestHandler.INSTANCE);
        addRequestHandler(UnlockRequestHandler.INSTANCE);
    }

    public void run() {
        startRunning();
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
        System.out.println(serverName + " - " + new Date(System.currentTimeMillis()).toString() + " : " + message);
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
                Request req = (Request) in.readObject();
                showMessage("Nachricht Empfangen ");
                handleRequest(req);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("Invalid Input");
                errors++;
                if (errors > 20) break; //stop this connection after 20 errors
            }
        } while (true);
    }
    public Peer getPartner(){
        Peer partner=new Peer(connection.getPort(),connection.getInetAddress().toString());
        return partner;
    }

    /**
     * Adds a RequestListener to listen for Requests.
     * @param rq New request listener.
     */
    public void addRequestHandler(RequestListener rq){
        if(requestListener == null) requestListener = new LinkedList<RequestListener>();
        requestListener.add(rq);
    }
    /**
     * Handles the requests
     * @param req Request to be handled.
     */
    private void handleRequest(Request req) {
        for(RequestListener handler:this.requestListener){
            handler.handleRequest(req,this);
        }
    }

    public void answer(Response response){
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
