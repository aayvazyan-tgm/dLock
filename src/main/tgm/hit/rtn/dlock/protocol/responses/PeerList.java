package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.Peer;

import java.util.List;

public class PeerList extends Response {

	public String msg = "PEER_LIST";

	public List<Peer> peers;

}
