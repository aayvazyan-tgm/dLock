package tgm.hit.rtn.dlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tgm.hit.rtn.dlock.transport.DLockClient;

import static org.mockito.Mockito.mock;

/**
 *
 */
public class Test_DLockClient_lockWhile {
    Integer[] sumThis;
    int expectedResult;
    DLock lock;

    PeerManager peerManager;
    DLockClient dLockClient;
    GotLock after100Millies;

    @Before
    public void prepare() {
        peerManager = new LinkedListPeerManager();
        dLockClient = mock(DLockClient.class);
        
        lock = new DLock(peerManager, dLockClient);

        sumThis = new Integer[]{1, 1, 2};
        expectedResult = 4;
    }

    @Test(timeout = 1000)  // rather have the test fail then have it run forever
    public void testObjectsPassed() throws InterruptedException {
        int myResult= (Integer) lock.lockWhile(new Sum(), (Object[])sumThis);
        Assert.assertEquals(myResult,expectedResult);
    }

}
class Sum implements Callback<Integer,Integer>{

    @Override
    public Integer run(Integer... params) {
        if(params==null)
            return 0;
        if(params.length==0)
            return 0;
        int sum=0;
        for (Integer param : params) {
            sum += param;
        }
        return sum;
    }
}
