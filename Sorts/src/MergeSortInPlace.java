import java.util.Vector;

public class MergeSortInPlace extends MergeSort {

	public MergeSortInPlace() {
		average = new RunningAverage("Merge Sort (in place)", Sorts.N, 0, 0);
	}
	
	@Override
	public SmartVector sort(Vector<Integer> unsorted) {
		SmartVector v = new SmartVector(
				unsorted, new Statistic(average.getAlgorithm(), unsorted.size(), 0, 0));
		v.getStatistic().startTimer();
		v = mergeSort(v, 0, v.size() - 1);
		v.getStatistic().stopTimer();
		average.addInstance(v.getStatistic());
		return v;
	}

	private SmartVector mergeSort(
			SmartVector v, int first, int last) {
		if (first == last) {
			return v;
		} else {
			int midpoint = (first + last) / 2;
			int left = first;
			int right = midpoint + 1;
			Integer temp;
			v.getStatistic().addStorage(1);
			mergeSort(v, left, midpoint);
			mergeSort(v, right, last);
			while (right <= last && left < right) {
				temp = v.get(right);
				if (temp < v.get(left)) {
					v.shiftRight(left, right);
					v.set(left, temp);
					right++;
				}
				left++;
			}
		}
		return v;
	}

	@Override
	public String toString() {
		return average.toString();
	}
}
