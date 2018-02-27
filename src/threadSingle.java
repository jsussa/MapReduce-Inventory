/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

import java.util.Map;
import java.util.TreeMap;

public class threadSingle extends Thread {
	private Server server; 
	private int index;
	private String filePath;
	
	public threadSingle(Server svr, int serverIndex) {
		server = svr;
		index = serverIndex;
		filePath = "/Users/jesus.arredondo/Documents/workspace/MapReduce/src/files/svFile-" + serverIndex;
	}
	
	public void run() { 
			System.out.println(filePath + " Server/thread-" + index);
			TreeMap<String, Product> tmap = FileMapper.readKVal(filePath);
			server.setMapOut(tmap);
			
			if (!tmap.isEmpty()) {
				for (Map.Entry<String, Product> entry : tmap.entrySet()) {
					String key = entry.getKey();
					Product value = entry.getValue();
					
					// Print out each product and inventory total from the current file
					System.out.println(filePath + " > " + key + " : " + value.inv_total);
				}
			}
			
			server.setDone();
	}
}
