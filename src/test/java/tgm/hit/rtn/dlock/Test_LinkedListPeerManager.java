package tgm.hit.rtn.dlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for LinkedListPeerManager
 */
public class Test_LinkedListPeerManager {

    LinkedListPeerManager linkedListPeerManager;
    Peer[] peers;

    @Before public void prepare() {
        linkedListPeerManager = new LinkedListPeerManager();
        peers = new Peer[]{
                new Peer(123, "host1"),
                new Peer(321, "127.0.0.1"),
                new Peer(6666, "example.com")
        };
        for(Peer peer: peers) {
            linkedListPeerManager.addPeer(peer);
        }
    }

    //
    // Tests with normal behavior
    //
    @Test public void test_getPeer_checkExpectedPeers() {
        Assert.assertSame(peers.length, linkedListPeerManager.getPeers().length);
        Assert.assertArrayEquals(peers, linkedListPeerManager.getPeers());
    }

    @Test public void test_addPeer_addAPeer() {
        Peer myPeer = new Peer(4444, "myPeer");

        linkedListPeerManager.addPeer(myPeer);

        boolean inArray = false;
        for (Peer peer: linkedListPeerManager.getPeers()) {
            if(peer.equals(myPeer)) {
                inArray = true;
            }
        }

        Assert.assertTrue("After adding a peer the peer should be in the array returned by #getPeers()",
                inArray);
    }

    @Test public void test_removePeer_removeAPeer() {
        linkedListPeerManager.removePeer(peers[0]);

        boolean inArray = false;
        for (Peer peer: linkedListPeerManager.getPeers()) {
            if(peer.equals(peers[0])) {
                inArray = true;
            }
        }

        Assert.assertFalse("After removing a peer the peer should not be in the array returned by #getPeers()",
                inArray);
    }

    //
    // Check special requirements
    //
    @Test public void test__noDuplicates() {
        Peer doublePeer = new Peer(666, "doublePeer");
        linkedListPeerManager.addPeer(doublePeer);
        linkedListPeerManager.addPeer(doublePeer);

        int doublePeerCount = 0;
        for(Peer peer: linkedListPeerManager.getPeers()) {
            if(peer.getHost().equals(doublePeer.getHost()) &&
                    peer.getPort() == doublePeer.getPort()) {
                doublePeerCount ++;
            }
        }

        Assert.assertEquals("A peer should occur only once.", doublePeerCount, 1);
    }

    @Test public void test_addPeer_nullShouldNotChangePeerCount() {
        linkedListPeerManager.addPeer(null);
        Assert.assertEquals("When adding null as a peer, null should not be added as a new peer",
                linkedListPeerManager.getPeers().length,
                peers.length);
    }

    @Test public void test_removePeer_nullShouldNotChangePeerCount() {
        linkedListPeerManager.removePeer(null);
        Assert.assertEquals("When remove null as a peer, null should change the number of peers",
                linkedListPeerManager.getPeers().length,
                peers.length);
    }
}
