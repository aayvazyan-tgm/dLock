package tgm.hit.rtn.dlock.handlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.transport.RTNConnection;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.GetPeerList;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class GetPeerListPackageHandler implements PackageListener {
    /** contains a instance of this class */
    private static GetPeerListPackageHandler instance = new GetPeerListPackageHandler();

    /** This class should not be instanced manually. */
    private GetPeerListPackageHandler(){}
    @Override
    public void handlePackage(RTNPackage pkg, RTNConnection threadedConnection) {
        if(pkg.type == PackageType.REQUEST
                && pkg.msg.equals(GetPeerList.GET_PEER_LIST_MESSAGE)) {
            // response
            Peer[] peers = threadedConnection.getPeerManager().getPeers();
            PeerList response = new PeerList(peers);
            threadedConnection.answer(response);
        }
    }

    /**
     * Static factory
     * @return A instance for for GetPeerListRequestHandler
     */
    public static GetPeerListPackageHandler getInstance() {
        if(instance == null) {
            instance = new GetPeerListPackageHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(GetPeerListPackageHandler newInstance) {
        instance = newInstance;
    }
}
