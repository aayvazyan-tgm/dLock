package tgm.hit.rtn.dlock;

import java.util.concurrent.TimeUnit;

public class DLock implements GotLock {

	private PeerManager peerManager;

	private DLockClient dLockClient;

	public DLock(PeerManager peerManager, DLockClient dLockClient) {
        this.peerManager = peerManager;
        this.dLockClient = dLockClient;

    }

	public void lockWhile(Callback call, Object... params) {
        lockRequest();
        call.run(params);
        unlock();
	}

    /**
     * Acquires the lock.
     * If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired.
     */
	public void lock() throws InterruptedException {
        while(!lockRequest()){
            Thread.sleep(Settings.RETRY_TIME_NANO);
        }
	}

    /**
     * Acquires the lock only if it is free at the time of invocation.

     Acquires the lock if it is available and returns immediately with the value true. If the lock is not available then this method will return immediately with the value false.
     * @return Tries to Acquires the lock if it is available and returns immediately with the value true.
     */
	public boolean tryLock() {
		return lockRequest();
	}

    /**
     *
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        dLockClient.lock();
        Thread.sleep(unit.toMillis(time));
        return false;
	}

	public void unlock() {
        dLockClient.unlock();
	}


	/**
	 * @see tgm.hit.rtn.dlock.GotLock#gotLock()
	 */
	public void gotLock() {

	}

    private boolean lockRequest() {

        return false;
    }
}
