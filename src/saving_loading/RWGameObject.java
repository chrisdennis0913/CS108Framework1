package saving_loading;


public interface RWGameObject {
	
	public boolean isThisKindOfObject(String objectTag);
	
	public void createAndAddToMap(AttributeContainer attributeContainer, MapContainer maps);
	
	
}
