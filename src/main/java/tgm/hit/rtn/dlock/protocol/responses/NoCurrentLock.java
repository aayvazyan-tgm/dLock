package tgm.hit.rtn.dlock.protocol.responses;

import java.io.Serializable;

public class NoCurrentLock extends Response implements Serializable {

    public String msg = "NO_CURRENT_LOCK";

    @Override
    public String getMessage() {
        return msg;
    }
}
