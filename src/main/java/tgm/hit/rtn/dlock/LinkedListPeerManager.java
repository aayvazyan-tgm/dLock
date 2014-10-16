package tgm.hit.rtn.dlock;

import tgm.hit.rtn.dlock.TransportLayer.DLockClient;

import java.util.LinkedList;
import java.util.List;

/**
 * A implementation of PeerManager, storing the Peers in
 * RAM in a LinkedList.
 *
 * @author Ari Michael Ayvazyan
 * @author Jakob Klepp
 * @version 16.10.2014
 */
public class LinkedListPeerManager implements PeerManager{
    private List<Peer> peers;

    public LinkedListPeerManager(){
        this.peers=new LinkedList<Peer>();
    }

    /**
     * @see PeerManager#addPeer(Peer)
     */
    @Override
    public void addPeer(Peer peer) {
        this.peers.add(peer);
    }

    /**
     * @see PeerManager#getPeers()
     */
    @Override
    public Peer[] getPeers() {
        return peers.toArray(new Peer[peers.size()]);
    }

    /**
     * @see PeerManager#removePeer(Peer)
     */
    @Override
    public boolean removePeer(Peer peer) {
        for (Peer storedPeer : peers) {
            if (peer.equals(storedPeer)) this.peers.remove(storedPeer);
        }
        return this.peers.remove(peer);
    }

    /**
     * @see PeerManager#setClient(tgm.hit.rtn.dlock.TransportLayer.DLockClient)
     */
    @Override
    public void setClient(DLockClient client) {
        //Do we really need this?
    }
}
