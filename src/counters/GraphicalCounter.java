package counters;

import java.awt.Graphics2D;

public abstract class GraphicalCounter extends BaseCounter {
	
	public GraphicalCounter(int count) {
		super(count);
	}

	public abstract void render(Graphics2D g);
}
