package actions;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import com.golden.gamedev.util.FileUtil;

public abstract class Directions implements Iterable<Direction>{
	private Cardinal curDirection;
	private Direction[] directions = new Direction[4];
	protected String json;
	public enum Cardinal {
		UP, DOWN, RIGHT, LEFT;
	}
	
	public Directions(String url) {
		String[] jsonPacked = FileUtil.fileRead(new File(url));
		StringBuilder jsonBuilder = new StringBuilder();
		for (String line : jsonPacked) {
			jsonBuilder.append(line);
		}
		json = jsonBuilder.toString();
	}
	
	protected abstract void constructDirections(String json);
	
	public Iterator<Direction> iterator() {
		Iterator<Direction> iterator = Arrays.asList(directions).iterator();
		return iterator;
	}
	
	public void setCurrentDirection(Cardinal direction) {
		curDirection = direction;
	}
	
	public Cardinal getCurrentDirection() {
		return curDirection;
	}

	public Direction[] getDirections() {
		return directions;
	}
}
