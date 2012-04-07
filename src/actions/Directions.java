package actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directions implements Iterable<Direction>{
	private Direction curDirection;
	private List<Direction> directions = new ArrayList<Direction>();
	
	public Iterator<Direction> iterator() {
		Iterator<Direction> iterator = directions.iterator();
		return iterator;
	}
	
	public void add(Direction direction) {
		directions.add(direction);
	}
	
	public boolean contains(Direction direction) {
		return directions.contains(direction);
	}
	
	public void setCurrentDirection(Direction direction) {
		curDirection = direction;
	}
	
	public Direction getCurrentDirection() {
		return curDirection;
	}
	
	public List<Direction> getDirections() {
		return directions;
	}

}
