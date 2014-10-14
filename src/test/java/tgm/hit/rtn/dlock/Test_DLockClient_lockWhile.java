package tgm.hit.rtn.dlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class Test_DLockClient_lockWhile {
    Integer[] sumThis;
    int expectedResult;
    DLock lock;

    @Before
    public void prepare() {
        sumThis = new Integer[]{1, 1, 2};
        expectedResult=4;
        lock = new DLock();
    }

    @Test
    public void testObjectsPassed() throws InterruptedException {
        Integer result=(Integer) lock.lockWhile(new Sum(), sumThis);
        int myResult=result.intValue();
        Assert.assertEquals(myResult,expectedResult);
    }

}
class Sum implements Callback<Integer,Integer>{

    @Override
    public Integer run(Integer... params) {
        if(params==null)return 0;
        if(params.length==0)return 0;
        int sum=0;
        for (int i = 0; i < params.length; i++) {
            sum+=params[i].intValue();
        }
        return sum;
    }
}
