package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.util.FileUtil;
import com.google.gson.Gson;

public class DirectionlessKeys {

	private List<Integer> keys;
	
	public DirectionlessKeys(String url) {
		keys = loadJSON(url);
	}
	
	public List<Integer> getKeys() {
		return Collections.unmodifiableList(keys);
	}
	
	private List<Integer> loadJSON(String url) {
		String[] jsonPacked = FileUtil.fileRead(new File(url));
		StringBuilder jsonBuilder = new StringBuilder();
		for (String line : jsonPacked) {
			jsonBuilder.append(line);
		}

		String json = jsonBuilder.toString();

		Gson gson = new Gson();
		Directionless directionless = gson.fromJson(json, Directionless.class);

		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 0; i < directionless.keys.length; i++)
			keys.add(directionless.keys[i]);

		return keys;
	}
	
	private class Directionless {
		public int[] keys;
	}
}
