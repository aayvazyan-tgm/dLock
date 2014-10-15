package tgm.hit.rtn.dlock.protocol.responses;

import java.io.Serializable;

public class Welcome extends Response implements Serializable {

	public String msg = "WELCOME";

    @Override
    public String getMessage() {
        return msg;
    }
}
