import objectdraw.*;
import java.awt.*;

public class Circle extends ActiveObject {

	private FramedOval circle;
	private Location center;
	private double radius;
	private boolean expanding;
	private CircleCollection circles;

	public Circle(Location center, CircleCollection circles, DrawingCanvas canvas) {
		this.center = center;
		radius = 1;
		circle = new FramedOval(center.getX() + radius, center.getY() + radius,
				radius * 2, radius * 2, canvas);
		expanding = true;
		this.circles = circles;
		start();
	}

	public void run() {
		while (!circles.overlap(this)) {
			radius++;
			circle.move(-1, -1);
			circle.setSize(radius * 2, radius * 2);
			pause(100);
		}
	}
	
	public Location getCenter() {
		return center;
	}
	
	public double getRadius() {
		return radius;
	}
}
