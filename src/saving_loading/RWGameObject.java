package saving_loading;

import app.RPGGame;
import level.Level;


public interface RWGameObject {
	
	public boolean isThisKindOfObject(String objectTag);
	
	public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps, Level level, RPGGame game2);
	
	
}
