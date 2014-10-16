package tgm.hit.rtn.dlock.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.protocol.PackageType;
import tgm.hit.rtn.dlock.protocol.requests.*;
import tgm.hit.rtn.dlock.protocol.responses.NoCurrentLock;
import tgm.hit.rtn.dlock.protocol.responses.ObjectIsLocked;
import tgm.hit.rtn.dlock.protocol.responses.PeerList;
import tgm.hit.rtn.dlock.protocol.responses.Welcome;

/**
 * Tests the constructors of all the protocol Packages
 */
public class Test_ProtocolConstructors {
    String testObject;
    Peer[] testPeers;

    @Before
    public void prepare() {
        testObject = "TestObject";
        testPeers = new Peer[]{
                new Peer(123, "host1"),
                new Peer(321, "127.0.0.1"),
                new Peer(6666, "example.com")
        };
    }

    //
    // requests
    //

    // Bye
    @Test public void test_Bye_type() {
        Bye pkg = new Bye();
        Assert.assertEquals(pkg.type, PackageType.REQUEST);
    }
    @Test public void test_Bye_msg() {
        Bye pkg = new Bye();
        Assert.assertEquals(pkg.msg, Bye.BYE_MESSAGE);
    }
    // GetPeerList
    @Test public void test_GetPeerList_type() {
        GetPeerList pkg = new GetPeerList();
        Assert.assertEquals(pkg.type, PackageType.REQUEST);
    }
    @Test public void test_GetPeerList_msg() {
        GetPeerList pkg = new GetPeerList();
        Assert.assertEquals(pkg.msg, GetPeerList.GET_PEER_LIST_MESSAGE);
    }
    // Hallo
    @Test public void test_Hallo_type() {
        Hallo pkg = new Hallo();
        Assert.assertEquals(pkg.type, PackageType.REQUEST);
    }
    @Test public void test_Hallo_msg() {
        Hallo pkg = new Hallo();
        Assert.assertEquals(pkg.msg, Hallo.HALLO_MESSAGE);
    }
    // Lock
    @Test public void test_Lock_type() {
        Lock pkg = new Lock(testObject);
        Assert.assertEquals(pkg.type, PackageType.REQUEST);
    }
    @Test public void test_Lock_msg() {
        Lock pkg = new Lock(testObject);
        Assert.assertEquals(pkg.msg, Lock.LOCK_MESSAGE);
    }
    @Test public void test_Lock_object() {
        Lock pkg = new Lock(testObject);
        Assert.assertEquals(pkg.object, testObject);
    }
    // Unlock
    @Test public void test_Unlock_type() {
        Unlock pkg = new Unlock(testObject);
        Assert.assertEquals(pkg.type, PackageType.REQUEST);
    }
    @Test public void test_Unlock_msg() {
        Unlock pkg = new Unlock(testObject);
        Assert.assertEquals(pkg.msg, Unlock.UNLOCK_MESSAGE);
    }
    @Test public void test_Unlock_object() {
        Unlock pkg = new Unlock(testObject);
        Assert.assertEquals(pkg.object, testObject);
    }

    //
    // responses
    //

    // NoCurrentLock
    @Test public void test_NoCurrentLock_type() {
        NoCurrentLock pkg = new NoCurrentLock(testObject);
        Assert.assertEquals(pkg.type, PackageType.RESPONSE);
    }
    @Test public void test_NoCurrentLock_msg() {
        NoCurrentLock pkg = new NoCurrentLock(testObject);
        Assert.assertEquals(pkg.msg, NoCurrentLock.NO_CURRENT_LOCK_MESSAGE);
    }
    @Test public void test_NoCurrentLock_object() {
        NoCurrentLock pkg = new NoCurrentLock(testObject);
        Assert.assertEquals(pkg.object, testObject);
    }
    // ObjectIsLocked
    @Test public void test_ObjectIsLocked_type() {
        ObjectIsLocked pkg = new ObjectIsLocked(testObject);
        Assert.assertEquals(pkg.type, PackageType.RESPONSE);
    }
    @Test public void test_ObjectIsLocked_msg() {
        ObjectIsLocked pkg = new ObjectIsLocked(testObject);
        Assert.assertEquals(pkg.msg, ObjectIsLocked.OBJECT_IS_LOCKED_MESSAGE);
    }
    @Test public void test_ObjectIsLocked_object() {
        ObjectIsLocked pkg = new ObjectIsLocked(testObject);
        Assert.assertEquals(pkg.object, testObject);
    }
    // PeerList
    @Test public void test_PeerList_type() {
        PeerList pkg = new PeerList(testPeers);
        Assert.assertEquals(pkg.type, PackageType.RESPONSE);
    }
    @Test public void test_PeerList_msg() {
        PeerList pkg = new PeerList(testPeers);
        Assert.assertEquals(pkg.msg, PeerList.PEER_LIST_MESSAGE);
    }
    @Test public void test_PeerList_object() {
        PeerList pkg = new PeerList(testPeers);
        Assert.assertArrayEquals(pkg.peers, testPeers);
    }
    // Welcome
    @Test public void test_Welcome_type() {
        Welcome pkg = new Welcome();
        Assert.assertEquals(pkg.type, PackageType.RESPONSE);
    }
    @Test public void test_Welcome_msg() {
        Welcome pkg = new Welcome();
        Assert.assertEquals(pkg.msg, Welcome.WELCOME_MESSAGE);
    }
}
