package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.GetPeerList;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class GetPeerListRequestHandler implements RequestListener {
    public final static GetPeerListRequestHandler INSTANCE = new GetPeerListRequestHandler();
    private GetPeerListRequestHandler(){};
    @Override
    public void handleRequest(Request request, ThreadedConnection threadedConnection) {
        if(request instanceof GetPeerList){
            GetPeerList byeReq=(GetPeerList)request;
            PeerList response=new PeerList();
            response.peers= threadedConnection.getPeerManager().getPeers();
            threadedConnection.answer(response);
        }
    }
}
