import java.util.Vector;

public class Quicksort implements SortingAlgorithm {

	protected RunningAverage average;

	public Quicksort() {
		average = new RunningAverage("Quicksort", Sorts.N, 0, 0);
	}

	@Override
	public SmartVector sort(Vector<Integer> unsorted) {
		SmartVector sorting = new SmartVector(unsorted, new Statistic(
				average.getAlgorithm(), unsorted.size(), 0, 0));
		sorting.getStatistic().startTimer();
		quicksort(sorting, 0, sorting.size() - 1);
		sorting.getStatistic().stopTimer();
		average.addInstance(sorting.getStatistic());
		return sorting;
	}

	private SmartVector quicksort(SmartVector v, int first, int last) {
		/* if we have only one element (or no elements), we're sorted! */
		if (first >= last) {
			return v;
		} /* otherwise, let's start sorting */else {
			int pivot = (first + last) / 2;
			int left = first, right = last;
			Integer pivotValue = v.get(pivot);
			Integer temp;
			v.getStatistic().addStorage(2);
			/*
			 * so long as our left and right thumbs are on the proper side of
			 * the pivot...
			 */
			while (left < pivot || right > pivot) {
				/*
				 * run the left thumb over until we encounter a misplaced
				 * element
				 */
				while (v.get(left) < pivotValue && left < pivot) {
					left++;
				}
				/*
				 * run the right thumb over until we encounter a misplaced
				 * element
				 */
				while (v.get(right) > pivotValue && right > pivot) {
					right--;
				}

				/*
				 * if both left and right thumbs are on the proper side of the
				 * pivot, swap the misplaced elements
				 */
				if (left < pivot && right > pivot) {
					v.swap(left, right);

				} /*
				 * otherwise, one thumb is at the pivot, so we need to insert
				 * our current element beyond the pivot
				 */else {
					/*
					 * if the left thumb is properly placed, that value needs to
					 * move
					 */
					if (left < pivot) {
						temp = v.get(left);
						v.shiftLeft(left, pivot);
						v.set(pivot, temp);
						pivot--; /* shifted left */
						right = pivot;
					} /*
					 * otherwise, if the right thumb is properly placed, that
					 * value needs to move
					 */else if (right > pivot) {
						temp = v.get(right);
						v.shiftRight(pivot, right);
						v.set(pivot, temp);
						pivot++; /* shifted right */
						left = pivot;
					}
				}
			}
			quicksort(v, first, pivot - 1);
			quicksort(v, pivot + 1, last);
			return v;
		}
	}

	@Override
	public RunningAverage getAverage() {
		return average;
	}

}
