import java.util.*;

public class Sorts {

	public static final int N = 1000;
	public static final int RUNS = 100;

	public static void main(String[] args) {
		SortingAlgorithm bubbleSort, insertionSort, selectionSort, mergeSort, mergeSortInPlace, quicksort;
		Vector<Integer> unsorted;

		bubbleSort = new BubbleSort();
		insertionSort = new InsertionSort();
		selectionSort = new SelectionSort();
		mergeSort = new MergeSort();
		mergeSortInPlace = new MergeSortInPlace();
		quicksort = new Quicksort();
		
		for (int i = 0; i < RUNS; i++) {
			System.out.println(unsorted = Sorts.shuffledVector(N));
			System.out.println(bubbleSort.sort(unsorted));
			System.out.println(insertionSort.sort(unsorted));
			System.out.println(selectionSort.sort(unsorted));
			System.out.println(mergeSort.sort(unsorted));
			System.out.println(mergeSortInPlace.sort(unsorted));
			System.out.println(quicksort.sort(unsorted));
			System.out.println("---------");
		}
		System.out.println("After " + RUNS + " runs:");
		System.out.println(bubbleSort.getAverage());
		System.out.println(insertionSort.getAverage());
		System.out.println(selectionSort.getAverage());
		System.out.println(mergeSort.getAverage());
		System.out.println(mergeSortInPlace.getAverage());
		System.out.println(quicksort.getAverage());
	}

	public static Vector<Integer> randomVector(int size) {
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < size; i++) {
			v.add((int) (Math.random() * size));
		}
		return v;
	}

	public static Vector<Integer> shuffledVector(int size) {
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < size; i++) {
			v.add(i);
		}
		for (int i = 0; i < Math.pow(size, 2); i++) {
			int a = (int) (Math.random() * size);
			int b = (int) (Math.random() * size);
			if (a != b) {
				int temp = v.get(a);
				v.set(a, v.get(b));
				v.set(b, temp);
			}
		}
		return v;
	}

	public static void printArray(Vector<Integer> v) {
		for (int i = 0; i < v.size(); i++) {
			System.out.print(v.get(i) + " ");
		}
		System.out.println();
	}

	public static boolean isSorted(Vector<Integer> v) {
		for (int i = 0; i < v.size() - 1; i++) {
			if (v.get(i) > v.get(i + 1)) {
				return false;
			}
		}
		return true;
	}
}
