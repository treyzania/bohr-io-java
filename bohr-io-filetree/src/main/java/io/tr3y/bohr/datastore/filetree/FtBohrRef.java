package io.tr3y.bohr.datastore.filetree;

import io.tr3y.bohr.BohrReference;

public class FtBohrRef implements BohrReference {

	private FileTreeDatastore datastore;
	private String label;

	public FtBohrRef(FileTreeDatastore ds, String l) {

		this.datastore = ds;
		this.label = l;

	}

	public String stripped() {
		return this.label;
	}

	public boolean isValid() {
		return true; // FIXME
	}

	@Override
	public String toString() {
		return "{" + this.label + "}";
	}

	@Override
	public int hashCode() {
		return this.datastore.hashCode() ^ this.label.hashCode(); // Hacky
	}

}
