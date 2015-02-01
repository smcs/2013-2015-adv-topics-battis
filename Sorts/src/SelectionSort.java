import java.util.Vector;

public class SelectionSort implements SortingAlgorithm {

	protected RunningAverage average;

	public SelectionSort() {
		average = new RunningAverage("Selection Sort", Sorts.N, 0, 0);
	}

	@Override
	public SmartVector sort(Vector<Integer> unsorted) {
		SmartVector v = new SmartVector(
				unsorted,
				new Statistic(average.getAlgorithm(), unsorted.size(), 0, 0));
		int sorted = 0;
		int currentMinIndex;
		Integer temp, currentMin; 
		v.getStatistic().addStorage(2);
		v.getStatistic().startTimer();
		while (sorted < v.size()) {
			currentMinIndex = sorted;
			currentMin = v.get(sorted);
			for (int i = sorted + 1; i < v.size(); i++) {
				temp = v.get(i);
				if (temp < currentMin) {
					currentMin = temp;
					currentMinIndex = i;
				}
			}
			if (currentMinIndex != sorted) {
				v.set(currentMinIndex, v.get(sorted));
				v.set(sorted, currentMin);
			}
			sorted++;
		}
		v.getStatistic().stopTimer();
		
		average.addInstance(v.getStatistic());
		
		return v;
	}

	@Override
	public RunningAverage getAverage() {
		return average;
	}

	@Override
	public String toString() {
		return average.toString();
	}
}
