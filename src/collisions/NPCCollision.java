package collisions;


import npc.NPC;
import actions.Attacking;
import actions.Talking;
import app.Main;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class NPCCollision extends BasicCollisionGroup {

	private Main game;
	private NPC npc;
	
	public NPCCollision (Main game, String npcname) {
		this.game = game;
		this.npc = game.getLevel().getNPC(npcname);
	}
	
	public void collided(Sprite character, Sprite scenery) {
			overlap(character, scenery);
			Talking talking = (Talking) game.getPlayer().getAction("talking");
			if (!talking.isActionable()) {
				talking.setActionable(true);
				talking.setTalkingTo(npc);
			}
			Attacking attacking = (Attacking) game.getPlayer().getAction("attacking");
			if (attacking.isActing() && npc.canDie())
				npc.die();
	}
	
	protected void overlap(Sprite character, Sprite scenery) {
		double maxsep = scenery.getImage().getHeight() - character.getImage().getHeight()/6*5;
		double separation = character.getY() - scenery.getY();
		
		if (separation <= maxsep) {
			character.setX(character.getOldX());
			character.setY(character.getOldY());
		}
	}

}
