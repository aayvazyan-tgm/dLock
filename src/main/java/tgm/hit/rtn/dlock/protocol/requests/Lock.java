package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Lock extends Request implements Serializable{
    public static final String LOCK_MESSAGE = "LOCK";
    public String object;
    public Lock(String object) {
        this.object = object;
        this.msg = LOCK_MESSAGE;
    }
}
