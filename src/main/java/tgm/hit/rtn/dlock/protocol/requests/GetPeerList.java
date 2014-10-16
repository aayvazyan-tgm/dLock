package tgm.hit.rtn.dlock.protocol.requests;

import java.io.Serializable;

public class GetPeerList extends Request implements Serializable{
    public static final String GET_PEER_LIST_MESSAGE = "GET_PEER_LIST";
    public GetPeerList() {
        this.msg = GET_PEER_LIST_MESSAGE;
    }
}
