package tgm.hit.rtn.dlock.RequestHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.requests.Request;

/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public interface RequestListener {
    /**
     * Handles the received Request
     * @param request
     * @param threadedRTNConnection
     */
    public void handleRequest(Request request, RTNConnection threadedRTNConnection);
}
