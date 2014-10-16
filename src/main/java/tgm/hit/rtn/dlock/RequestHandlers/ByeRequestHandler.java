package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class ByeRequestHandler implements RequestListener {
    /** contains a instance of this class */
    private static ByeRequestHandler instance;

    /** This class should not be instanced manually. */
    private ByeRequestHandler(){}

    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Bye){
            Bye byeReq=(Bye)request;
            threadedConnection.getPeerManager().removePeer(threadedConnection.getPartner());
        }
    }

    /**
     * Static factory
     * @return A instance for for ByeRequestHandler
     */
    public static ByeRequestHandler getInstance() {
        if(instance == null) {
            instance = new ByeRequestHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(ByeRequestHandler newInstance) {
        instance = newInstance;
    }
}
