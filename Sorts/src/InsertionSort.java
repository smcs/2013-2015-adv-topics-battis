import java.util.Vector;

public class InsertionSort implements SortingAlgorithm {

	protected RunningAverage average;

	public InsertionSort() {
		average = new RunningAverage("Insertion Sort", Sorts.N, 0, 0);
	}

	@Override
	public SmartVector sort(Vector<Integer> unsorted) {
		SmartVector v = new SmartVector(
				unsorted,
				new Statistic(average.getAlgorithm(), unsorted.size(), 0, 0));
		int sortedElements = 0;
		Integer temp;
		v.getStatistic().addStorage(1);
		v.getStatistic().startTimer();
		while (sortedElements < v.size()) {
			temp = v.get(sortedElements);
			int insertionLocation = 0;
			while (v.get(insertionLocation) < temp
					&& insertionLocation < sortedElements) {
				insertionLocation++;
			}
			if (insertionLocation != sortedElements) {
				v.shiftRight(insertionLocation, sortedElements);
				v.set(insertionLocation, temp);
			}
			sortedElements++;
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
