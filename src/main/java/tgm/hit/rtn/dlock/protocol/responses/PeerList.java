package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.PeerManagers.Peer;

import java.io.Serializable;

public class PeerList extends Response implements Serializable {

    public String msg = "PEER_LIST";

    public Peer[] peers;

    @Override
    public String getMessage() {
        return msg;
    }
}
