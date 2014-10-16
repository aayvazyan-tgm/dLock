package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.Lock;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class LockRequestHandler implements RequestListener {
    /** contains a instance of this class */
    private static LockRequestHandler instance;

    /** This class should not be instanced manually. */
    private LockRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Lock){
            Lock lockReq=(Lock)request;
            //TODO return locked/not locked status!
        }
    }

    /**
     * Static factory
     * @return A instance for for LockRequestHandler
     */
    public static LockRequestHandler getInstance() {
        if(instance == null) {
            instance = new LockRequestHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(LockRequestHandler newInstance) {
        instance = newInstance;
    }
}
