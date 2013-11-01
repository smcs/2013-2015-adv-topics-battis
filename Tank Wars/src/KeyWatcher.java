import objectdraw.*;
import java.awt.event.*;

public class KeyWatcher extends ActiveObject implements KeyListener {
	
	private Tank tank;
	private ControlSet controls;
	private TankWars game;
	
	private boolean[] keysPressed = new boolean[ControlSet.KEY_COUNT];
	
	public KeyWatcher(Tank aTank, ControlSet aControlSet, TankWars aGame) {
		tank = aTank;
		controls = aControlSet;
		game = aGame;
		game.addKeyListener(this);
		game.getCanvas().addKeyListener(this);
		start();
	}
	
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

	@Override
	public void keyPressed(KeyEvent e) {
		int keyPressed = controls.getCommand(e.getKeyCode());
		if (keyPressed != ControlSet.ERROR) {
			keysPressed[keyPressed] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyPressed = controls.getCommand(e.getKeyCode());
		if (keyPressed != ControlSet.ERROR) {
			keysPressed[keyPressed] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

}
