package tgm.hit.rtn.dlock.util;

/**
 * The Callback interface is designed to allow for a common, reusable interface
 * to exist for defining APIs that requires a call back in certain situations.
 * 
 * Callback is defined with two generic parameters: the first parameter
 * specifies the type of the object passed in to the <code>call</code> method,
 * with the second parameter specifying the return type of the method.
 *
 * @param <P> The type of the argument provided to the <code>call</code> method.
 * @param <R> The type of the return type of the <code>call</code> method.
 */
public interface Callback<P, R> {
    /**
     * The <code>call</code> method is called when required, and is given a
     * single argument of type P, with a requirement that an object of type R
     * is returned.
     *
     * @param params The arguments upon which the returned value should be
     *               determined.
     * @return An object of type R that may be determined based on the provided
     * parameter value.
     */
    public abstract R run(P... params);

}
