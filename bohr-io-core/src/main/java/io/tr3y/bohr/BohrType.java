package io.tr3y.bohr;

/**
 * Added onto an object to specify that it can be stored in the datastore.
 * 
 * @author treyzania
 *
 */
public @interface BohrType {

	/**
	 * A simple name for the type.
	 * 
	 * @return the name
	 */
	String name();

}
