package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.TransportLayer.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class ByeRequestHandler implements RequestListener {
    /* contains a instance of this class */
    public final static ByeRequestHandler INSTANCE = new ByeRequestHandler();
    private ByeRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Bye){
            Bye byeReq=(Bye)request;
            threadedConnection.getPeerManager().removePeer(threadedConnection.getPartner());
        }
    }
}
