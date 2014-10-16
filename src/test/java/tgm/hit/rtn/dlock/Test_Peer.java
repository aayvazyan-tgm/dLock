package tgm.hit.rtn.dlock;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PeerTest</code> contains tests for the class <code>{@link Peer}</code>.
 *
 * @generatedBy CodePro at 10/16/14 3:04 PM
 * @author jakob
 * @version $Revision: 1.0 $
 */
public class Test_Peer {
    /**
     * Run the Peer(int,String) constructor test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testPeer_1()
            throws Exception {
        int port = 1;
        String host = "";

        Peer result = new Peer(port, host);

        // add additional test code here
        assertNotNull(result);
        assertEquals("", result.getHost());
        assertEquals(1, result.getPort());
    }

    /**
     * Run the boolean equals(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testEquals_1()
            throws Exception {
        Peer fixture = new Peer(1, "");
        Object otherPeer = new Peer(1, "");

        boolean result = fixture.equals(otherPeer);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the boolean equals(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testEquals_2()
            throws Exception {
        Peer fixture = new Peer(1, "");
        Object otherPeer = new Peer(1, "");

        boolean result = fixture.equals(otherPeer);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the boolean equals(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testEquals_3()
            throws Exception {
        Peer fixture = new Peer(1, "");
        Object otherPeer = new Peer(1, "");

        boolean result = fixture.equals(otherPeer);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the boolean equals(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testEquals_4()
            throws Exception {
        Peer fixture = new Peer(1, "");
        Object otherPeer = new Object();

        boolean result = fixture.equals(otherPeer);

        // add additional test code here
        assertEquals(false, result);
    }

    /**
     * Run the String getHost() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testGetHost_1()
            throws Exception {
        Peer fixture = new Peer(1, "");

        String result = fixture.getHost();

        // add additional test code here
        assertEquals("", result);
    }

    /**
     * Run the int getPort() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testGetPort_1()
            throws Exception {
        Peer fixture = new Peer(1, "");

        int result = fixture.getPort();

        // add additional test code here
        assertEquals(1, result);
    }

    /**
     * Run the int hashCode() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testHashCode_1()
            throws Exception {
        Peer fixture = new Peer(1, "");

        int result = fixture.hashCode();

        // add additional test code here
        assertEquals(992, result);
    }

    /**
     * Run the void setHost(String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testSetHost_1()
            throws Exception {
        Peer fixture = new Peer(1, "");
        String host = "";

        fixture.setHost(host);

        // add additional test code here
    }

    /**
     * Run the void setPort(int) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Test
    public void testSetPort_1()
            throws Exception {
        Peer fixture = new Peer(1, "");
        int port = 1;

        fixture.setPort(port);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @Before
    public void setUp()
            throws Exception {
        // add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception
     *         if the clean-up fails for some reason
     *
     * @generatedBy CodePro at 10/16/14 3:04 PM
     */
    @After
    public void tearDown()
            throws Exception {
        // Add additional tear down code here
    }
}