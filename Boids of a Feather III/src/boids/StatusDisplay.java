package boids;

import objectdraw.*;

public class StatusDisplay extends ActiveObject {

	public static final double MARGIN = 5;
	public static final double TEXT_HEIGHT = 15;

	private Flock flock;
	private Environment environment;
	private Text wallWeight, crashWeight, friendsWeight, flowWeight;

	public StatusDisplay(Flock flock, Environment environment,
			DrawingCanvas canvas) {
		this.flock = flock;
		this.environment = environment;
		wallWeight = new Text(environment.getStayInBoundsWeight(), MARGIN, MARGIN,
				canvas);
		crashWeight = new Text(environment.getAvoidCollisionWeight(), MARGIN, MARGIN
				+ TEXT_HEIGHT, canvas);
		friendsWeight = new Text(environment.getFriendsWeight(), MARGIN, MARGIN
				+ TEXT_HEIGHT * 2, canvas);
		flowWeight = new Text(environment.getFlowWeight(), MARGIN, MARGIN
				+ TEXT_HEIGHT * 3, canvas);
		start();
	}

	public void run() {
		while (flock.isUpdating()) {
			wallWeight.setText(environment.getStayInBoundsWeight() + " * stay in-bounds");
			crashWeight.setText(environment.getAvoidCollisionWeight()
					+ " * avoid crashes");
			friendsWeight.setText(environment.getFriendsWeight()
					+ " * find friends");
			flowWeight.setText(environment.getFlowWeight()
					+ " * go with the flow");
			pause(environment.getIconRefreshDelay());
		}
	}
}
