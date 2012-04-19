package level;

import npc.Priest;
import saving_loading.AttributeContainer;
import saving_loading.Jsonable;
import saving_loading.MapContainer;
import saving_loading.RWGameObject;

public class LevelSettings implements Jsonable{

	private AttributeContainer attributes;
	
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return attributes.asJsonString();
	}
	
	public static class Factory implements RWGameObject{
		
		@Override
		public boolean isThisKindOfObject(String objectTag) {
			return (objectTag.equals("priest"));
		}

		@Override
		public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps) {
			String name = attributeContainer.getName();
			int[] location = (int[]) attributeContainer.getObjectForKey("location", int[].class);
		    Priest priest = new Priest(game, attributeContainer);
		    priest.add(location, 6);
		    maps.npcs.put(name, priest);
		}		
	}


}
