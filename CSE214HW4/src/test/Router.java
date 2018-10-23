package test;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * The <code>Router</code> class holds the packets
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class Router {
	ArrayList<Packet> pack; // arraylist to hold packets
	
	/**
	 * Constructor for Router class
	 */
	public Router() {
		pack = new ArrayList<Packet>();
	}
	/**
	 * Constructor for Router class
	 * @param pack
	 * 		Arraylist to hold packets
	 */
	public Router(ArrayList<Packet> pack) {
		this.pack = pack;
	}
	
	/**
	 * finds which router has the least amount of packets in them
	 * 
	 * @param routers
	 * 		the collection of routers from class Simulator
	 * @return
	 * 		the index of the router that has the least amount of packets
	 */
	public static int sendPacketTo(Collection routers) {
		int min = Simulator.routers.get(0).pack.size();
		int x = 0;
		for(int i = 0;i<Simulator.routers.size();i++) {
			Router s = Simulator.routers.get(i);
			if(s.pack.size()<min) {
				min = s.pack.size();
				x = i;
			}
		}
		if(min==Simulator.maxBufferSize) {
			Simulator.packetsDropped++;
			return -1;
		}
		return x;
	}
	
	/**
	 * adds the packet p into the pack list
	 * 
	 * @param p
	 * 		packet being added into the list
	 */
	public void enqueue(Packet p) {
		pack.add(p);
	}
	
	/**
	 * removes the first packet from the list
	 * 
	 * @return
	 * 		the packet being removed
	 */
	public Packet dequeue() {
		Packet x = peek();
		pack.remove(0);
		return x;
	}
	
	/**
	 * shows the first packet in the list
	 * 
	 * @return
	 * 		the packet at the front of the queue
	 */
	public Packet peek() {
		return pack.get(0);
	}
	
	/**
	 * shows the size of the router
	 * 
	 * @return
	 * 		size of pack
	 */
	public int size() {
		return pack.size();
	}
	
	/**
	 * shows if router is empty
	 * 
	 * @return
	 * 		if router is empty or not
	 */
	public boolean isEmpty() {
		return(pack.size()==0);
	}
	
	/**
	 * Returns printed router
	 */
	public String toString() {
		return pack.toString();
	}
}
