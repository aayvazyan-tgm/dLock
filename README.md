Builds will be available at:
http://aayvazyan.bitnamiapp.com/jenkins/

dLock is a peer to peer distributed lock manager.

#Installation:
Simply add the .jar as a library.

A Maven repo may be available soon. 

#Usage:
To get detailed information about the possibilities of dLock, take a look at the JavaDoc of the Class `DLock`

##Simple Lock Example

```java
public class example {
    public void testObjectsPassed() throws InterruptedException {
        Integer[] sumThis = new Integer[]{1, 1, 2};
        DLock lock = new DLock();
        Integer result=(Integer) lock.lockWhile(new Sum(), sumThis);
    }
}
class Sum implements tgm.hit.rtn.dlock.Callback<Integer,Integer>{
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
```