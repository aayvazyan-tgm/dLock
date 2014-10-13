package tgm.hit.rtn.dlock.TransportLayer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;
import tgm.hit.rtn.dlock.protocol.responses.Response;

/**
 * @author Ari
 * @version 13.10.2014
 */
public interface RTNConnection {

    PeerManager getPeerManager();

    Peer getPartner();

    void answer(Response response);
}
