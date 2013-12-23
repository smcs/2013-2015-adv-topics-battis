package boids;

import objectdraw.*;

public class Icon extends ActiveObject {

	private Boid boid;
	private Environment environment;
	private Line left, right, bottom;
	private Text caption1, caption2;

	public Icon(Boid boid, Environment environment, DrawingCanvas canvas) {
		this.boid = boid;
		this.environment = environment;
		left = new Line(boid.getLocation(), boid.getLocation(), canvas);
		right = new Line(boid.getLocation(), boid.getLocation(), canvas);
		bottom = new Line(boid.getLocation(), boid.getLocation(), canvas);
		caption1 = new Text("", boid.getLocation(), canvas);
		caption2 = new Text("", boid.getLocation(), canvas);
		if (!environment.isCaptionsEnabled()) {
			caption1.hide();
			caption2.hide();
		}
		start();
	}

	public void run() {
		while (boid.isUpdating()) {
			left.setStart(boid.getX() + environment.getIconHeight() / 2 * Math.cos(boid.getAngle()), boid.getY() + environment.getIconHeight() / 2 * Math.sin(boid.getAngle()));
			left.setEnd(boid.getX() - environment.getIconHeight() / 2 * Math.cos(boid.getAngle()) - environment.getIconWidth() / 2 * Math.cos(Math.PI / 2 - boid.getAngle()), boid.getY() - environment.getIconHeight() / 2 * Math.sin(boid.getAngle()) + environment.getIconWidth() / 2 * Math.sin(Math.PI / 2 - boid.getAngle()));
			bottom.setStart(left.getEnd());
			bottom.setEnd(boid.getX() - environment.getIconHeight() / 2 * Math.cos(boid.getAngle()) + environment.getIconWidth() / 2 * Math.cos(Math.PI / 2 - boid.getAngle()), boid.getY() - environment.getIconWidth() / 2 * Math.sin(boid.getAngle()) - environment.getIconWidth() / 2 * Math.sin(Math.PI / 2 - boid.getAngle()));
			right.setStart(bottom.getEnd());
			right.setEnd(left.getStart());
			if (environment.isCaptionsEnabled()) {
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
