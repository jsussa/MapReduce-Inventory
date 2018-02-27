/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileMapper {

	public static TreeMap<String, Product> readKVal(String fPath) {
		TreeMap<String, Product> tmap = new TreeMap<String, Product>();
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			// JSON array to read and parse JSON file
			 jsonArray = (JSONArray) parser.parse(new FileReader(fPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (Object object : jsonArray) {
			
			JSONObject jsonProduct = (JSONObject) object;
			Product newProduct = new Product();
			
			// Read pub_id value from the JSON file and set it to Product object
			String p_id = (String) jsonProduct.get("pub_id");
			newProduct.setPub_id(p_id);
			
			// Read inv_total value from the JSON file and set it to Product object
			Long i_total = (Long) jsonProduct.get("inv_total");
			newProduct.setInv_total(i_total);
			
			// Get all comments from Product and create JSON array
			JSONArray jsonComments = (JSONArray) jsonProduct.get("comments");
			
			tmap.put(p_id, newProduct);	
		}
		return tmap;
	}
	
	
	public static TreeMap<String, Product> reduceKVal(ServerCluster cluster) {
		TreeMap<String, Product> rMap = new TreeMap <String, Product>();
		
		for (Server d: cluster.getMSvr()) { 
			TreeMap<String, Product> dMap = d.getMapOut();
			
			if (! dMap.isEmpty()) {
				for (Map.Entry<String, Product> entry: dMap.entrySet()) {
					String key = entry.getKey();
					Product value = entry.getValue();
										
					// If product is read and it already exists, keep the highest inventory total
					if (rMap.containsKey(key)) {
						if (value.getInv_total() > rMap.get(key).getInv_total()) {
							rMap.remove(key);
							rMap.put(key, value);
						}
					} else { rMap.put(key, value); }
				}
			}
			
		}
		return rMap;
	}
}
