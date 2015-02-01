import java.util.*;

public interface SortingAlgorithm {

	/**
	 * @precondition none
	 * @postcondition unsorted is unmodified
	 * @param unsorted
	 *            a vector of integers to be sorted
	 * @return a vector containing the contents of unsorted, sorted from
	 *         smallest to largest
	 */
	public abstract SmartVector sort(Vector<Integer> unsorted);
	
	/**
	 * @return a running average of performance statistics
	 */
	public RunningAverage getAverage();
}
