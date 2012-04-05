package actions;

import java.awt.Graphics2D;
import java.util.List;

public interface Actions {
	public List<BaseAction> getActions();
	public void update(long elapsed);
	public void render(Graphics2D g);
}
