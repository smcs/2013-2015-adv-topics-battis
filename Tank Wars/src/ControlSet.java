import java.awt.event.*;

public class ControlSet {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int FIRE = 4;
	public static final int ERROR = -1;
	
	private int leftKey = KeyEvent.VK_LEFT;
	private int rightKey = KeyEvent.VK_RIGHT;
	private int upKey = KeyEvent.VK_UP;
	private int downKey = KeyEvent.VK_DOWN;
	private int fireKey = KeyEvent.VK_PERIOD;
	
	public ControlSet() {
		// use defaults
	}
	
	public ControlSet(int left, int right, int up, int down, int fire) {
		leftKey = left;
		rightKey = right;
		upKey = up;
		downKey = down;
		fireKey = fire;
	}
	
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
