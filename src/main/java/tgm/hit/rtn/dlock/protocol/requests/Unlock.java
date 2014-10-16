package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Unlock extends Request implements Serializable {

    public String msg = "UNLOCK";

    @Override
    public String getMessage() {
        return msg;
    }
}
