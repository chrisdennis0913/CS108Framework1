package collisions;


import player.PlayerActions;
import npc.NPC;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class NPCCollision extends BasicCollisionGroup {

	private RPGGame game;
	private NPC npc;
	
	public NPCCollision (RPGGame game, String npcname) {
		this.game = game;
		this.npc = game.getLevel().getNPC(npcname);
	}
	
	public void collided(Sprite character, Sprite scenery) {
			overlap(character, scenery);
			PlayerActions actions = game.getPlayer().getActions();
			if (!actions.isTalkable()) {
				actions.setTalkable(true);
				actions.setTalkingNPC(npc);
			}

			System.out.println(game.getPlayer().getActions().isAttacking());
			
			if (game.getPlayer().getActions().isAttacking() && npc.canDie())
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
