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
			return (objectTag.equals("settings"));
		}

		@Override
		public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps, Level level) {
			level.setNextLevel(attributeContainer.getStringForKey("nextLevel"));
			level.setStartText(attributeContainer.getStringForKey("startText"));
		}		
	}


}
