import java.awt.event.*;

/**
 * A set of controls for an individual tank.
 * 
 * @author Seth Battis
 *
 */
public class ControlSet {
	
	/**
	 * Represents the left control.
	 */
	public static final int LEFT = 0;
	
	/**
	 * Represents the right control.
	 */
	public static final int RIGHT = 1;
	
	/**
	 * Represents the up control.
	 */
	public static final int UP = 2;
	
	/**
	 * Represents the down control.
	 */
	public static final int DOWN = 3;
	
	/**
	 * Represents the fire control.
	 */
	public static final int FIRE = 4;
	
	/**
	 * The total count of controls available in a ControlSet.
	 */
	public static final int KEY_COUNT = 5;
	
	/**
	 * Represents an erroneous control.
	 */
	public static final int ERROR = -1;
	
	/**
	 * The keyboard equivalent of the left control.
	 */
	private int leftKey = KeyEvent.VK_LEFT;
	
	/**
	 * The keyboard equivalent of the right control.
	 */
	private int rightKey = KeyEvent.VK_RIGHT;
	
	/**
	 * The keyboard equivalent of the up control.
	 */
	private int upKey = KeyEvent.VK_UP;
	
	/**
	 * The keyboard equivalent of the down control.
	 */
	private int downKey = KeyEvent.VK_DOWN;
	
	/**
	 * The keyboard equivalent of the fire control.
	 */
	private int fireKey = KeyEvent.VK_PERIOD;
	
	/**
	 * Default constructor (arrow keys to control direction and speed, period (.) fires).
	 */
	public ControlSet() {
		// use defaults
	}

	/**
	 * Define a specific alternate ControlSet.
	 * @param left One of the KeyEvent.VK_ constants.
	 * @param right One of the KeyEvent.VK_ constants.
	 * @param up One of the KeyEvent.VK_ constants.
	 * @param down One of the KeyEvent.VK_ constants.
	 * @param fire One of the KeyEvent.VK_ constants.
	 */
	public ControlSet(int left, int right, int up, int down, int fire) {
		leftKey = left;
		rightKey = right;
		upKey = up;
		downKey = down;
		fireKey = fire;
	}
	
	/**
	 * Convert a KeyEvent VK_ constant into a control.
	 * @param keyCode The KeyEvent keycode to be converted.
	 * @return A ControlSet control constant equivalent to the keyboard input, or ControlSet.ERROR if no equivalent.
	 */
	public int getCommand(int keyCode) {
		if (keyCode == leftKey) {
			return LEFT;
		} else if (keyCode == rightKey) {
			return RIGHT;
		} else if (keyCode == upKey) {
			return UP;
		} else if (keyCode == downKey) {
			return DOWN;
		} else if (keyCode == fireKey) {
			return FIRE;
		} else {
			return ERROR;
		}
	}
}
