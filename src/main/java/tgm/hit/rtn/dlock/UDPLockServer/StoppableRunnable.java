package tgm.hit.rtn.dlock.UDPLockServer;

/**
 * @author Ari Michael Ayvazyan
 * @version 12.10.2014
 */
public interface StoppableRunnable extends Runnable {
    void stop();
}
