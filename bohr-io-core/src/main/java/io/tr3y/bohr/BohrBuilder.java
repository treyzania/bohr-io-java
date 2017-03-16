package io.tr3y.bohr;

import com.google.gson.GsonBuilder;

public class BohrBuilder {

	private GsonBuilder gsonBuilder = new GsonBuilder();
	private Datastore datastore;

	public BohrBuilder() {

	}

	public GsonBuilder getGsonBuilder() {
		return this.gsonBuilder;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public Bohr create() {

		if (this.datastore == null) throw new IllegalStateException("Datastore not specified.");
		return new Bohr(this.gsonBuilder.create(), this.datastore);

	}

}
