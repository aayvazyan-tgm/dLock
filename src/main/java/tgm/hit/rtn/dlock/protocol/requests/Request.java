package tgm.hit.rtn.dlock.protocol.requests;

import tgm.hit.rtn.dlock.protocol.*;

public abstract class Request implements tgm.hit.rtn.dlock.protocol.Package {

    private PackageType type = PackageType.REQUEST;

    @Override
    public PackageType getPackageType() {
        return type;
    }
}
