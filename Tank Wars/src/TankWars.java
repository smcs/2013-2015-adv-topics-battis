import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class TankWars extends WindowController {
	/*
	 * Game parameters
	 */
	public static final int NUM_TANKS = 3;
	public static final int NUM_BULLETS = 10;
	public static final int REFRESH_RATE = 10;
	public static final double MARGIN = 15;
	public static final double SPEED_INCREMENT = .1;
	public static final double ANGLE_INCREMENT = Math.PI / 20;
	public static final double TANK_SIZE = 10;
	public static final double BARREL_LENGTH = 5;
	
	/*
	 * All the things in the game that can collide with each other (and
	 * with bullets)
	 */
	private static GameObject[] gameObjects;
		
	/**
	 * Set up the game board and get things rolling!
	 */
	@Override
	public void begin() {
		ControlSet[] controlSets = new ControlSet[] {
			new ControlSet(), // default arrow keys
			new ControlSet(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_F),
			new ControlSet(KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_H)
		};
		gameObjects = new GameObject[NUM_TANKS]; // TODO eventually this should include walls and anything else collidable
		for (int i = 0; i < NUM_TANKS; i++) {
			/*
			 *  FIXME should really be making sure that we have at least as
			 *  many control sets as we have tanks...
			 */
			gameObjects[i] = new Tank(this, controlSets[i], gameObjects);
		}
	}
	
	public DrawingCanvas getCanvas() {
		return canvas;
	}
	
	public boolean isGameOver() {
		return false;
	}
}