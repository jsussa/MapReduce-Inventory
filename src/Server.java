/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

import java.util.TreeMap;

public class Server {
	private boolean bDone;
	
	public boolean getDone() { 
		return bDone; 
	}
	
	public void setDone() { 
		bDone = true; 
	}
	
	private TreeMap<String, Product> tmap;
	
	public void setMapOut(TreeMap<String, Product> tm) { 
		tmap = tm; 
	}
	
	public TreeMap<String, Product> getMapOut() { 
		return tmap; 
	
	}
	public threadSingle thread;
	
	public Server(int index) {
		bDone = false;
		tmap = new TreeMap<String, Product>();
		thread = new threadSingle(this, index);
	}
	
	public void mapOut() { 
		thread.start(); 
	}
}
