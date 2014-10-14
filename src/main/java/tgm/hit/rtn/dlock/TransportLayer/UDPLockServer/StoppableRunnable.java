package tgm.hit.rtn.dlock.TransportLayer.UDPLockServer;

/**
 * @author Ari Michael Ayvazyan
 * @version 12.10.2014
 */
public interface StoppableRunnable extends Runnable {
    /** stops the current work */
    void stop();
}
