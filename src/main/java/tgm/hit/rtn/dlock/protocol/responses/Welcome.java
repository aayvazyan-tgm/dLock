package tgm.hit.rtn.dlock.protocol.responses;

import java.io.Serializable;

public class Welcome extends Response implements Serializable {
    public static final String WELCOME_MESSAGE = "WELCOME";
    public Welcome() {
        this.msg = WELCOME_MESSAGE;
    }
}
