package tgm.hit.rtn.dlock;

import java.util.concurrent.TimeUnit;
/**
 * @author Ari Michael Ayvazyan
 * @version 13.10.2014
 */
public class DLock implements GotLock {

    private PeerManager peerManager;

    private DLockClient dLockClient;

    /**
     * Creates a DLock using a default configuration.
     * 
     */
    public DLock(){
        this.peerManager=new LinkedListPeerManager();
        this.dLockClient=new UDPMulticastLockClient();
    }
    public DLock(PeerManager peerManager, DLockClient dLockClient) {
        this.peerManager = peerManager;
        this.dLockClient = dLockClient;

    }

    /**
     *  Locks while the call is being executed. (blocking)
     *  Waits until The System is not locked.
     *
     *  @param call the call to be executed while locked
     *  @param params the params for the call
     *  @return returns the return value of call
     *  @throws java.lang.InterruptedException if the thread was interrupted while waiting for a lock
     */
    public Object lockWhile(Callback call, Object... params) throws InterruptedException {
        lock();
        Object returnVal=call.run(params);
        unlock();
        return returnVal;
    }

    /**
     * Acquires the lock.
     * If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired.
     */
    public void lock() throws InterruptedException {
        while (!tryLock()) {
            Thread.sleep(Settings.RETRY_TIME_NANO);
        }
    }

    /**
     * Acquires the lock only if it is free at the time of invocation.
     * <p/>
     * Acquires the lock if it is available and returns immediately with the value true. If the lock is not available then this method will return immediately with the value false.
     *
     * @return Tries to Acquires the lock if it is available and returns immediately with the value true.
     */
    public boolean tryLock() {
        //TODO IMPLEMENT
        return false;
    }


    /**
     * Acquires the lock if it is free within the given waiting time and the current thread has not been interrupted.
     * <p/>
     * If the lock is available this method returns immediately with the value true. If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of three things happens:
     * <p/>
     * The lock is acquired by the current thread; or
     * Some other thread interrupts the current thread, and interruption of lock acquisition is supported; or
     * The specified waiting time elapses
     * <p/>
     * If the lock is acquired then the value true is returned.
     * <p/>
     * If the specified waiting time elapses then the value false is returned. If the time is less than or equal to zero, the method will not wait at all.
     *
     * @param time - the maximum time to wait for the lock
     * @param unit - the time unit of the time argument
     * @return true if the lock was acquired and false if the waiting time elapsed before the lock was acquired
     * @throws InterruptedException if the current thread is interrupted while acquiring the lock (and interruption of lock acquisition is supported)
     */
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        //TODO a thread would come handy
        //Thread.sleep(unit.toMillis(time));
        dLockClient.lock();
        return false;
    }

    /**
     * Releases the lock.
     */
    public void unlock() {
        dLockClient.unlock();
    }


    /**
     * @see tgm.hit.rtn.dlock.GotLock#gotLock()
     */
    public boolean gotLock() {
        //TODO RETURN the current lock staus
        return false;
    }
}
