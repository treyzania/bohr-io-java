package io.tr3y.bohr;

import com.google.gson.Gson;

/**
 * Used to specify a type system to interact with a datastore.
 * 
 * @author treyzania
 *
 */
public class Bohr {

	private Gson gson;
	private Datastore datastore;

	protected Bohr(Gson gson, Datastore datastore) {

		this.gson = gson;
		this.datastore = datastore;

	}

	// TODO

}
