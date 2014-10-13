package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.requests.Unlock;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class UnlockRequestHandler implements RequestListener {
    public final static UnlockRequestHandler INSTANCE = new UnlockRequestHandler();
    private UnlockRequestHandler(){};
    @Override
    public void handleRequest(Request request, ThreadedConnection threadedConnection) {
        if(request instanceof Unlock){
            Unlock byeReq=(Unlock)request;
            //Because of a "Is the system locked?" request. unlock requests do not need to be handled
        }
    }
}
