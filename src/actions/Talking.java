package actions;

import java.awt.event.KeyEvent;

import app.Player;

import npc.NPC;

public class Talking extends Action {

	private NPC talkingTo;

	public Talking(Player player, int frames, String name) {
		super(player, frames, name);
		messageable = true;
	}

	public void setTalkingTo(NPC npc) {
		talkingTo = npc;
	}

	public void act() {
		if (isActionable() && !isActing() && player.getGame().keyDown(KeyEvent.VK_ENTER)) {
			setActing(true);
			player.getGame().getDialog().setMessage(talkingTo.getTalk());
		}

		if (getActionTime() > 2000) {
			setActing(false);
			setActionable(false);
			setTalkingTo(null);
		}
	}

}
