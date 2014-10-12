package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.UDPLockServer.ThreadedConnection;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public interface RequestListener {
    public void handleRequest(Request request, ThreadedConnection threadedConnection);
}
