package tgm.hit.rtn.dlock.handlers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.transport.DLockClient;
import tgm.hit.rtn.dlock.transport.RTNConnection;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.*;
import tgm.hit.rtn.dlock.protocol.responses.*;

import static org.mockito.Mockito.mock;

/**
 * Unit tests for LockPackageHandler
 * @author Jakob Klepp
 */
public class Test_LockPackageHandler {
    public PeerManager manager;
    public int connectionCalls;
    public RTNConnection connection;
    public RTNPackage[] pkgs;
    public RTNConnection failConnection;
    public Lock lock;

    @Before
    public void prepare() {
        manager = mock(PeerManager.class);
        connectionCalls = 0;
        connection = connection = new RTNConnection() {
            @Override
            public PeerManager getPeerManager() {
                connectionCalls++;
                return new PeerManager() {
                    @Override public void addPeer(Peer peer) { }
                    @Override public Peer[] getPeers() { return new Peer[0]; }
                    @Override public boolean removePeer(Peer peer) { return false; }
                    @Override public void setClient(DLockClient client) {}
                };
            }
            @Override public Peer getPartner() { return null; }
            @Override public void answer(Response response) {}
        };
        pkgs = new RTNPackage[] {
                mock(RTNPackage.class),
                mock(Request.class),
                mock(Response.class),
                new Bye(),
                new GetPeerList(),
                new Hallo(),
                new Unlock(""),
                new NoCurrentLock(""),
                new ObjectIsLocked(""),
                new PeerList(new Peer[]{new Peer(1, "localhost")}),
                new Welcome()
        };
        failConnection = new RTNConnection() {
            @Override
            public PeerManager getPeerManager() {
                Assert.fail("You shall not pass!");
                return null;
            }
            @Override public Peer getPartner() { return null; }
            @Override public void answer(Response response) {}
        };
        lock = new Lock("");
    }

    //
    // correct usage
    //

    @Test
    public void test_handlePackage_correctUsage() {
        Assert.fail("Not implemented");  // The package handler, not only the test
        //LockPackageHandler handler = LockPackageHandler.getInstance();
        //
        //handler.handlePackage(lock, connection);
        //
        //Assert.assertEquals(1, connectionCalls);
    }

    @Test
    public void test_getInstance_correctUsage() {
        LockPackageHandler handler = LockPackageHandler.getInstance();
        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof LockPackageHandler);
    }

    //
    // "experimental usage"
    //

    @Test
    public void test_getInstance_setNull() {
        LockPackageHandler.setInstance(null);

        LockPackageHandler handler = LockPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof LockPackageHandler);
    }

    @Test
    public void test_getInstance_useSetter() {
        LockPackageHandler myHandler = mock(LockPackageHandler.class);
        LockPackageHandler.setInstance(myHandler);

        LockPackageHandler handler = LockPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertEquals(myHandler, handler);
        Assert.assertTrue(handler instanceof LockPackageHandler);
    }

    @Test
    public void test_handlePackage_wrongPackage() {
        LockPackageHandler handler = LockPackageHandler.getInstance();

        for(RTNPackage pkg: pkgs) {
            handler.handlePackage(pkg, failConnection);
        }
    }

    // the PackageHandler should be able to
    // get along with invalid packages
    @Test
    public void test_handlePackage_nullPackage() {
        LockPackageHandler handler = LockPackageHandler.getInstance();

        handler.handlePackage(null, connection);
    }

    // if connection == null, a we cannot proceed
    @Test(expected = NullPointerException.class)
    public void test_handlePackage_nullConnection() {
        LockPackageHandler handler = LockPackageHandler.getInstance();

        handler.handlePackage(lock, null);
    }
}
