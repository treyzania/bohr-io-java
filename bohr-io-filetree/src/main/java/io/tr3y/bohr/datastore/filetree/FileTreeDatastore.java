package io.tr3y.bohr.datastore.filetree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import io.tr3y.bohr.BohrReference;
import io.tr3y.bohr.Datastore;
import io.tr3y.bohr.util.CallableProcessor;

public class FileTreeDatastore implements Datastore {

	private static final String ANCHORS_DIR_NAME = "anch";
	private static final String OBJECTS_DIR_NAME = "objs";
	private static final String SHA256_HEX_REGEX = "[A-Fa-f0-9]{64}";
	
	private File root;
	private CallableProcessor queue = new CallableProcessor("FileTreeDatastore-Thread");

	public FileTreeDatastore(File root) {
		
		this.root = root;
		this.queue.start();

		File a = this.getAnchorsDir();
		File o = this.getObjectsDir();
		if (!a.exists()) a.mkdirs();
		if (!o.exists()) o.mkdirs();

	}

	public File getAnchorsDir() {
		return new File(this.root, ANCHORS_DIR_NAME);
	}

	public File getObjectsDir() {
		return new File(this.root, OBJECTS_DIR_NAME);
	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
		this.queue.interrupt();

	}

	public List<BohrReference> putObjects(List<JsonObject> objs) throws IOException {
		// TODO Auto-generated method stub
		return null;

	}

	public List<JsonObject> getObjects(List<BohrReference> refs) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BohrReference> resolveAnchors(List<String> names) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, BohrReference> getAnchors() throws IOException {

		Map<String, BohrReference> refs = new HashMap<>();

		for (File f : this.getAnchorsDir().listFiles()) {

			if (f.isDirectory()) {

				f.delete();
				continue;

			}

			String name = f.getName();
			Reader r = new FileReader(f);
			int len = (int) f.length();

			if (len != 64) {

				f.delete();
				continue;

			}

			char[] data = new char[len];
			r.read(data);
			r.close();

			String label = new String(data);
			if (!label.matches(SHA256_HEX_REGEX)) {

				f.delete();
				continue;

			}

			refs.put(name, new FtBohrRef(this, label));

		}

		return refs;

	}

	public List<BohrReference> getAllObjectReferences() throws IOException {

		List<BohrReference> refs = new ArrayList<>();

		for (File f : this.getObjectsDir().listFiles()) {

			String name = f.getName();
			if (!name.matches(SHA256_HEX_REGEX)) f.delete();

			refs.add(new FtBohrRef(this, name));

		}
		
		return refs;

	}

	public BohrReference putAnchoredObject(String anchor, JsonObject obj) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void cleanup(List<BohrReference> keep) throws IOException {
		// TODO Auto-generated method stub
	}

}
