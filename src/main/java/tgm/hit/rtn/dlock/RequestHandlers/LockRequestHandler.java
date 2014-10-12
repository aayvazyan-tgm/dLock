package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Lock;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class LockRequestHandler implements RequestListener {
    public final static LockRequestHandler INSTANCE = new LockRequestHandler();
    private LockRequestHandler(){};
    @Override
    public void handleRequest(Request request, ThreadedConnection threadedConnection) {
        if(request instanceof Lock){
            Lock byeReq=(Lock)request;
            //Because of a "IsLocked?" request. lock requests do not need to be handled
        }
    }
}
