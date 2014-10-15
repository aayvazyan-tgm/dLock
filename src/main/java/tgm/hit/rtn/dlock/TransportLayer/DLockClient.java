package tgm.hit.rtn.dlock.TransportLayer;

/**
 * this Interface defines the requests that can be sent to the network.
 */
public interface DLockClient {
    /**
     * sends a hello message
     */
	public abstract void hallo();

    /**
     * sends a bye message
     */
	public abstract void bye();

    /**
     * sends a lock request
     */
	public abstract void lock();

    /**
     * sends a unlock message
     */
	public abstract void unlock();

    /**
     * sends the current Peer list
     */
	public abstract void getPeerList();

}
