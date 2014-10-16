package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.Hallo;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.Welcome;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class HalloRequestHandler implements RequestListener {
    /* contains a instance of this class */
    public final static HalloRequestHandler INSTANCE = new HalloRequestHandler();
    /** This class should not be instanced manually. */
    private HalloRequestHandler(){};
    @Override
    public void handleRequest(Request request, RTNConnection threadedConnection) {
        if(request instanceof Hallo){
            Hallo halloReq=(Hallo)request;
            threadedConnection.answer(new Welcome());
        }
    }
}
