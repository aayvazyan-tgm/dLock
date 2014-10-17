package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.PackageType;

public abstract class Response extends RTNPackage {
    /**
     * Sets RTNPackage#type to PackageType.RESPONSE
     */
    public Response() {
        this.type = PackageType.RESPONSE;
    }
}
