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
 * Unit tests for GetPeerListPackageHandler
 * @author Jakob Klepp
 */
public class Test_GetPeerListPackageHandler {
    public PeerManager manager;
    public int connectionGetPeerManagerCalls;
    public RTNConnection connection;
    public RTNPackage[] pkgs;
    public RTNConnection failConnection;
    public GetPeerList getPeerList;

    @Before
    public void prepare() {
        manager = mock(PeerManager.class);
        connectionGetPeerManagerCalls = 0;
        connection = connection = new RTNConnection() {
            @Override
            public PeerManager getPeerManager() {
                connectionGetPeerManagerCalls++;
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
        getPeerList = new GetPeerList();
    }

    //
    // correct usage
    //

    @Test
    public void test_handlePackage_correctUsage() {
        // TODO implement the test case in a correct way
        // it has to be different from the ByePackageHandler on
        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();
        handler.handlePackage(getPeerList, connection);
        Assert.assertEquals(1, connectionGetPeerManagerCalls);
    }

    @Test
    public void test_getInstance_correctUsage() {
        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof GetPeerListPackageHandler);
    }

    //
    // "experimental usage"
    //

    @Test
    public void test_getInstance_setNull() {
        ByePackageHandler.setInstance(null);

        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof GetPeerListPackageHandler);
    }

    @Test
    public void test_getInstance_useSetter() {
        GetPeerListPackageHandler myHandler = mock(GetPeerListPackageHandler.class);
        GetPeerListPackageHandler.setInstance(myHandler);

        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        Assert.assertNotNull(handler);
        Assert.assertEquals(myHandler, handler);
        Assert.assertTrue(handler instanceof GetPeerListPackageHandler);
    }

    @Test
    public void test_handlePackage_wrongPackage() {
        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        for(RTNPackage pkg: pkgs) {
            handler.handlePackage(pkg, failConnection);
        }
    }

    // the PackageHandler should be able to
    // get along with invalid packages
    @Test
    public void test_handlePackage_nullPackage() {
        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        handler.handlePackage(null, connection);
    }

    // if connection == null, a we cannot proceed
    @Test(expected = NullPointerException.class)
    public void test_handlePackage_nullConnection() {
        GetPeerListPackageHandler handler = GetPeerListPackageHandler.getInstance();

        handler.handlePackage(getPeerList, null);
    }
}
