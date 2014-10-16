package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Bye extends Request implements Serializable {

    public String msg = "BYE";

    @Override
    public String getMessage() {
        return msg;
    }
}
