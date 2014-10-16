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
 * Unit tests for ByePackageHandler
 * @author Jakob Klepp
 */
public class Test_ByePackageHandler {
    public PeerManager manager;
    public int connectionCalls;
    public RTNConnection connection;
    public RTNPackage[] pkgs;
    public RTNConnection failConnection;
    public Bye bye;

    @Before
    public void prepare() {
        manager = mock(PeerManager.class);
        connectionCalls = 0;
        connection = new RTNConnection() {
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
                new GetPeerList(),
                new Hallo(),
                new Lock(""),
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
        bye = new Bye();
    }

    //
    // correct usage
    //

    @Test
    public void test_handlePackage_correctUsage() {
        ByePackageHandler handler = ByePackageHandler.getInstance();

        handler.handlePackage(bye, connection);

        Assert.assertEquals(1, connectionCalls);
    }

    @Test
    public void test_getInstance_correctUsage() {
        ByePackageHandler handler = ByePackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof ByePackageHandler);
    }

    //
    // "experimental usage"
    //

    @Test
    public void test_getInstance_setNull() {
        ByePackageHandler.setInstance(null);

        ByePackageHandler handler = ByePackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof ByePackageHandler);
    }

    @Test
    public void test_getInstance_useSetter() {
        ByePackageHandler myHandler = mock(ByePackageHandler.class);
        ByePackageHandler.setInstance(myHandler);

        ByePackageHandler handler = ByePackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertEquals(myHandler, handler);
        Assert.assertTrue(handler instanceof ByePackageHandler);
    }

    @Test
    public void test_handlePackage_wrongPackage() {
        ByePackageHandler handler = ByePackageHandler.getInstance();

        for(RTNPackage pkg: pkgs) {
            handler.handlePackage(pkg, failConnection);
        }
    }

    // the PackageHandler should be able to
    // get along with invalid packages
    @Test
    public void test_handlePackage_nullPackage() {
        ByePackageHandler handler = ByePackageHandler.getInstance();

        handler.handlePackage(null, connection);
    }

    // if connection == null, a we cannot proceed
    @Test(expected = NullPointerException.class)
    public void test_handlePackage_nullConnection() {
        ByePackageHandler handler = ByePackageHandler.getInstance();

        handler.handlePackage(bye, null);
    }
}
