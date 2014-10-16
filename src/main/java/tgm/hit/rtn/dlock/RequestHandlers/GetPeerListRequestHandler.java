package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.GetPeerList;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class GetPeerListRequestHandler implements RequestListener {
    /** contains a instance of this class */
    private static GetPeerListRequestHandler instance = new GetPeerListRequestHandler();

    /** This class should not be instanced manually. */
    private GetPeerListRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof GetPeerList){
            GetPeerList peerReq=(GetPeerList)request;
            PeerList response=new PeerList();
            response.peers= threadedConnection.getPeerManager().getPeers();
            threadedConnection.answer(response);
        }
    }

    /**
     * Static factory
     * @return A instance for for GetPeerListRequestHandler
     */
    public static GetPeerListRequestHandler getInstance() {
        if(instance == null) {
            instance = new GetPeerListRequestHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(GetPeerListRequestHandler newInstance) {
        instance = newInstance;
    }
}
