/*
 * Jesus Arredondo
 *  2/27/2018
 *  Advanced Database Class
 *  Final Project: Inventory MapReduce
*/

import java.util.TreeMap;

public class ServerCluster {
	
	public int numberOfServers;
	private Server[] mSvr;
	
	public ServerCluster(int totalServers) {
		numberOfServers = totalServers;
		mSvr = new Server[numberOfServers];
		
		for (int i = 0; i < numberOfServers; i++) { 
			mSvr[i] = new Server(i); 
		}
	}
	
	public Server[] getMSvr() { 
		return mSvr; 
	}
	
	public void mapAll() { 
		for (Server d : mSvr) { 
			d.mapOut();
		}
	}
	
	public void join() { 
		Boolean finished = false;
		
		while (!finished) {
			finished = true;
		
			for (Server d : mSvr) {
				
				if (!d.getDone()) { 
					finished = false; 
					break; 
				}
			}
			
			System.out.println("\nWait...\n");
			
			try { 
				Thread.sleep(50); 
			} catch(Exception e) { }
		}
	}
	
	public TreeMap<String, Product> reduceBack() {
		return FileMapper.reduceKVal(this);
	}
}
