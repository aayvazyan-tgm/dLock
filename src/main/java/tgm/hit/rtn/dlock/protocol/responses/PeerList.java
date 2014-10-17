package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.Peer;

import java.io.Serializable;

public class PeerList extends Response implements Serializable{
    public static final String PEER_LIST_MESSAGE = "PEER_LIST";
	public Peer[] peers;

    public PeerList(Peer[] peers) {
        this.msg = PEER_LIST_MESSAGE;
        this.peers = peers;
    }
}
