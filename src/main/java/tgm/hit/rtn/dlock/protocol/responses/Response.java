package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.protocol.*;

public abstract class Response implements tgm.hit.rtn.dlock.protocol.Package {

    private PackageType type = PackageType.RESPONSE;

    public PackageType getPackageType() {
        return type;
    }

}
