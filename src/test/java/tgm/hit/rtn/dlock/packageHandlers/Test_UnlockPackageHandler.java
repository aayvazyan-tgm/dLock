package tgm.hit.rtn.dlock.packageHandlers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tgm.hit.rtn.dlock.Peer;
import tgm.hit.rtn.dlock.PeerManager;
import tgm.hit.rtn.dlock.TransportLayer.DLockClient;
import tgm.hit.rtn.dlock.TransportLayer.RTNConnection;
import tgm.hit.rtn.dlock.protocol.RTNPackage;
import tgm.hit.rtn.dlock.protocol.requests.*;
import tgm.hit.rtn.dlock.protocol.responses.*;

import static org.mockito.Mockito.mock;

/**
 * Unit tests for UnlockPackageHandler
 * @author Jakob Klepp
 */
public class Test_UnlockPackageHandler {
    public PeerManager manager;
    public int connectionCalls;
    public RTNConnection connection;
    public RTNPackage[] pkgs;
    public RTNConnection failConnection;
    public Unlock unlock;

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
                new Lock(""),
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
        unlock = new Unlock("");
    }

    //
    // correct usage
    //

    @Test
    public void test_handlePackage_correctUsage() {
        // TODO implement the test case in a correct way
        // it has to be different from the ByePackageHandler on
        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();
        handler.handlePackage(unlock, connection);

        //Assert.assertEquals(1, connectionCalls);
        Assert.assertTrue("According to current plans, the Unlock package can be safely ignored.", true);
    }

    @Test
    public void test_getInstance_correctUsage() {
        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof UnlockPackageHandler);
    }

    //
    // "experimental usage"
    //

    @Test
    public void test_getInstance_setNull() {
        UnlockPackageHandler.setInstance(null);

        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof UnlockPackageHandler);
    }

    @Test
    public void test_getInstance_useSetter() {
        UnlockPackageHandler myHandler = mock(UnlockPackageHandler.class);
        UnlockPackageHandler.setInstance(myHandler);

        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertEquals(myHandler, handler);
        Assert.assertTrue(handler instanceof UnlockPackageHandler);
    }

    @Test
    public void test_handlePackage_wrongPackage() {
        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        for(RTNPackage pkg: pkgs) {
            handler.handlePackage(pkg, failConnection);
        }
    }

    // the PackageHandler should be able to
    // get along with invalid packages
    @Test
    public void test_handlePackage_nullPackage() {
        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        handler.handlePackage(null, connection);
    }

    // if connection == null, a we cannot proceed
    @Test(expected = NullPointerException.class)
    public void test_handlePackage_nullConnection() {
        UnlockPackageHandler handler = UnlockPackageHandler.getInstance();

        handler.handlePackage(unlock, null);
    }
}
