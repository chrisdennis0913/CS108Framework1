package npc;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import level.MapContainer;
import scenery.Portal;
import app.RPGGame;
import app.RWGameObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dialogue.SimpleDialogue;

@SuppressWarnings("serial")
public class Priest extends StationaryNPC {
	
	SimpleDialogue dialogue;
	public static int numPriests =0;
	public Priest(RPGGame game, String name) {
		super(game, name);
		setCanDie(true);
		dialogue = new SimpleDialogue("resources/script/"+name+".txt");
	}
	

	public String getTalk() {
		if (game.getPlayer().hasItem("Golden Sword of Paradise") && !dialogue.isDone())
			dialogue.goToNextLine(true);
		return dialogue.getCurrentLine();
	}

	public void die() {
		if (!isDead()) {
			BufferedImage death = game.getImage("resources/npc/priest_dead.png");
			getGroup().getActiveSprite().setImage(death);
			setDead(true);
			game.getLevel().addSceneryObject(new Portal(game, "resources/scenery/portal.gif"));
		}
	}
	

	@Override
	public String toJson() {
		// {"value1":1,"value2":"abc"}
		Gson gson = new Gson();
		Collection collection = new ArrayList();
	    collection.add("priest");
	    collection.add(name);
	    collection.add(location[0]);
	    collection.add(location[1]);
	    String json = gson.toJson(collection);
		//String json = "[\"tag\": \"priest\", "+"\"name\":"+ "\"priest\", \"xlocation\":"+location[0]+", \""+location[1]+"]]";
	    System.out.println(json);
		return json;
	}
	
	@Override
	public void changeLocation(int[] newLocation) {
		this.location = newLocation;
		this.setLocation(newLocation[0], newLocation[1]);
	}
	
	public static class Factory implements RWGameObject{
		
		@Override
		public boolean isThisKindOfObject(String objectTag) {
			return (objectTag.equals("priest"));
		}

		@Override
		public void createAndAddToMap(String jsonData, MapContainer maps) {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
		    JsonArray array = parser.parse(jsonData).getAsJsonArray();
		    String name = gson.fromJson(array.get(1), String.class);
		    int x = gson.fromJson(array.get(2), int.class);
		    int y = gson.fromJson(array.get(3), int.class);
		    int[] location = new int[]{x,y};
		    Priest priest = new Priest(game, name);
		    priest.add(location, 6);
		    maps.npcs.put(name, priest);
		}		
	}
}
