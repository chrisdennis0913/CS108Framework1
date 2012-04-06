package counters;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCounters {
	private List<BaseCounter> counters = new ArrayList<BaseCounter>();

	public List<BaseCounter> getCounters() {
		return counters;
	}

	public void update(long elapsed) {
		for (BaseCounter counter : counters)
			counter.update(elapsed);
	}
	
	public void render(Graphics2D g) {
		for (BaseCounter counter : counters) {
			if (counter instanceof GraphicalCounter) {
				GraphicalCounter gcounter = (GraphicalCounter) counter;
				gcounter.render(g);
			}
		}
	}
}
