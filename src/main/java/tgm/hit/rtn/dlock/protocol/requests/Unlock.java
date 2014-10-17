package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Unlock extends Request implements Serializable {
    public static final String UNLOCK_MESSAGE = "UNLOCK";
    public String object;
    public Unlock(String object) {
        this.object = object;
        this.msg = UNLOCK_MESSAGE;
    }
}
