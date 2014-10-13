package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Bye;
import tgm.hit.rtn.dlock.protocol.requests.Hallo;
import tgm.hit.rtn.dlock.protocol.requests.Request;
import tgm.hit.rtn.dlock.protocol.responses.Welcome;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class HalloRequestHandler implements RequestListener {
    public final static HalloRequestHandler INSTANCE = new HalloRequestHandler();
    private HalloRequestHandler(){};
    @Override
    public void handleRequest(Request request, ThreadedConnection threadedConnection) {
        if(request instanceof Hallo){
            Hallo byeReq=(Hallo)request;
            threadedConnection.answer(new Welcome());
        }
    }
}
