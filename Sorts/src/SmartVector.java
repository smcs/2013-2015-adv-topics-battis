import java.util.*;

@SuppressWarnings("serial")
public class SmartVector extends Vector<Integer> {
	private Statistic stat;
	boolean swapped = false;

	public SmartVector(Statistic stat) {
		super();
		this.stat = stat;
	}

	public SmartVector(int size, Statistic stat) {
		super(size);
		this.stat = stat;
		stat.addStorage(size());
	}

	public SmartVector(SmartVector other) {
		super(other);
		this.stat = other.stat;
		stat.addStorage(size());
	}

	public SmartVector(Vector<Integer> v, Statistic stat) {
		super(v);
		this.stat = stat;
		stat.addStorage(size());
	}

	public SmartVector(List<Integer> list, Statistic stat) {
		super(list);
		this.stat = stat;
		stat.addStorage(size());
	}

	@Override
	public Integer get(int i) {
		stat.addRead();
		return super.get(i);
	}

	@Override
	public boolean add(Integer value) {
		stat.addWrite();
		return super.add(value);
	}

	@Override
	public void add(int i, Integer value) {
		stat.addWrite();
		super.add(i, value);
	}

	@Override
	public Integer set(int i, Integer value) {
		stat.addWrite();
		return super.set(i, value);
	}
	
	@Override
	public List<Integer> subList(int fromIndex, int toIndex) {
		stat.addReads(toIndex-fromIndex);
		return super.subList(fromIndex, toIndex);
	}

	public Statistic getStatistic() {
		return stat;
	}

	public void setStatistic(Statistic stat) {
		this.stat = stat;
	}

	public void swap(int a, int b) {
		if (!swapped) {
			stat.addStorage(1);
		}
		Integer temp = get(a);
		set(a, get(b));
		set(b, temp);
	}

	/**
	 * @precondition first and last are both valid indices in v
	 * @postcondition Shift all the elements from v[first+1] to v[last] to the
	 *                left one space, overwriting (and losing) the value in
	 *                v[first]. The element in v[last[ will remain untouched (so
	 *                v[last] will be a duplicate of v[last-1]).
	 * 
	 * @param fromIndex
	 * @param toIndex
	 */
	public void shiftLeft(int fromIndex, int toIndex) {
		assert (fromIndex >= 0 && fromIndex < size());
		assert (toIndex >= 0 && toIndex < size());
		for (int i = fromIndex; i < toIndex; i++) {
			set(i, get(i + 1));
		}
	}

	/**
	 * @precondition first and last are both valid indices in v
	 * @postcondition Shift all the elements from v[first] to v[last-1] to the
	 *                right one space, overwriting (and losing) the value in
	 *                v[last]. The element in v[first] will remain untouched (so
	 *                v[first] will be a duplicate of v[first+1]).
	 * 
	 * @param fromIndex
	 * @param toIndex
	 */
	public void shiftRight(int fromIndex, int toIndex) {
		assert (fromIndex >= 0 && fromIndex < size());
		assert (toIndex >= 0 && toIndex < size());
		for (int i = toIndex; i > fromIndex; i--) {
			set(i, get(i - 1));
		}
	}

	@Override
	public String toString() {
		return super.toString() + " " + stat.getAlgorithm();
	}
}
