package player;

import java.awt.Graphics2D;

import npc.NPC;
import actions.DirectionlessKeys;
import app.Dialog;

public class Talking extends PlayerAction{
	private long timer = 0;
	private NPC npc;
	private DirectionlessKeys keys;

	public Talking(Player player) {
		super(player);
		setEnabled(false, true);
		keys = new DirectionlessKeys("resources/player/actions/talking.json");
	}
	private void reset() {
		timer = 0;
	}

	private void unsetNPC() {
		setActive(false);
		npc = null;
		reset();
	}

	public void setNPC(NPC npc) {
		this.npc = npc;
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			if (getActions().checkKeys(keys.getKeys())) {
				setActive(true);
				Dialog dialog = getPlayer().getGame().getDialog();
				dialog.setMessage(npc.getTalk());
			} else {
				timer += elapsed;
				if (timer > 500)
					setEnabled(false, false);
			}
		}
		else if (isActive()) {
			timer += elapsed;
			if (timer > 3000)
				unsetNPC();
		}
	}

	public void render(Graphics2D g) {
		if (isActive()) {
			Dialog dialog = getPlayer().getGame().getDialog();
			dialog.showMessage(g);
		}
	}
}
