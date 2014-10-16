package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Hallo extends Request implements Serializable {

    public String msg = "HALLO";

    @Override
    public String getMessage() {
        return msg;
    }
}
