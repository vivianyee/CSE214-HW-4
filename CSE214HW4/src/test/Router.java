package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Router {
	ArrayList<Packet> pack = new ArrayList<Packet>();

	public Router() {
		ArrayList<Packet> pack = new ArrayList<Packet>();
	}
	
	public Router(ArrayList<Packet> pack) {
		this.pack = pack;
	}
	
	public static int sendPacketTo(Collection routers) throws illegalElementException {
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
	
	public void enqueue(Packet p) throws illegalElementException {
		pack.add(p);
	}
	
	public Packet dequeue() {
		Packet x = peek();
		pack.remove(0);
		return x;
	}
	
	public Packet peek() {
		return pack.get(0);
	}
	
	public int size() {
		return pack.size();
	}
	
	public boolean isEmpty() {
		return(pack.size()==0);
	}
	
	public String toString() {
		return pack.toString();
	}
}
