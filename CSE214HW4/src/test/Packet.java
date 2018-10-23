package test;
/**
 * 
 * The <code>Packet</code> class are the packets that are
 * stored within the routers
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class Packet {
	static int packetCount=0; // how many packets are produced
	int orgTimeToDest; // the original time to dest
	private int id; // name of packet
	private int packetSize; // size of the packet
	private int timeArrive; // time of arrival
	private int timeToDest; // how much time left until dest
	
	/**
	 * Constructor for Packet class
	 * 
	 * @param packetSize
	 * 		how big the packet size is
	 * @param id
	 * 		number of the packet
	 * @param timeArrive
	 * 		what time the packet arrived at the dispatcher
	 * @param timeToDest
	 * 		time left until destination
	 */
	public Packet(int packetSize, int id, int timeArrive, int timeToDest){
		this.packetSize = packetSize;
		this.id = id;
		this.timeArrive = timeArrive;
		this.timeToDest = timeToDest;
		orgTimeToDest = timeToDest-1 ;
	}

	/**
	 * To string method for printing packages
	 */
	public String toString() {
		return ("["+id+", "+timeArrive+", "+timeToDest+"]");
	}
	
	/**
	 * Getter for PacketCount
	 * @return
	 * 		PackCount
	 */
	public static int getPacketCount() {
		return packetCount;
	}
	/**
	 * Setter for PacketCount
	 * @param packetCount
	 * 		PacketCount
	 */
	public static void setPacketCount(int packetCount) {
		Packet.packetCount = packetCount;
	}

	/**
	 * Getter for ID
	 * @return
	 * 		ID
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter for ID
	 * @param id
	 * 		ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for PacketSize
	 * @return
	 * 		PacketSize
	 */
	public int getPacketSize() {
		return packetSize;
	}
	/**
	 * Setter for PacketSize
	 * @param packetSize
	 * 		PacketSize
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	/**
	 * Getter for TimeArrive
	 * @return
	 * 		TimeArrive
	 */
	public int getTimeArrive() {
		return timeArrive;
	}
	/**
	 * Setter for TimeArrive
	 * @param timeArrive
	 * 		TimeArrive
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}

	/**
	 * Getter for TimeToDest
	 * @return
	 * 		TimeToDest
	 */
	public int getTimeToDest() {
		return timeToDest;
	}
	/**
	 * Setter for TimeToDest
	 * @param timeToDest
	 * 		TimeToDest
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}	
}
