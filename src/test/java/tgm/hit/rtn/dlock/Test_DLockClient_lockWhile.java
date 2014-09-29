package tgm.hit.rtn.dlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class Test_DLockClient_lockWhile {
    Object[] testObjects;
    DLock lock;

    @Before
    public void prepare() {
        testObjects = new Object[]{"blub", 1};
        lock = new DLock();
    }

    @Test
    public void testObjectsPassed() {
        lock.lockWhile(new Callback() {
            @Override
            public void run(Object... params) {
                Assert.assertArrayEquals(params, testObjects);
            }
        }, testObjects);
    }
}
