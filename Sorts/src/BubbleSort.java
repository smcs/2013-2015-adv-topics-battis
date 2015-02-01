import java.util.Vector;

public class BubbleSort implements SortingAlgorithm {

	protected RunningAverage average;

	public BubbleSort() {
		average = new RunningAverage("Bubble Sort", Sorts.N, 0, 0);
	}
	
	/**
	 * Perform a a bubble sort on an array of presumptively unsorted integers
	 * 
	 * @param unsorted
	 *            the array to be sorted
	 * @return a sorted array
	 * @pre unsorted is unsorted, all elements in unsorted are assigned integer
	 *      values
	 * @post unsorted is unchanged
	 */
	public SmartVector sort(Vector<Integer> unsorted) {
		/* storage variables */
		SmartVector v = new SmartVector(
				unsorted, new Statistic(average.getAlgorithm(), unsorted.size(), 0, 0));

		/* process variables */
		boolean swap;
		int bubbleThumb;
		
		/* keep repeating until we have no more swaps to make */
		v.getStatistic().startTimer();
		do {
			swap = false;
			/*
			 * one pass through the array, floating the current highest value to
			 * the end
			 */
			for (bubbleThumb = 0; bubbleThumb < v.size() - 1; bubbleThumb++) {
				assert (v.get(bubbleThumb) != null);
				assert (v.get(bubbleThumb + 1) != null);
				if (v.get(bubbleThumb) > v.get(bubbleThumb + 1)) {
					swap = true;
					v.swap(bubbleThumb, bubbleThumb+1);
				}
			}
		} while (swap);
		v.getStatistic().stopTimer();
		
		average.addInstance(v.getStatistic());
		
		/* v is now sorted */
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
