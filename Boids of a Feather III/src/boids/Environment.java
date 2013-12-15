package boids;

import objectdraw.*;

import java.awt.event.*;

public class Environment extends WindowController implements KeyListener {

	private static final int CANVAS_WIDTH = 750;
	private static final int CANVAS_HEIGHT = CANVAS_WIDTH;

	private double iconWidth = CANVAS_WIDTH / 100;
	private double iconHeight = iconWidth;

	private double stepDelay = 10;
	private double iconRefreshDelay = stepDelay;
	private boolean captionsEnabled = true;

	private int flockSize = 100;

	private double minimumSpeed = 1;
	private double maximumSpeed = 3;
	private double maximumTurnAngle = Math.PI / 180 * 15;
	private int duration = -1; /*
									 * value > 0 limits number of simulated
									 * steps
									 */
	private double crashRadius = iconWidth;
	private double wallMargin = 25;
	private boolean wrapAround = false;

	private double subflockRange = CANVAS_WIDTH / 2;

	private double wallWeight = 1;
	private double crashWeight = 0;
	private double friendsWeight = 0;
	private double flowWeight = 0;

	private Flock flock;
	private int modifier;

	public void begin() {
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		flock = new Flock(this, canvas);
		new StatusDisplay(flock, this, canvas);
		System.out.println(canvas.getHeight());
		System.out.println(canvas.getWidth());
	}

	public double getIconWidth() {
		return iconWidth;
	}

	public double getIconHeight() {
		return iconHeight;
	}

	public double getStepDelay() {
		return stepDelay;
	}

	public double getIconRefreshDelay() {
		return iconRefreshDelay;
	}

	public boolean captionsEnabled() {
		return this.captionsEnabled;
	}

	public int getFlockSize() {
		return flockSize;
	}

	public double getMinimumSpeed() {
		return minimumSpeed;
	}

	public double getMaximumSpeed() {
		return maximumSpeed;
	}

	public double getMaximumTurnAngle() {
		return maximumTurnAngle;
	}

	public int getDuration() {
		return duration;
	}

	public double getCrashRadius() {
		return crashRadius;
	}

	public double getWallMargin() {
		return wallMargin;
	}

	public boolean wrapAround() {
		return wrapAround;
	}
	
	public double getSubflockRange() {
		return subflockRange;
	}

	public double getWallWeight() {
		return wallWeight;
	}

	public double getCrashWeight() {
		return crashWeight;
	}

	public double getFriendsWeight() {
		return friendsWeight;
	}

	public double getFlowWeight() {
		return flowWeight;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_C:
		case KeyEvent.VK_F:
		case KeyEvent.VK_L:
			modifier = e.getKeyCode();
			break;
		case KeyEvent.VK_UP:
			switch (modifier) {
			case KeyEvent.VK_W:
				wallWeight++;
				break;
			case KeyEvent.VK_C:
				crashWeight++;
				break;
			case KeyEvent.VK_F:
				friendsWeight++;
				break;
			case KeyEvent.VK_L:
				flowWeight++;
			}
			break;
		case KeyEvent.VK_DOWN:
			switch (modifier) {
			case KeyEvent.VK_W:
				wallWeight--;
				break;
			case KeyEvent.VK_C:
				crashWeight--;
				break;
			case KeyEvent.VK_F:
				friendsWeight--;
				break;
			case KeyEvent.VK_L:
				flowWeight--;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
