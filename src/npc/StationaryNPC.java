package npc;

import level.MapContainer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import app.RPGGame;

public abstract class StationaryNPC extends NPC{

	public StationaryNPC(RPGGame game2, String name) {
		super(game2, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract String toJson();

	@Override
	public abstract String getTalk();

	@Override
	public abstract void die();
	
	public abstract void changeLocation(int[] location);
	

}
