import objectdraw.*;
import java.awt.event.*;

/**
 * A threaded process to watch for keystrokes controlling one
 * specific tank.
 * 
 * @author Seth Battis
 */
public class KeyWatcher extends ActiveObject implements KeyListener {

	/**
	 * The tank being controlled by the KeyWatcher.
	 */
	private Tank tank;
	
	/**
	 * The control set for the tank being controlled..
	 */
	private ControlSet controls;
	
	/**
	 * The game within which the tank is playing.
	 */
	private TankWars game;
	
	/**
	 * Whether or not any of the keys controlling this tank are being
	 * held down.
	 */
	private boolean[] keysPressed = new boolean[ControlSet.KEY_COUNT];

	/**
	 * Construct a new KeyWatcher connected to a specific tank.
	 * @param aTank The tank to control with this KeyWatcher.
	 * @param aControlSet The control set to use to control the tank.
	 * @param aGame The game within which the Tank is playing.
	 */
	public KeyWatcher(Tank aTank, ControlSet aControlSet, TankWars aGame) {
		tank = aTank;
		controls = aControlSet;
		game = aGame;
		game.addKeyListener(this);
		game.getCanvas().addKeyListener(this);
		start();
	}
	
	/**
	 * Continuously check for keys that are held down and pass those
	 * commands to the tank.
	 * {@inheritDoc}
	 */
	public void run() {
		while (!game.isGameOver()) {
			if (keysPressed[ControlSet.LEFT]) {
				tank.turnLeft();
			}
			if (keysPressed[ControlSet.RIGHT]) {
				tank.turnRight();
			}
			if (keysPressed[ControlSet.UP]) {
				tank.speedUp();
			}
			if (keysPressed[ControlSet.DOWN]) {
				tank.slowDown();
			}
			if (keysPressed[ControlSet.FIRE]) {
				tank.fire();
			}
			pause(TankWars.KEY_REPEAT_DELAY);
		}
	}

	/**
	 * Start tracking a key being held down (if it is one of the tank controls).
	 * @param e A KeyEvent representing the key being pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyPressed = controls.getCommand(e.getKeyCode());
		if (keyPressed != ControlSet.ERROR) {
			keysPressed[keyPressed] = true;
		}
	}

	/**
	 * Stop tracking a key being held down (if it is one of the tank controls).
	 * @param e A KeyEvent representing the key being released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int keyPressed = controls.getCommand(e.getKeyCode());
		if (keyPressed != ControlSet.ERROR) {
			keysPressed[keyPressed] = false;
		}
	}

	/**
	 * Do nothing
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
