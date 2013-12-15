package boids;

import objectdraw.*;

public class Formulae {
	public static double distance(Location a, Location b) {
		return distance(a.getX(), a.getY(), b.getX(), b.getY());
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static Location endPoint(Location location, Velocity velocity) {
		return endPoint(location.getX(), location.getY(), velocity.getAngle(),
				velocity.getSpeed());
	}

	public static Location endPoint(double x, double y, double angle,
			double speed) {
		return new Location(x + speed * Math.cos(angle), y + speed
				* Math.sin(angle));
	}

	public static double heading(Location start, Location end) {
		return heading(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public static double heading(double x1, double y1, double x2, double y2) {
		return Math.acos((x1 - x2) / distance(x1, y1, x2, y2));
	}
}
