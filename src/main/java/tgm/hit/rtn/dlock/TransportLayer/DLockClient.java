package tgm.hit.rtn.dlock.TransportLayer;

/**
 * this Interface defines the requests that can be sent to the network.
 */
public interface DLockClient {
    /**
     * sends a hello msg
     */
	public abstract void hallo();

    /**
     * sends a bye msg
     */
	public abstract void bye();

    /**
     * sends a lock request
     */
	public abstract void lock();

    /**
     * sends a unlock msg
     */
	public abstract void unlock();

    /**
     * sends the current Peer list
     */
	public abstract void getPeerList();

}
