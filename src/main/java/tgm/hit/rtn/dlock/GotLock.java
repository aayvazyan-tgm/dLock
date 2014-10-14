package tgm.hit.rtn.dlock;

public interface GotLock {
    /**
     * Returns the current lock status locked/not locked
     * @return Returns true if currently holding a lock, false otherwise.
     */
	public boolean gotLock();
}
