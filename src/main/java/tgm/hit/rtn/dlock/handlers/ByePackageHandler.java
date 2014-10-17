package tgm.hit.rtn.dlock.handlers;

import tgm.hit.rtn.dlock.transport.RTNConnection;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.Bye;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class ByePackageHandler implements PackageListener {
    /** contains a instance of this class */
    private static ByePackageHandler instance;

    /** This class should not be instanced manually. */
    private ByePackageHandler(){}

    @Override
    public void handlePackage(RTNPackage pkg, RTNConnection threadedConnection) {
        if(pkg.type == PackageType.REQUEST
                && pkg.msg.equals(Bye.BYE_MESSAGE)) {
            // remove peer
            threadedConnection.getPeerManager()
                    .removePeer(threadedConnection.getPartner());
        }
    }

    /**
     * Static factory
     * @return A instance for for ByeRequestHandler
     */
    public static ByePackageHandler getInstance() {
        if(instance == null) {
            instance = new ByePackageHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(ByePackageHandler newInstance) {
        instance = newInstance;
    }
}
