package algorithms;

public interface IAlgo <T>{

	
	/**
	 * get page from the MMU
	 * @param t the reference to the required object
	 * @return the required object
	 */
	T get(T t);

	/**
	 * add page to the MMU
	 * @param t the object to add as page
	 * @return the object that was added as page
	 */
	T add(T t);

	/**
	 * remove required page from the MMU
	 * @param t the reference to be removed as page
	 */
	void remove(T t);

}
