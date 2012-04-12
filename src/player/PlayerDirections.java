package player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import actions.Directions;

import com.google.gson.Gson;

public class PlayerDirections extends Directions {

	private Player player;

	public PlayerDirections(Player player, String url) {
		super(url);
		this.player = player;
		constructDirections(json);
		curDirection = Cardinal.DOWN;
	}
	
	public void setCurrentDirection(Cardinal direction) {
		 curDirection = direction;
		 player.getActions().setCurrentDirection(direction);
	}

	protected void constructDirections(String json) {
		Gson gson = new Gson();
		JSONDirection[] directions = gson.fromJson(json, JSONDirection[].class);

		for (JSONDirection direction : directions) {

			BufferedImage[] images = new BufferedImage[direction.images.length];
			for (int i = 0; i < images.length; i++)
				images[i] = player.getGame().getImage(direction.images[i]);

			List<Integer> keys = new ArrayList<Integer>();
			for (int i = 0; i < direction.keys.length; i++)
				keys.add(direction.keys[i]);

			getDirections()[direction.cardinal] = new PlayerDirection(player,
					images, direction.speedX, direction.speedY,
					Cardinal.values()[direction.cardinal], keys);
		}
	}

	private class JSONDirection {
		public int cardinal;
		public double speedX;
		public double speedY;
		public String[] images;
		public int[] keys;
	}

}
