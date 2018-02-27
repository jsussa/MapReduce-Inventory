/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

import java.util.Map;
import java.util.TreeMap;

public class MapReduce {

	public static void main(String[] args) {
		
		// This is the number of files to read, default is 5
		int numberOfFiles = 5;
		
		// Used to receive number of files from the command line (example of API use)
		if (args.length > 0) {
		    try {
		        numberOfFiles = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}
		
		ServerCluster serverCluster = new ServerCluster(numberOfFiles);
	
		System.out.println("\n1. Mapping ... \n");
		
		serverCluster.mapAll();
		serverCluster.join(); 
		
		System.out.println("\n2. Joining ... \n");
		
		TreeMap<String, Product> rMap = serverCluster.reduceBack();
		
		System.out.println("\n3. Reducing ... \n");
		
		// Print all the entries in the reduced map
		for (Map.Entry<String, Product> productEntry : rMap.entrySet()) {
			System.out.println("pub_Id: " + productEntry.getKey() + ", inv_total: " + productEntry.getValue().inv_total);
		}		
	}
}