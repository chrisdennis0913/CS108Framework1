package counters;

import java.awt.Graphics2D;
import java.util.List;

public interface Counters {
	public List<BaseCounter> getCounters();
	public void update(long elapsed);
	public void render(Graphics2D g);
}
