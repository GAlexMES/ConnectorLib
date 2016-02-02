package de.szut.dqi12.cheftrainer.connectorlib.utils;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Sendable;

/**
 * This class provides a few JSON method, which could be used to transform JSONObject etc. to something else. Or other way round.
 * @author Alexander Brennecke
 *
 */
public class JSONUtils {

	/**
	 * This class created a JSONObject out of the given HashMap.
	 * @param map the Map, that should be transformed to a JSON.
	 * @return a new JSONObject with the map information inside.
	 */
	public static JSONObject mapToJSON(HashMap<String,Boolean> map) {
        JSONObject retval = new JSONObject();
        
        for(String key : map.keySet() ){
            boolean value = map.get(key); 
            retval.put(key, value);

        }
        return retval;
    }
	
	/**
	 * This function is used to create a {@link JSONArray} based on a {@link List} of objects, which extends the {@link Sendable} abstract class.
	 * @param valueList a list of classes, which extends from the {@link Sendable} abstract class.
	 * @return a {@link JSONArray} based on the given {@link List}
	 */
	public static JSONArray listToJSON(List<? extends Sendable> valueList){
		JSONArray retval = new JSONArray();
		for(Sendable s: valueList){
			retval.put(s.toJSON());
		}
		return retval;
	}
}
