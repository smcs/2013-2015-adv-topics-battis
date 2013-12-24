package boids;

import objectdraw.*;
import java.awt.event.*;

public class Adjuster extends ActiveObject implements KeyListener {

	private static final double BIG_INCREMENT = 1.0;
	private static final double SMALL_INCREMENT = 0.1;
	private Environment environment;
	private Flock flock;
	private int modifier;

	public Adjuster(Flock flock, Environment environment, DrawingCanvas canvas) {
		this.flock = flock;
		this.environment = environment;
		environment.addKeyListener(this);
		canvas.addKeyListener(this);
		start();
	}

	public void run() {
		while (flock.isUpdating()) {
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_B:
		case KeyEvent.VK_C:
		case KeyEvent.VK_F:
		case KeyEvent.VK_L:
			modifier = e.getKeyCode();
			break;
		case KeyEvent.VK_UP:
			switch (modifier) {
			case KeyEvent.VK_B:
				environment.incrementStayInBoundsWeight(BIG_INCREMENT);
				break;
			case KeyEvent.VK_C:
				environment.incremementAvoidCollisionWeight(BIG_INCREMENT);
				break;
			case KeyEvent.VK_F:
				environment.incrementFriendsWeight(BIG_INCREMENT);
				break;
			case KeyEvent.VK_L:
				environment.incrementFlowWeight(BIG_INCREMENT);
				break;
			}
			break;
		case KeyEvent.VK_DOWN:
			switch (modifier) {
			case KeyEvent.VK_B:
				environment.incrementStayInBoundsWeight(-BIG_INCREMENT);
				break;
			case KeyEvent.VK_C:
				environment.incremementAvoidCollisionWeight(-BIG_INCREMENT);
				break;
			case KeyEvent.VK_F:
				environment.incrementFriendsWeight(-BIG_INCREMENT);
				break;
			case KeyEvent.VK_L:
				environment.incrementFlowWeight(-BIG_INCREMENT);
				break;
			}
			break;
		case KeyEvent.VK_LEFT:
			switch (modifier) {
			case KeyEvent.VK_B:
				environment.incrementStayInBoundsWeight(-SMALL_INCREMENT);
				break;
			case KeyEvent.VK_C:
				environment.incremementAvoidCollisionWeight(-SMALL_INCREMENT);
				break;
			case KeyEvent.VK_F:
				environment.incrementFriendsWeight(-SMALL_INCREMENT);
				break;
			case KeyEvent.VK_L:
				environment.incrementFlowWeight(-SMALL_INCREMENT);
				break;
			}
			break;
		case KeyEvent.VK_RIGHT:
			switch (modifier) {
			case KeyEvent.VK_B:
				environment.incrementStayInBoundsWeight(SMALL_INCREMENT);
				break;
			case KeyEvent.VK_C:
				environment.incremementAvoidCollisionWeight(SMALL_INCREMENT);
				break;
			case KeyEvent.VK_F:
				environment.incrementFriendsWeight(SMALL_INCREMENT);
				break;
			case KeyEvent.VK_L:
				environment.incrementFlowWeight(SMALL_INCREMENT);
				break;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_B:
		case KeyEvent.VK_C:
		case KeyEvent.VK_F:
		case KeyEvent.VK_L:
			if (modifier == e.getKeyCode()) {
				modifier = -1;
			}
			break;
		}
	}

}
