package tgm.hit.rtn.dlock;

import tgm.hit.rtn.dlock.TransportLayer.DLockClient;
import tgm.hit.rtn.dlock.TransportLayer.UDPMulticastLockClient;

import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 13.10.2014
 */
public class DLock implements GotLock {

    private PeerManager peerManager;

    private DLockClient dLockClient;

    private boolean isLocked=false;

    /**
     * Creates a dLock using a default configuration.
     * 
     */
    public DLock(){
        this(new LinkedListPeerManager(),
                new UDPMulticastLockClient());
    }

    /**
     * Dependency injection constructor
     *
     * @param peerManager Object responsible for managing the peers.
     * @param dLockClient Send network requests with this object.
     */
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
     *  @throws java.lang.InterruptedException if the thread was interrupted
     *  while waiting for a lock
     */
    public Object lockWhile(Callback call, Object... params) throws InterruptedException {
        lock();
        Object returnVal = call.run(params);
        unlock();
        return returnVal;
    }

    /**
     * Acquires the lock.
     * If the lock is not available then the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until the lock has
     * been acquired.
     *
     * @throws java.lang.InterruptedException Throws when interrupted while
     * waiting for lock.
     */
    public void lock() throws InterruptedException {
        while (!tryLock()) {
            Thread.sleep(Settings.RETRY_TIME_NANO+ new Random().nextInt(Settings.VARIANCE));
        }
    }

    /**
     * <p>
     * Acquires the lock only if it is free at the time of invocation.
     * </p><p>
     * Acquires the lock if it is available and returns immediately with the
     * value true. If the lock is not available then this method will return
     * immediately with the value false.
     * </p>
     *
     * @return Tries to Acquires the lock if it is available and returns
     * immediately with the value true.
     */
    public boolean tryLock() {
        beforeTryToLock();
        boolean acquiredLock=false;

        // TODO send a lock request
        
        afterTryToLock(acquiredLock);
        return acquiredLock;
    }


    /**
     * <p>
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been interrupted.
     * </p><p>
     * If the lock is available this method returns immediately with the value
     * true. If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until one of
     * three things happens:
     * </p><p>
     * The lock is acquired by the current thread; or
     * Some other thread interrupts the current thread, and interruption of
     * lock acquisition is supported; or
     * The specified waiting time elapses
     * </p><p>
     * If the lock is acquired then the value true is returned.
     * </p><p>
     * If the specified waiting time elapses then the value false is returned.
     * If the time is less than or equal to zero, the method will not wait at
     * all.
     * </p>
     *
     * @param time - the maximum time to wait for the lock
     * @param unit - the time unit of the time argument
     * @return true if the lock was acquired and false if the waiting time
     * elapsed before the lock was acquired
     * @throws InterruptedException if the current thread is interrupted while
     * acquiring the lock (and interruption of lock acquisition is supported)
     */
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long sleepUntil = System.currentTimeMillis() + unit.toMillis(time);
        final boolean[] success = {false};
        Thread tryLockThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock();
                    success[0]=true;
                } catch (InterruptedException e) {
                    /* This exception is expected if the time runs out */
                }
            }
        });
        while(success[0] == false && System.currentTimeMillis() < sleepUntil){
            Thread.sleep(100);
        }
        if(tryLockThread.isAlive())tryLockThread.interrupt();
        return success[0];
    }

    /**
     * Releases the lock.
     */
    public void unlock() {
        setLockStatus(false);
        dLockClient.unlock();
    }


    /**
     * @see tgm.hit.rtn.dlock.GotLock#gotLock()
     */
    public boolean gotLock() {
        return isLocked;
    }

    /**
     * Sets the lock status that should be represented to the outside.
     * @param locked
     */
    private void setLockStatus(boolean locked) {
        this.isLocked=locked;
    }

    /**
     * Should be called before acquiring a lock
     */
    private void beforeTryToLock(){
        //We want to set our lock status to true BEFORE we have acquired a lock,
        //so if a other client sends a request while we try to acquire a lock he shall not receive the lock
        setLockStatus(true);
    }

    /**
     * should be called after trying to acquire a lock
     * @param acquiredLock true if a lock was acquired, false otherwise
     */
    private void afterTryToLock(boolean acquiredLock) {
        setLockStatus(acquiredLock);
    }

}
