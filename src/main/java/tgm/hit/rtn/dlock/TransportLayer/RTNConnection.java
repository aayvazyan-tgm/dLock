package tgm.hit.rtn.dlock.TransportLayer;

import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.protocol.responses.Response;

/**
 * This class defines the resources, that are required by the RequestHandlers.
 *
 * @author Ari
 * @author Jakob Klepp
 * @version 13.10.2014
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
     * Sends a Response to the current partner.
     *
     * @param response Response to be send.
     */
    void answer(Response response);
}
