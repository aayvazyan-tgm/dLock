package tgm.hit.rtn.dlock.protocol;

import java.io.Serializable;

public interface Package extends Serializable {
    /**
     * gets the type of this package
     *
     * @return the type of this package
     */
    public abstract PackageType getPackageType();

    /**
     * gets the message contained in this package
     *
     * @return the message contained in this package
     */
    public abstract String getMessage();
}
