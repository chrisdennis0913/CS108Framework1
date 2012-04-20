package saving_loading;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AttributeContainer {

	/**
	 * A Map containing the names of attributes and JSON formatted Strings of their corresponding values.
	 */
	private Map<String, String> attributes;
	private Gson gson;
		
	public AttributeContainer(){
		attributes = new HashMap<String, String>();
		gson = new Gson();
	}
	
	public AttributeContainer(String json){
		gson = new Gson();
        Type collectionType = new TypeToken<HashMap<String,String>>(){}.getType();
        HashMap<String,String> attributesFromJson = gson.fromJson(json, collectionType);
        attributes = attributesFromJson;
	}
	
	/** Allow users to add objects of any type (that can be converted to Json).
	 * 
	 * @param attributeName
	 * @param attributeValue
	 */
	public void put(String attributeName, Object attributeValue){
		String jsonValue = gson.toJson(attributeValue, attributeValue.getClass());
		attributes.put(attributeName, jsonValue);
	}
	
	// Allow putting of a specified attribute and a generated json String
	public void putAttributeWithJson(String attributeName, String jsonValue){
		attributes.put(attributeName, jsonValue);
	}
	
	public String getName(){
		return gson.fromJson(attributes.get("name"), String.class);
	}
	
	public String getType(){
		return gson.fromJson(attributes.get("type"), String.class);
	}
	
	public Map<String, String> getAttributeMap(){
		return attributes;
	}
	
	public String getStringForKey(String key){
		return gson.fromJson(attributes.get(key), String.class);
	}
	
	public int getIntForKey(String key){
		return gson.fromJson(attributes.get(key), int.class);
	}
	
	public double getDoubleForKey(String key){
		return gson.fromJson(attributes.get(key), double.class);
	}
	
	public Collection getCollectionForKey(String key, Type collectionType){
     
        return gson.fromJson(attributes.get(key), collectionType);
	}
	/**
	 * 
	 * @param key
	 * @param t the Type of the given attribute that you are requesting (e.g. String.class, int[].class, etc) 
	 * @return
	 */
	public Object getObjectForKey(String key, Type t){
		return gson.fromJson(attributes.get(key), t);
	}
	
	public String asJsonString(){
        return gson.toJson(attributes);
	}
}
