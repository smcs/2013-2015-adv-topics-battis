package boids;

import objectdraw.*;
import java.awt.*;

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
		Color myColor = Formulae.generateRandomColor();
		left.setColor(myColor);
		right.setColor(myColor);
		bottom.setColor(myColor);
		caption1.setColor(myColor);
		caption2.setColor(myColor);
		start();
	}

	public void run() {
		double x1, y1, x2, y2, x3, y3;
		double theta1, theta2, theta3, r;
		while (boid.isUpdating()) {
			r = Formulae.distance(0, 0, environment.getIconWidth() / 2, environment.getIconHeight() / 2);
			theta1 = Math.acos(environment.getIconHeight() / 2 / r);
			theta2 = boid.getAngle() - theta1;
			theta3 = Math.PI - (boid.getAngle() + theta1);
			x1 = boid.getX() + environment.getIconHeight() / 2 * Math.cos(boid.getAngle());
			y1 = boid.getY() + environment.getIconHeight() / 2 * Math.sin(boid.getAngle());
			x2 = boid.getX() - r * Math.cos(theta2);
			y2 = boid.getY() - r * Math.sin(theta2);
			x3 = boid.getX() + r * Math.cos(theta3);
			y3 = boid.getY() - r * Math.sin(theta3);
			left.setStart(x1, y1);
			left.setEnd(x2, y2);
			bottom.setStart(left.getEnd());
			bottom.setEnd(x3, y3);
			right.setStart(bottom.getEnd());
			right.setEnd(left.getStart());
			
			if (environment.isCaptionsEnabled()) {
				caption1.setText(String.format("(%.1f, %.1f)", boid.getX(), boid.getY()));
				caption2.setText(String.format("angle = %.2f / speed = %.1f", boid.getAngle(), boid.getSpeed()));
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
