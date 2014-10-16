package tgm.hit.rtn.dlock.packageHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.RTNPackage;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public interface PackageListener {
    /**
     * Handles the received Request
     *
     * @param pkg Package of question.
     * @param threadedRTNConnection Connection the request arrived on.
     */
    public void handlePackage(RTNPackage pkg,
                              RTNConnection threadedRTNConnection);
}
