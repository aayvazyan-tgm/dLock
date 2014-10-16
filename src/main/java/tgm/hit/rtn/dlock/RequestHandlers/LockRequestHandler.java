package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.TransportLayer.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Lock;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class LockRequestHandler implements RequestListener {
    /* contains a instance of this class */
    public final static LockRequestHandler INSTANCE = new LockRequestHandler();
    /** This class should not be instanced manually. */
    private LockRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Lock){
            Lock lockReq=(Lock)request;
            //TODO return locked/not locked status!
        }
    }
}
