import java.util.Vector;

public class MergeSort implements SortingAlgorithm {

	protected RunningAverage average;

	public MergeSort() {
		average = new RunningAverage("Merge Sort", Sorts.N, 0, 0);
	}

	@Override
	public SmartVector sort(Vector<Integer> unsorted) {
		SmartVector v = new SmartVector(
				unsorted, new Statistic(average.getAlgorithm(), unsorted.size(), 0, 0));
		v.getStatistic().startTimer();
		v = mergeSort(v);
		v.getStatistic().stopTimer();
		average.addInstance(v.getStatistic());
		return v;
	}

	private SmartVector mergeSort(
			SmartVector v) {
		if (v.size() <= 1) {
			return v;
		} else {
			int midpoint = v.size() / 2;
			SmartVector left = new SmartVector(
					v.subList(0, midpoint), v.getStatistic());
			SmartVector right = new SmartVector(
					v.subList(midpoint, v.size()),
					v.getStatistic());
			
			left = mergeSort(left);
			right = mergeSort(right);
			
			SmartVector sorted = new SmartVector(
					v.getStatistic());
			while (left.size() > 0 && right.size() > 0) {
				if (left.get(0) < right.get(0)) {
					sorted.add(left.remove(0));
				} else {
					sorted.add(right.remove(0));
				}

				if (left.size() == 0) {
					sorted.addAll(right);
					right.removeAllElements();
				} else if (right.size() == 0) {
					sorted.addAll(left);
					left.removeAllElements();
				}
			}
			return sorted;
		}
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
