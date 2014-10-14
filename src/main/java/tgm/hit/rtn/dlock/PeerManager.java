package tgm.hit.rtn.dlock;

public interface PeerManager {
    /**
     * Adds a Peer to the Peer manager
     * @param peer the Peer to add
     */
	public void addPeer(Peer peer);

    /**
     * gets all stored Peers
     * @return all stored Peers
     */
	public Peer[] getPeers();

    /**
     * removes a Peer by its Object
     * @param peer the peer to remove
     * @return true if removed, false otherwise
     */
	public boolean removePeer(Peer peer);

    /**
     * sets the DLockClient that communicates with the Peers
     * @param client the client to set
     */
	public void setClient(DLockClient client);

}
