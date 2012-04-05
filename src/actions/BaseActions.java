package actions;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class BaseActions implements Actions {

	private List<BaseAction> actions = new ArrayList<BaseAction>();
	
	public List<BaseAction> getActions() {
		return actions;
	}

	public void update(long elapsed) {
		for (BaseAction action : actions)
			action.update(elapsed);
	}

	public void render(Graphics2D g) {
		for (BaseAction action : actions) {
			if (action instanceof GraphicalAction) {
				GraphicalAction gaction = (GraphicalAction) action;
				gaction.render(g);
			}
		}
	}

}
