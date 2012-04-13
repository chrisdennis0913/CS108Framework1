package enemy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;


import saving_loading.AttributeContainer;
import saving_loading.MapContainer;
import saving_loading.RWGameObject;
import scenery.Portal;
import ai.BetterAI;
import ai.SimpleAI;
import ai.SlowPlayerAttack;
import app.RPGGame;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Snake extends Enemy {

	private SystemTimer timer = new SystemTimer();
	private boolean acting = false;
	private long actionStartTime = 0;
	// Used to give each snake a unique name
	public static int numSnakes =0;

	public Snake(RPGGame game, AttributeContainer ac) {
		super(game, ac, 1);
		timer.setFPS(100);
		timer.startTimer();
		setActing(false);
		setAI(new BetterAI(game,this));
		attributes = ac;
	}


	@Override
	public void initAttacks() {
		spontaneousAttacks.put("shooting", new ShootingAttack(game,this));
		reactiveAttacks.put("poison", new PoisonAttack(game,this,true));
		reactiveAttacks.put("slow", new SlowPlayerAttack(game, this, true));
		//attacks.put("supertelepathic", new SuperTelepathicAttack(game,this));
	}

	public void setActing(boolean acting) {
		this.acting = acting;
		actionStartTime = timer.getTime();
	}

	public boolean isAction() {
		return acting;
	}

	public long getActionTime() {
		return timer.getTime() - actionStartTime;
	}

	public void die() {
		BufferedImage death = game.getImage("resources/npc/snake_dead.gif");
		getGroup().getActiveSprite().setImage(death);
		setDead(true);
		game.getLevel().addSceneryObject(
				new Portal(game, "resources/scenery/portal.gif"));
	}
	
	@Override
	public String toJson() {
	    return attributes.asJsonString();
	}
	
	public static class Factory implements RWGameObject{
		
		@Override
		public boolean isThisKindOfObject(String objectTag) {
			return (objectTag.equals("snake"));
		}

		@Override
		public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps) {
			String name = attributeContainer.getName();
			int[] location = (int[]) attributeContainer.getObjectForKey("location", int[].class);
		    Snake snake = new Snake(game, attributeContainer);
		    snake.add(location, 4); // this used to be 100,100
			maps.enemies.put(name, snake);
		}		
	}

}
