package tgm.hit.rtn.dlock.packageHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.Hallo;
import tgm.hit.rtn.dlock.protocol.responses.Welcome;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class HalloPackageHandler implements PackageListener {
    /** contains a instance of this class */
    private static HalloPackageHandler instance;

    /** This class should not be instanced manually. */
    private HalloPackageHandler(){}

    @Override
    public void handlePackage(RTNPackage pkg, RTNConnection threadedConnection) {
        if(pkg.type == PackageType.REQUEST
                && pkg.msg.equals(Hallo.HALLO_MESSAGE)){
            // welcome response
            threadedConnection.answer(new Welcome());
        }
    }

    /**
     * Static factory
     * @return A instance for for HalloRequestHandler
     */
    public static HalloPackageHandler getInstance() {
        if(instance == null) {
            instance = new HalloPackageHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(HalloPackageHandler newInstance) {
        instance = newInstance;
    }
}
