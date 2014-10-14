package tgm.hit.rtn.dlock;

/**
 * This class serves as a Dummy class for the lockWhile test
 * @author Ari
 * @version 14.10.2014
 */
public class Sum implements Callback<Integer,Integer>{

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
