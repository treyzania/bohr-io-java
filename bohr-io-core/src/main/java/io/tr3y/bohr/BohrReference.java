package io.tr3y.bohr;

public interface BohrReference {

	/**
	 * Returns a stripped version on this reference that stores only the needed
	 * information.
	 * 
	 * @return a stripped version of the hash
	 */
	String stripped();

	/**
	 * Checks to see if the reference is current valid in the datastore.
	 * 
	 * @return if the reference is valid
	 */
	boolean isValid();

}
