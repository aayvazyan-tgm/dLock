package tgm.hit.rtn.dlock.protocol.responses;

import java.io.Serializable;

public class ObjectIsLocked extends Response implements Serializable {
    public static final String OBJECT_IS_LOCKED_MESSAGE = "OBJECT_IS_LOCKED";
    public String object;

    public ObjectIsLocked(String object) {
        this.msg = OBJECT_IS_LOCKED_MESSAGE;
        this.object = object;
    }
}
