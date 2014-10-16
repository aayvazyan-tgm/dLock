package tgm.hit.rtn.dlock.TransportLayer.UDPLockServer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.packageHandlers.*;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.responses.Response;

import java.util.LinkedList;

//TODO no Server functionality is implemented yet
public class UDPLockClient implements RTNConnection{

    private PeerManager peerManager;
    private LinkedList<PackageListener> packageListener;


    public UDPLockClient()//allows multiple connections
    {
        initializeRequestHandlers();
    }

    private void initializeRequestHandlers() {
        addRequestHandler(ByePackageHandler.getInstance());
        addRequestHandler(GetPeerListPackageHandler.getInstance());
        addRequestHandler(HalloPackageHandler.getInstance());
        addRequestHandler(LockPackageHandler.getInstance());
        addRequestHandler(UnlockPackageHandler.getInstance());
    }

    public Peer getPartner(){
        //TODO IMPLEMENT
        return null;
    }

    /**
     * Adds a RequestListener to listen for Requests.
     * @param rq New request listener.
     */
    public void addRequestHandler(PackageListener rq){
        if(packageListener == null) packageListener = new LinkedList<PackageListener>();
        packageListener.add(rq);
    }
    /**
     * Handles the requests
     * @param req Request to be handled.
     */
    private void handlePackage(RTNPackage req) {
        for(PackageListener handler:this.packageListener){
            handler.handlePackage(req, this);
        }
    }

    public void answer(Response response){
        //TODO send the response
    }

    public PeerManager getPeerManager() {
        return peerManager;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }
}
