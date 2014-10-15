package tgm.hit.rtn.dlock;

import tgm.hit.rtn.dlock.TransportLayer.DLockClient;

import java.util.ArrayList;

/**
 * @author Ari Michael Ayvazyan
 * @version 12.10.2014
 */
public class LinkedListPeerManager implements PeerManager{
    private ArrayList<Peer> peers;

    public LinkedListPeerManager(){
        this.peers=new ArrayList<Peer>();
    }

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
        for (Peer storedPeer:peers) {
            if(peer.equals(storedPeer))this.peers.remove(storedPeer);
        }
        return this.peers.remove(peer);
    }

    @Override
    public void setClient(DLockClient client) {
        //Do we really need this?
    }
}
