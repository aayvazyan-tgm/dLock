package tgm.hit.rtn.dlock;

import tgm.hit.rtn.dlock.DLockClient;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;

import java.util.ArrayList;

/**
 * @author Ari Michael Ayvazyan
 * @version 12.10.2014
 */
public class LinkedListPeerManager implements PeerManager{
    private ArrayList<Peer> peers;

    @Override
    public void addPeer(Peer peer) {
        this.peers.add(peer);
    }

    @Override
    public Peer[] getPeers() {
        return peers.toArray(new Peer[peers.size()]);
    }

    @Override
    public boolean removePeer(Peer peer) {
        return this.peers.remove(peer);
    }

    @Override
    public void setClient(DLockClient client) {

    }
}
