package tgm.hit.rtn.dlock.protocol.responses;

import java.io.Serializable;

public class NoCurrentLock extends Response implements Serializable {
    public static final String NO_CURRENT_LOCK_MESSAGE = "NO_CURRENT_LOCK";
    public String object;
    public NoCurrentLock(String object) {
        this.object = object;
        this.msg = NO_CURRENT_LOCK_MESSAGE;
    }
}
