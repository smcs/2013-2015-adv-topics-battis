import java.awt.event.*;

public class KeyboardBinding {
	private static int nextSerialNumber = 0;
	private int serialNumber;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int SHOOT = 4;
	
	private static final int[][] keyBindings = {
		{
			KeyEvent.VK_A,
			KeyEvent.VK_D,
			KeyEvent.VK_W,
			KeyEvent.VK_S,
			KeyEvent.VK_Q
		},
		{
			KeyEvent.VK_J,
			KeyEvent.VK_L,
			KeyEvent.VK_I,
			KeyEvent.VK_K,
			KeyEvent.VK_U
		},
		{
			KeyEvent.VK_LEFT,
			KeyEvent.VK_RIGHT,
			KeyEvent.VK_UP,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_CONTROL
		}
	};
	
	public KeyboardBinding() {
		serialNumber = nextSerialNumber++;
	}
	
	public int left() {
		return keyBindings[serialNumber % keyBindings.length][LEFT];
	}
	public int right() {
		return keyBindings[serialNumber % keyBindings.length][RIGHT];
	}
	public int up() {
		return keyBindings[serialNumber % keyBindings.length][UP];
	}
	public int down() {
		return keyBindings[serialNumber % keyBindings.length][DOWN];
	}
	
	public int shoot() {
		return keyBindings[serialNumber % keyBindings.length][SHOOT];
	}

}
