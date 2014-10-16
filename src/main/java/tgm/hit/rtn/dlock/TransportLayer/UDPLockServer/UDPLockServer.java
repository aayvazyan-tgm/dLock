package tgm.hit.rtn.dlock.TransportLayer.UDPLockServer;

import tgm.hit.rtn.dlock.PeerManagers.Peer;
import tgm.hit.rtn.dlock.PeerManagers.PeerManager;
import tgm.hit.rtn.dlock.RequestHandlers.*;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.*;
import tgm.hit.rtn.dlock.protocol.responses.Response;

import java.util.LinkedList;

//TODO no Server functionality is implemented yet
public class UDPLockServer implements RTNConnection {

    private PeerManager peerManager;
    private LinkedList<RequestListener> requestListener;


    public UDPLockServer()//allows multiple connections
    {
        initializeRequestHandlers();
    }

    private void initializeRequestHandlers() {
        addRequestHandler(ByeRequestHandler.INSTANCE);
        addRequestHandler(GetPeerListRequestHandler.INSTANCE);
        addRequestHandler(HalloRequestHandler.INSTANCE);
        addRequestHandler(LockRequestHandler.INSTANCE);
        addRequestHandler(UnlockRequestHandler.INSTANCE);
    }

    public Peer getPartner() {
        //TODO IMPLEMENT
        return null;
    }

    /**
     * Adds a RequestListener to listen for Requests.
     *
     * @param rq New request listener.
     */
    public void addRequestHandler(RequestListener rq) {
        if (requestListener == null) requestListener = new LinkedList<RequestListener>();
        requestListener.add(rq);
    }

    /**
     * Handles the requests
     *
     * @param req Request to be handled.
     */
    private void handleRequest(Request req) {
        for (RequestListener handler : this.requestListener) {
            handler.handleRequest(req, this);
        }
    }

    public void answer(Response response) {
        //TODO send the response
    }

    public PeerManager getPeerManager() {
        return peerManager;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }
}
