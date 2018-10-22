package test;

public class Packet {
	static int packetCount=0;
	int orgTimeToDest;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;
	
	public Packet(int packetSize, int id, int timeArrive, int timeToDest){
		this.packetSize = packetSize;
		this.id = id;
		this.timeArrive = timeArrive;
		this.timeToDest = timeToDest;
		orgTimeToDest = timeToDest;
	}

	public String toString() {
		return ("["+id+", "+timeArrive+", "+timeToDest+"]");
	}
	
	public static int getPacketCount() {
		return packetCount;
	}
	public static void setPacketCount(int packetCount) {
		Packet.packetCount = packetCount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	public int getTimeArrive() {
		return timeArrive;
	}
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}

	public int getTimeToDest() {
		return timeToDest;
	}
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}	
}
