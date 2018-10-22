package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class Simulator {
	static Router dispatcher = new Router(); // level 1 router
	static ArrayList<Router> routers = new ArrayList<Router>(); // level 2 router
	static Iterator<Router> iterator = routers.iterator();
	static int totalServiceTime; // running sum of total time each
						  		 // packet is in network
	static int totalPacketsArrived; // total number of packets that
							        // that has moved to destination
	static int packetsDropped; // records number of packets dropped
	public static final int MAX_PACKETS = 3;
	static double arrivalProb;
	static int numIntRouters;
	static int maxBufferSize;
	static int minPacketSize;
	static int maxPacketSize;
	static int bandwidth;
	static int duration;
	
	public static double simulate() throws illegalElementException {
		for(int x=1;x<duration+1;x++) {
			System.out.println("Time: "+x);
			// Decide whether packets have arrived at the Dispatcher.
			// A maximum of 3 can arrive at a given time.
			int howmany = 0;
			for(int y=0;y<MAX_PACKETS;y++) {
				if(Math.random()<arrivalProb) {
					Packet.setPacketCount(Packet.getPacketCount() + 1);
					int size = randInt(minPacketSize,maxPacketSize);	
					System.out.println("Packet "+Packet.getPacketCount()+" arrives at dispatcher with size "+size);
					Packet newPack = new Packet(size,Packet.getPacketCount(),x+1,size/100);
					dispatcher.enqueue(newPack); // get()
					howmany++;
				}
			}
			// If the Dispatcher contains unsent packets, send them off to one of the 
			// Intermediate routers. You will write the method sendPacketTo
			// (Collection intRouters) to decide which router the packet should be forwarded to.
			int count = Packet.getPacketCount()-howmany+1;
			while(!dispatcher.isEmpty()) {
				int dest = Router.sendPacketTo(routers); // destination where the router is
				Packet yo = dispatcher.dequeue();
				if(dest==-1) {
					System.out.println("Network is congested. Packet "+yo.getId()+" is dropped.");
				}else {
					routers.get(dest).enqueue(yo);
					dest++;
					System.out.println("Packet "+count+" sent to Router "+dest);
				}
				count++;
			}
			
			// NOT WORKING
			// Decrement all packets counters in the beginning of the 
			// queue at each Intermediate router.
			int band = 0;
			for(Router i : routers) {
				for(int j = 0;j < i.pack.size();j++) {
					Packet yeo = i.pack.get(j);
					yeo.setTimeToDest(yeo.getTimeToDest()-1);
					if(yeo.getTimeToDest()<=0) {
						if(band!=bandwidth) { // same router not sending back
							System.out.println("Packet "+yeo.getId()+" has successfully reached its destination: +"
										+yeo.orgTimeToDest);
							totalServiceTime+=yeo.orgTimeToDest;
							i.pack.remove(j);
							totalPacketsArrived++;
							band++;
							j--;
						}else {
							packetsDropped++;
						}
					}
				}
			}
			for(int l=1;l<routers.size()+1;l++) {
				System.out.println("R"+l+": "+routers.get(l-1).toString());
			}
			System.out.println("\n");
			
		}
		return (double)totalServiceTime/totalPacketsArrived;
	}
	
	private static int randInt(int minVal, int maxVal) {
		int x = maxVal - minVal;
		int y = (int) ((Math.random()*x)+minVal);
		return y;
	}
	
	public static void main(String[] args) throws illegalElementException {
		Scanner scan = new Scanner(System.in);
		// HOW MANY ROUTERS
		System.out.println("Starting simulator...\r\n" + 
				"\r\n" + "Enter the number of Intermediate routers: ");
		numIntRouters = scan.nextInt();
		// PROBABILITY OF MAKING A PACKET
		System.out.println("Enter the arrival probability of a packet: ");
		arrivalProb = scan.nextDouble();
		// HOW BIG A ROUTER CAN BE
		System.out.println("Enter the maximum buffer size of a router: ");
		maxBufferSize = scan.nextInt();
		// MIN VALUE OF PACKET
		System.out.println("Enter the minimum size of a packet: ");
		minPacketSize = scan.nextInt();
		// MAX VALUE OF PACKET
		System.out.println("Enter the maximum size of a packet: ");
		maxPacketSize = scan.nextInt();
		// HOW MANY PACKETS CAN TRANSFER AT ONCE
		System.out.println("Enter the bandwidth size: ");
		bandwidth = scan.nextInt();
		// HOW MANY TIMES IT REPEATS
		System.out.println("Enter the simulation duration: ");
		duration = scan.nextInt();
		// Create array lists for routers
		for(int n=0;n<numIntRouters;n++) {
			routers.add(new Router());
		}
		double avg = simulate();
		System.out.println("Simulation ending...\r\n" + 
				"Total service time: " + totalServiceTime + "\r\n" + 
				"Total packets served: " + totalPacketsArrived + "\r\n" + 
				"Average service time per packet: "+ avg +"\r\n" + 
				"Total packets dropped: " + packetsDropped);
	}
}
