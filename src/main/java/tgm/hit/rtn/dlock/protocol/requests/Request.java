package tgm.hit.rtn.dlock.protocol.requests;

import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.PackageType;

public abstract class Request extends RTNPackage {
    /**
     * Sets RTNPackage#type to PackageType.REQUEST
     */
    public Request() {
        this.type = PackageType.REQUEST;
    }
}
