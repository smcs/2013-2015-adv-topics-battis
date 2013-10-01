
public class Pencil {
	private int length;
	private boolean isSharp;
	
	/**
	 * Default Constructor
	 */
	public Pencil() {
		length = 10;
		isSharp = false;
	}
	
	/**
	 * Copy Constructor
	 * @param other
	 */
	public Pencil(Pencil other) {
		this.length = other.length;
		this.isSharp = other.isSharp;
	}
	
	/**
	 * Length Constructor
	 * @param newLength
	 */
	public Pencil(int newLength) {
		length = newLength;
		isSharp = false;
	}
	
	/**
	 * Detailed Constructor
	 * @param newLength
	 * @param sharpness
	 */
	public Pencil(int newLength, boolean sharpness) {
		length = newLength;
		isSharp = sharpness;
	}
		
	public void drawALine() {
		length -= 1;
		isSharp = false;
	}
	
	public void sharpen() {
		length -= 2;
		isSharp = true;
	}
}
