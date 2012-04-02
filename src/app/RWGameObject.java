package app;

import level.MapContainer;

public interface RWGameObject {
	
	public boolean isThisKindOfObject(String objectTag);
	
	public void createAndAddToMap(String jsonData, MapContainer maps);
	
	
}
