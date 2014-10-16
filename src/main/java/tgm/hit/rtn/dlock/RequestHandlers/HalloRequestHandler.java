package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.Hallo;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.Welcome;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class HalloRequestHandler implements RequestListener {
    /** contains a instance of this class */
    private static HalloRequestHandler instance;

    /** This class should not be instanced manually. */
    private HalloRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Hallo){
            Hallo halloReq=(Hallo)request;
            threadedConnection.answer(new Welcome());
        }
    }

    /**
     * Static factory
     * @return A instance for for HalloRequestHandler
     */
    public static HalloRequestHandler getInstance() {
        if(instance == null) {
            instance = new HalloRequestHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(HalloRequestHandler newInstance) {
        instance = newInstance;
    }
}
