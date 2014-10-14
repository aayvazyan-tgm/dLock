package tgm.hit.rtn.dlock.TransportLayer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;
import tgm.hit.rtn.dlock.protocol.responses.Response;

/**
 * @author Ari
 * @version 13.10.2014
 * This class defines the resources, that are required by the RequestHandlers.
 */
public interface RTNConnection {

    /**
     * Getter for property 'peerManager'.
     *
     * @return Value for property 'peerManager'.
     */
    PeerManager getPeerManager();

    /**
     * Getter for property 'partner'.
     *
     * @return Value for property 'partner'.
     */
    Peer getPartner();

    /**
     *  sends a Response to the current partner.
     * @param response
     */
    void answer(Response response);
}
