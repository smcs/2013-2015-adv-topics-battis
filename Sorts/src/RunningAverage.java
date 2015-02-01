
public class RunningAverage extends Statistic {

	protected int instances = 0;
	
	public RunningAverage(String algorithm, int n, int keyboards, int tears) {
		super(algorithm, n, keyboards, tears);
	}
	
	public void addInstance(Statistic stat) {
		storage = runningAverage(instances, storage, stat.getStorage());
		runTime = runningAverage(instances, runTime, stat.getRunTime());
		reads = runningAverage(instances, reads, stat.getReads());
		writes = runningAverage(instances, writes, stat.getWrites());
		instances++;
	}
	
	private static long runningAverage(long previousInstances, long currentAverage, long newValue) {
		return (previousInstances * currentAverage + newValue) / (previousInstances + 1);
	}


}
