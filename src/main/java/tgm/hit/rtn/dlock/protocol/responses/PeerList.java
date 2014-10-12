package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.Peer;

import java.io.Serializable;
import java.util.List;

public class PeerList extends Response implements Serializable{

	public String msg = "PEER_LIST";

	public Peer[] peers;

}
