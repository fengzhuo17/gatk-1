package org.broadinstitute.sting.indels;

/** Utility class that makes working with counted objects slightly easier (and faster). 
 * Consider a "generic" counter representation as Map<Object,Integer>: updating the counter would require
 * int i = map.get(obj).intValue(); i++; map.set(obj,i) - cumbersome, and also inefficient due to extra invocations of Integer
 * constructor. This ObjectCounter class can increment its internally kept counter without the need to rebuild any objects,
 * so one can use, e.g. "Set<CountedObject> myset;" and then "myset.get(obj).increment()".  Note that equals() method
 * defines counted objects to be the same iff the underlying objects are equal, regardless of the
 * counter value. Should the counters be compared, one has to use the getters on the two counted objects
 * and compare the results.
 * @author asivache
 *
 */
public class CountedObject<T> {
	private T mObject;
	int mCounter;
	
	/** Creates new counter associated with the passed object and assigns the default count of 1 
	 * 
	 * @param o object to start counting for
	 */
	public CountedObject(T o) {
		mObject = o;
		mCounter = 1;
	}

	/** Creates new counter associated with the object o and assigns specified initial count to it
	 * 
	 * @param o object to start counting for
	 * @param n initial count
	 */
	public CountedObject(T o, int n) {
		mObject = o;
		mCounter = n;
	}

    public T getObject() { return mObject; }
	public int getCount() { return mCounter; }
	public void increment() { mCounter++;}
	public void increment(int n) { mCounter+=n; }
	public void decrement() { mCounter--; }
	public void decrement(int n) { mCounter -= n; }
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( ! ( o instanceof CountedObject )) return false;
        return mObject.equals(o);
    }

}
