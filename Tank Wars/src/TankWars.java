import objectdraw.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TankWars extends WindowController {
	
	/**
	 * The number of tanks playing the game.
	 */
	public static final int NUM_TANKS = 3;
	
	/**
	 * The number of bullets allowed per tank.
	 */
	public static final int NUM_BULLETS = 10;
	
	/**
	 * How frequently (in milliseconds) should the animated objects refresh their positions?
	 */
	public static final int REFRESH_RATE = 10;
	
	/**
	 * How frequently should keys that are held down repeat (in milliseconds)?
	 */
	public static final int KEY_REPEAT_DELAY = 50;
	
	/**
	 * How close can tanks be regenerated to the edge of the canvas or to each other?
	 */
	public static final double MARGIN = 15;
	
	/**
	 * The amount that a tank's speed is increased/decreased by up/down keys.
	 */
	public static final double SPEED_INCREMENT = .1;
	
	/**
	 * The amount that a tank's direction is altered by left/right keys (in radians).
	 */
	public static final double ANGLE_INCREMENT = Math.PI / 20;
	
	/**
	 * The diameter of a tank (in pixels).
	 */
	public static final double TANK_SIZE = 10;
	
	/**
	 * The length of the cannon barrel (in pixels);
	 */
	public static final double CANNON_LENGTH = 5;
	
	/**
	 * The diameter of an individual bullet (in pixels).
	 */
	public static final double BULLET_SIZE = 3;
	
	/**
	 * The additional speed imparted to a bullet (in addition to the firing tank's velocity).
	 */
	public static final double BULLET_VELOCITY = 3;
	
	/**
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
	
	/**
	 * Canvas accessor method.
	 * @return a reference to the game's canvas.
	 */
	public DrawingCanvas getCanvas() {
		return canvas;
	}

	/**
	 * Is the game over?
	 * @return true if a tank has won.
	 */
	public boolean isGameOver() {
		return false;
	}
}