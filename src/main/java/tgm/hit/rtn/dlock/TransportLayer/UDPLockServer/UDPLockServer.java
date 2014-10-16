package tgm.hit.rtn.dlock.TransportLayer.UDPLockServer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.packageHandlers.*;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.responses.Response;

import java.util.LinkedList;

//TODO no Server functionality is implemented yet
public class UDPLockServer implements RTNConnection {

    private PeerManager peerManager;
    private LinkedList<PackageListener> packageListener;


    public UDPLockServer()//allows multiple connections
    {
        initializeRequestHandlers();
    }

    private void initializeRequestHandlers() {
        addPackageHandler(ByePackageHandler.getInstance());
        addPackageHandler(GetPeerListPackageHandler.getInstance());
        addPackageHandler(HalloPackageHandler.getInstance());
        addPackageHandler(LockPackageHandler.getInstance());
        addPackageHandler(UnlockPackageHandler.getInstance());
    }

    public Peer getPartner() {
        //TODO IMPLEMENT
        return null;
    }

    /**
     * Adds a RequestListener to listen for Requests.
     *
     * @param pl New package listener.
     */
    public void addPackageHandler(PackageListener pl) {
        if(packageListener == null) packageListener = new LinkedList<PackageListener>();
        packageListener.add(pl);
    }

    /**
     * Handles the requests
     *
     * @param pkg Package to be handled.
     */
    private void handlePackage(RTNPackage pkg) {
        for(PackageListener handler:this.packageListener){
            handler.handlePackage(pkg, this);
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
