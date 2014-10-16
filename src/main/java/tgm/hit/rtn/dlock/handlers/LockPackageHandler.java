package tgm.hit.rtn.dlock.handlers;

import tgm.hit.rtn.dlock.transport.RTNConnection;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.Lock;

/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class LockPackageHandler implements PackageListener {
    /** contains a instance of this class */
    private static LockPackageHandler instance;

    /** This class should not be instanced manually. */
    private LockPackageHandler(){}

    @Override
    public void handlePackage(RTNPackage pkg, RTNConnection threadedConnection) {
        if(pkg.type == PackageType.REQUEST
                && pkg.msg.equals(Lock.LOCK_MESSAGE)){
            Lock lockReq=(Lock)pkg;
            //TODO return locked/not locked status!
        }
    }

    /**
     * Static factory
     * @return A instance for for LockRequestHandler
     */
    public static LockPackageHandler getInstance() {
        if(instance == null) {
            instance = new LockPackageHandler();
        }
        return instance;
    }

    /**
     * Instance setter for testing (mocking) purpose.
     */
    @Deprecated
    public static void setInstance(LockPackageHandler newInstance) {
        instance = newInstance;
    }
}
