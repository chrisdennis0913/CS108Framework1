package scenery;

import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import level.Level;
import saving_loading.AttributeContainer;
import saving_loading.Jsonable;
import saving_loading.MapContainer;
import saving_loading.RWGameObject;
import app.RPGGame;
import collisions.SceneryCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.reflect.TypeToken;

public class Scenery implements Jsonable{
	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	private List<int[]> locations = new ArrayList<int[]>();
	private AttributeContainer attributes;
	
	public Scenery (RPGGame game2, String imageURL) {
		attributes = new AttributeContainer();
		Scenery.game = game2;
		this.image = game2.getImage(imageURL);
		this.group = new SpriteGroup("scenery_"+game2.getRandom(0, 1000000));
	}
	
	public Scenery (RPGGame game2, AttributeContainer ac) {
		attributes = ac;
		Scenery.game = game2;
		this.image = game2.getImage(ac.getStringForKey("imageURL"));
		this.group = new SpriteGroup("scenery_"+game2.getRandom(0, 1000000));
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void add(int[] location, int layer) {
		Sprite scenery = new Sprite(image, location[0], location[1]);
		scenery.setLayer(layer);
		group.add(scenery);
		locations.add(location);		
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	
	public void setCollision() {
		SceneryCollision collision = new SceneryCollision();
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}
	
	public SpriteGroup getGroup() {
		return group;
	}
	
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return attributes.asJsonString();
	}
	
	public static class Factory implements RWGameObject{		
		@Override
		public boolean isThisKindOfObject(String objectTag) {
			return (objectTag.equals("scenery"));
		}

		@Override
		public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps, Level level, RPGGame game2) {
			String name = attributeContainer.getName();
			int layer = attributeContainer.getIntForKey("layer");
			game = game2;
			
			// Have to include this when retrieving a Collection
	        Type collectionType = new TypeToken<ArrayList<int[]>>(){}.getType();
	        @SuppressWarnings("unchecked")
            List<int[]> locations = (List<int[]>) attributeContainer.getCollectionForKey("locations", collectionType);
	        
		    Scenery scenery = new Scenery(game, attributeContainer);
		    for(int[] location : locations){
		    	scenery.add(location, layer);
		    }
		    System.out.println(name);
		    maps.scenery.put(name, scenery);
		}		
	}


}
