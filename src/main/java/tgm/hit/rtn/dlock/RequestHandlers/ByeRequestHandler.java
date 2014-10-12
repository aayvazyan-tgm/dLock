package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class ByeRequestHandler implements RequestListener {
    public final static ByeRequestHandler INSTANCE = new ByeRequestHandler();
    private ByeRequestHandler(){};
    @Override
    public void handleRequest(Request request, ThreadedConnection threadedConnection) {
        if(request instanceof Bye){
            Bye byeReq=(Bye)request;
            threadedConnection.getPeerManager().removePeer(threadedConnection.getPartner());
        }
    }
}
