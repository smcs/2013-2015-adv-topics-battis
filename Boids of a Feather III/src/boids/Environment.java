package boids;

import objectdraw.*;

@SuppressWarnings("serial")
public class Environment extends WindowController {

	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 800;

	private double iconWidth = 10;
	private double iconHeight = iconWidth;

	private double stepDelay = 10;
	private double iconRefreshDelay = stepDelay;
	private boolean captionsEnabled = false;

	private int flockSize = 100;

	private double minimumSpeed = 1;
	private double maximumSpeed = 3;
	private double maximumBearingChange = Math.PI / 180;
	private int duration = -1; /*
								 * value > 0 limits number of simulated steps
								 */
	private double subflockRange = 250;
	private int subflockSize = -1; /* TODO value > 0 limits size of subflock */

	private double maximumCollisionRadius = 50;
	private double wallMargin = 100;
	private boolean wrapAround = true;

	private double stayInBoundsWeight = 1;
	private double avoidCollisionWeight = 2;
	private double friendsWeight = 2;
	private double flowWeight = 1;

	private Flock flock;

	public void begin() {
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		flock = new Flock(this, canvas);
		new StatusDisplay(flock, this, canvas);
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

	public boolean isCaptionsEnabled() {
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

	public double getMaximumBearingChange() {
		return maximumBearingChange;
	}

	public int getDuration() {
		return duration;
	}

	public double getMaximumCollisionRadius() {
		return maximumCollisionRadius;
	}

	public double getWallMargin() {
		return wallMargin;
	}

	public boolean isWrapAround() {
		return wrapAround;
	}

	public double getSubflockRange() {
		return subflockRange;
	}
	
	public int getSubflockSize() {
		return subflockSize;
	}

	public double getStayInBoundsWeight() {
		return stayInBoundsWeight;
	}

	public double getAvoidCollisionWeight() {
		return avoidCollisionWeight;
	}

	public double getFriendsWeight() {
		return friendsWeight;
	}

	public double getFlowWeight() {
		return flowWeight;
	}
}
