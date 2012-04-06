package enemy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import level.MapContainer;

import scenery.Portal;
import ai.SimpleAI;
import app.RPGGame;
import app.RWGameObject;

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

	public Snake(RPGGame game, String name) {
		super(game, name, 1);
		timer.setFPS(100);
		timer.startTimer();
		setActing(false);
		setAI(new SimpleAI(game,this));
	}


	@Override
	public void initAttacks() {
		super.initAttacks();
		attacks.put("shooting", new ShootingAttack(game,this));
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
		Gson gson = new Gson();
		Collection collection = new ArrayList();
	    collection.add("snake");
	    collection.add("snake");
	    collection.add(location[0]);
	    collection.add(location[1]);
	    String json = gson.toJson(collection);
	    
	    System.out.println(json);
		return json;
	}
	
	public static class Factory implements RWGameObject{
		
		@Override
		public boolean isThisKindOfObject(String objectTag) {
			// TODO Auto-generated method stub
			return (objectTag.equals("snake"));
		}

		@Override
		public void createAndAddToMap(String jsonData, MapContainer maps) {
			// TODO Auto-generated method stub
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
		    JsonArray array = parser.parse(jsonData).getAsJsonArray();
		    String name = gson.fromJson(array.get(1), String.class);
		    int x = gson.fromJson(array.get(2), int.class);
		    int y = gson.fromJson(array.get(3), int.class);
		    int[] location = new int[]{x,y};
		    Snake snake = new Snake(game, name);
		    snake.add(location, 4); // this used to be 100,100
			maps.enemies.put(name, snake);
		}		
	}

}
