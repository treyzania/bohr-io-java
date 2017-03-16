package io.tr3y.bohr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

public interface Datastore {

	/**
	 * Puts the objects into the datastore and return a list of references to
	 * them, mapped 1:1.
	 * 
	 * @param objs
	 *            the objects to serialize
	 * @return the references to the objects stored
	 * @throws IOException
	 *             on fail
	 */
	List<BohrReference> putObjects(List<JsonObject> objs) throws IOException;

	/**
	 * Gets the objects by their reference, returning null if somehow not found.
	 * 
	 * @param refs
	 *            the references
	 * @return the objects found
	 * @throws IOException
	 *             on fail
	 */
	List<JsonObject> getObjects(List<BohrReference> refs) throws IOException;

	/**
	 * Resolves the names of the anchors to the references they represent.
	 * 
	 * @param names
	 *            the names of the anchors
	 * @return the references the anchors reference
	 * @throws IOException
	 *             on fail
	 */
	List<BohrReference> resolveAnchors(List<String> names) throws IOException;

	/**
	 * Returns a map of string to the anchors they represent.
	 * 
	 * @return the anchors in the datastore
	 * @throws IOException
	 *             on fail
	 */
	Map<String, BohrReference> getAnchors() throws IOException;

	/**
	 * Returns a list of all objects stored in the datastore. This includes
	 * objects that cannot be referenced by any anchored object.
	 * 
	 * @return a list of all references in the datastore
	 * @throws IOException
	 *             on fail
	 */
	List<BohrReference> getAllObjectReferences() throws IOException;

	/**
	 * Stores the object in the database and resets the anchor referencing the
	 * object.
	 * 
	 * @param anchor
	 *            the name of the anchor
	 * @param obj
	 *            the object we are storing
	 * @return the new reference
	 * @throws IOException
	 *             on fail
	 */
	BohrReference putAnchoredObject(String anchor, JsonObject obj) throws IOException;

	/**
	 * Locks the datastore and remove any object that does not satisfy one of
	 * the following properties:
	 * 
	 * <ul>
	 * <li>is an anchor</li>
	 * <li>is referenced by an anchor</li>
	 * <li>is in the "keep" list</li>
	 * <li>is referenced by an object in the "keep" list</li>
	 * </ul>
	 * 
	 * @param keep
	 *            a list of extra objects
	 * @throws IOException
	 *             on fail
	 */
	void cleanup(List<BohrReference> keep) throws IOException;

}
