package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Hallo extends Request implements Serializable{
    public static final String HALLO_MESSAGE = "HALLO";
    public Hallo() {
        this.msg = HALLO_MESSAGE;
    }
}
