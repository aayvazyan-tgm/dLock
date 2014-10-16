package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.GetPeerList;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class GetPeerListRequestHandler implements RequestListener {
    /* contains a instance of this class */
    public final static GetPeerListRequestHandler INSTANCE = new GetPeerListRequestHandler();
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
}
