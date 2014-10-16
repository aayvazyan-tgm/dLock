package tgm.hit.rtn.dlock.packageHandlers;

import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.Unlock;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class UnlockPackageHandler implements PackageListener {
    /** contains a instance of this class */
    private static UnlockPackageHandler instance;

    /** This class should not be instanced manually. */
    private UnlockPackageHandler(){};

    @Override
    public void handlePackage(RTNPackage pkg, RTNConnection threadedConnection) {
        if(pkg.type == PackageType.REQUEST
                && pkg.msg.equals(Unlock.UNLOCK_MESSAGE)){
            Unlock unlockReq=(Unlock)pkg;
            //Because of a "Is the system locked?" request. unlock requests do not need to be handled
        }
    }

    /**
     * Static factory
     * @return A instance for for UnlockRequestHandler
     */
    public static UnlockPackageHandler getInstance() {
        if(instance == null) {
            instance = new UnlockPackageHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(UnlockPackageHandler newInstance) {
        instance = newInstance;
    }
}
