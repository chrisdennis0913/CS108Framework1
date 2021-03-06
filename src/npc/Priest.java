package npc;

import java.awt.image.BufferedImage;

import level.Level;

import saving_loading.AttributeContainer;
import saving_loading.MapContainer;
import saving_loading.RWGameObject;
import scenery.Portal;
import app.RPGGame;
import dialogue.SimpleDialogue;

@SuppressWarnings("serial")
public class Priest extends StationaryNPC {
	
	SimpleDialogue dialogue;
	public static int numPriests =0;
	public Priest(RPGGame game, AttributeContainer ac) {
		super(game, ac);
		setCanDie(true);
		attributes = ac;
		if(attributes.getType() != null)
			dialogue = new SimpleDialogue("resources/script/"+attributes.getType()+".txt");
	}

	public String getTalk() {
		if (game.getPlayer().getEquipped().getCategory().equals("Weapon"))
			dialogue.goToNextLine(dialogue.new SimpleDialogueObject());
		if (game.getPlayer().hasItem("Golden Sword of Paradise") && !dialogue.isDone())
			dialogue.goToNextLine(dialogue.new SimpleDialogueObject());
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
		public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps, Level level, RPGGame game2) {
			game = game2;
		    String name = attributeContainer.getName();
			int[] location = (int[]) attributeContainer.getObjectForKey("location", int[].class);
		    Priest priest = new Priest(game, attributeContainer);
		    priest.add(location, 6);
		    maps.npcs.put(name, priest);
		}		
	}
}
