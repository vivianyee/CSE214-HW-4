package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
/**
 * 
 * The <code>Simulator</code> class contains main method and
 * creates a network of routers
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class Simulator {
	static Router dispatcher = new Router(); // level 1 router
	static ArrayList<Router> routers = new ArrayList<Router>(); // level 2 router
	//static Collection<Router> routers = new List(); // level 2 router
	static Iterator<Router> iterator = routers.iterator();
	static int totalServiceTime; // running sum of total time each
						  		 // packet is in network
	static int totalPacketsArrived; // total number of packets that
							        // that has moved to destination
	static int packetsDropped; // records number of packets dropped
	public static final int MAX_PACKETS = 3; // number of packets attempted to create
	static double arrivalProb; // probability of making a packet
	static int numIntRouters; // number of routers
	static int maxBufferSize; // max number of packets in router
	static int minPacketSize; // minimum size for packet
	static int maxPacketSize; // maximum size for packet
	static int bandwidth; // how many can go to destination at once
	static int duration; // how many times it loops through
	
	/**
	 * goes through routers and adds packages to routers until duration is reached
	 * 
	 * @return
	 * 		total service time over the total packets that have arrived
	 * @throws illegalElementException
	 */
	public static double simulate() throws IllegalElementException {
		for(int x=1;x<duration+1;x++) {
			System.out.println("Time: "+x);
			int howmany = 0;
			for(int y=0;y<MAX_PACKETS;y++) {
				if(Math.random()<arrivalProb) {
					Packet.setPacketCount(Packet.getPacketCount() + 1);
					int size = randInt(minPacketSize,maxPacketSize);	
					System.out.println("Packet "+Packet.getPacketCount()+" arrives at dispatcher with size "+size);
					int nani = (size/100)+1;
					Packet newPack = new Packet(size,Packet.getPacketCount(),x,nani);
					dispatcher.enqueue(newPack); // get()
					howmany++;
				}
			}
			if(dispatcher.isEmpty()) {
				System.out.println("No packets arrived.");
			}
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
		if(totalPacketsArrived==0) {
			return 0;
		}
		return (double)totalServiceTime/totalPacketsArrived;
	}
	
	/**
	 * Gives a random number from min to max
	 * 
	 * @param minVal
	 * 		minimum the value can be
	 * @param maxVal
	 * 		maximum the value can be
	 * @return
	 * 		random integers between min and max
	 */
	private static int randInt(int minVal, int maxVal) {
		int x = maxVal - minVal;
		int y = (int) ((Math.random()*x)+minVal);
		return y;
	}
	
	/**
	 * Main method of Simulator
	 * 
	 * @param args
	 * @throws IllegalElementException
	 * 		if minPacketSize is less than 100;
	 */
	public static void main(String[] args) throws IllegalElementException {
		replace();
		System.out.println("Program terminating successfully...");
	}
	public static void replace() throws IllegalElementException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Starting simulator...\r\n" + 
				"\r\n" + "Enter the number of Intermediate routers: ");
		numIntRouters = scan.nextInt();
		System.out.println("Enter the arrival probability of a packet: ");
		arrivalProb = scan.nextDouble();
		System.out.println("Enter the maximum buffer size of a router: ");
		maxBufferSize = scan.nextInt();
		System.out.println("Enter the minimum size of a packet: ");
		minPacketSize = scan.nextInt();
		if(minPacketSize<100){
			throw new IllegalElementException("Enter a value greater than 100 for course code.");
		}
		System.out.println("Enter the maximum size of a packet: ");
		maxPacketSize = scan.nextInt();
		System.out.println("Enter the bandwidth size: ");
		if(minPacketSize>maxPacketSize) {
			throw new IllegalElementException("max size of packet must be bigger than min size");
		}
		bandwidth = scan.nextInt();
		System.out.println("Enter the simulation duration: ");
		duration = scan.nextInt();
		for(int n=0;n<numIntRouters;n++) {
			routers.add(new Router());
		}
		double avg = Math. round(simulate() * 100.0) / 100.0;
		
		System.out.println("Simulation ending...\r\n" + 
				"Total service time: " + totalServiceTime + "\r\n" + 
				"Total packets served: " + totalPacketsArrived + "\r\n" + 
				"Average service time per packet: "+ avg +"\r\n" + 
				"Total packets dropped: " + packetsDropped);
	}
}
