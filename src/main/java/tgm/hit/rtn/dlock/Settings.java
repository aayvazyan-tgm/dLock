package tgm.hit.rtn.dlock;

//This could probably be implemented in a better way
public class Settings {
    /** Wait RETRY_TIME_NANO + a random value between 0 and VARIANCE until retrying to acquire a lock */
    public final static long RETRY_TIME_NANO = 4000;
    /** Wait RETRY_TIME_NANO + a random value between 0 and VARIANCE until retrying to acquire a lock */
    public static int VARIANCE = 1000;
}
