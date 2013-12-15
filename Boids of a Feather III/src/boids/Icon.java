package boids;

import objectdraw.*;

public class Icon extends ActiveObject {

	private Boid boid;
	private Environment environment;
	private FilledOval icon;
	private Text caption1, caption2;

	public Icon(Boid boid, Environment environment, DrawingCanvas canvas) {
		this.boid = boid;
		this.environment = environment;
		icon = new FilledOval(boid.getLocation(), environment.getIconWidth(),
				environment.getIconHeight(), canvas);
		caption1 = new Text("", boid.getLocation(), canvas);
		caption2 = new Text("", boid.getLocation(), canvas);
		if (!environment.captionsEnabled()) {
			caption1.hide();
			caption2.hide();
		}
		start();
	}

	public void run() {
		while (boid.isUpdating()) {
			icon.moveTo(boid.getLocation());
			if (environment.captionsEnabled()) {
				caption1.setText(String.format("(%.1f, %.1f)", boid.getX(), boid.getY()));
				caption2.setText(String.format("angle = %.1f / speed = %.1f", boid.getAngle(), boid.getSpeed()));
				caption1.moveTo(boid.getX() + environment.getIconWidth(), boid.getY());
				caption2.moveTo(boid.getX() + environment.getIconWidth(), boid.getY() + 15);
				if (caption1.isHidden()) {
					caption1.show();
					caption2.show();
				}
			} else if (!caption1.isHidden()) {
				caption1.hide();
				caption2.hide();
			}
			pause(environment.getIconRefreshDelay());
		}
	}
}
