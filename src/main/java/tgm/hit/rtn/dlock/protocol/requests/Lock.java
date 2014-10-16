package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class Lock extends Request implements Serializable{

	public String msg = "LOCK";
    @Override
    public String getMessage() {
        return msg;
    }
}
