package tgm.hit.rtn.dlock.protocol;

import java.io.Serializable;

public abstract class RTNPackage implements Serializable {
    /**
     * Type of the Package
     */
    public PackageType type = null;

    /**
     * The Package payload.
     */
    public String msg = null;
}
