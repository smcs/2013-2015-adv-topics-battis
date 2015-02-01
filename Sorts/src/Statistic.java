
public class Statistic {
	protected int numberOfBrokenKeyboards, quantityOfDeveloperTears;
	protected int n; // number of elements
	private long start, stop; // in milliseconds
	protected long runTime; // in milliseconds
	protected long storage; // in bytes
	protected long reads, writes;
	protected String algorithm;
	
	public Statistic(String algorithm, int n, int keyboards, int tears) {
		this.algorithm = algorithm;
		this.numberOfBrokenKeyboards = keyboards;
		this.quantityOfDeveloperTears = tears;
		this.n = n;
		storage = 0;
		reads = 0;
		writes = 0;
		runTime = 0;
	}
	
	public void startTimer() {
		start = System.nanoTime();
	}
	
	public void stopTimer() {
		stop = System.nanoTime();
		runTime = stop - start;
	}
	
	public void addRead() {
		reads++;
	}
	
	public void addReads(long reads) {
		this.reads += reads;
	}
	
	public void addWrite() {
		writes++;
	}
	
	public void addWrites(long writes) {
		this.writes += writes;
	}
	
	public String toString() {
		return new String(
				algorithm + ": " + n + " elements\n" +
				"runtime: " + runTime + " nanoseconds\n" +
				"storage: " + storage + " <Integer> max, " + reads + " reads, " + writes + " writes\n" +
				"anguish: " + numberOfBrokenKeyboards + " keyboards broken / " + quantityOfDeveloperTears + " tears shed\n"
			);
	}
	
	public long getRunTime() {
		return runTime;
	}
	
	public long getStorage() {
		return storage;
	}
	
	public long getReads() {
		return reads;
	}
	
	public long getWrites() {
		return writes;
	}
	
	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}
	
	public void setStorage(long storage) {
		this.storage = storage;
	}
	
	public void setReads(long reads) {
		this.reads = reads;
	}
	
	public void setWrites(long writes) {
		this.writes = writes;
	}

	public void addStorage(long size) {
		storage += size;
	}

	public String getAlgorithm() {
		return algorithm;
	}
}
