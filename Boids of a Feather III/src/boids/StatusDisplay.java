package boids;

import objectdraw.*;

public class StatusDisplay extends ActiveObject {

	public static final double MARGIN = 5;
	public static final double TEXT_HEIGHT = 15;

	private Flock flock;
	private Environment environment;
	private Text wallWeight, crashWeight, friendsWeight, flowWeight,
			iconDimensions;

	public StatusDisplay(Flock flock, Environment environment,
			DrawingCanvas canvas) {
		this.flock = flock;
		this.environment = environment;
		wallWeight = new Text("", MARGIN, MARGIN, canvas);
		wallWeight.setColor(environment.getTextColor());
		crashWeight = new Text("", MARGIN, MARGIN + TEXT_HEIGHT, canvas);
		crashWeight.setColor(environment.getTextColor());
		friendsWeight = new Text("", MARGIN, MARGIN + TEXT_HEIGHT * 2, canvas);
		friendsWeight.setColor(environment.getTextColor());
		flowWeight = new Text("", MARGIN, MARGIN + TEXT_HEIGHT * 3, canvas);
		flowWeight.setColor(environment.getTextColor());
		iconDimensions = new Text("", MARGIN, MARGIN + TEXT_HEIGHT * 4, canvas);
		iconDimensions.setColor(environment.getTextColor());
		start();
	}

	public void run() {
		while (flock.isUpdating()) {
			wallWeight.setText(String.format("%.1f * stay in-[B]ounds",
					environment.getStayInBoundsWeight()));
			crashWeight.setText(String.format("%.1f * avoid [C]rashes",
					environment.getAvoidCollisionWeight()));
			friendsWeight.setText(String.format("%.1f  * find [F]riends",
					environment.getFriendsWeight()));
			flowWeight.setText(String.format("%.1f  * go with the f[L]ow",
					environment.getFlowWeight()));
			iconDimensions.setText(String.format(
					"%.1f x %.1f icons ([W] x [H])",
					environment.getIconWidth(), environment.getIconHeight()));
			pause(environment.getIconRefreshDelay());
		}
	}
}
