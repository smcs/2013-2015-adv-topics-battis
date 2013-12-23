package boids;

import objectdraw.*;

public class Formulae {
	/**
	 * @param a
	 * @param b
	 * @return the distance between Locations a and b
	 */
	public static double distance(Location a, Location b) {
		return distance(a.getX(), a.getY(), b.getX(), b.getY());
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return the distance between points (x1, y1) and (x2, y2)
	 */
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	/**
	 * @param startPoint
	 * @param velocity
	 * @return the end point of a line with the length of the velocity's seed,
	 *         starting at startPoint, drawn on the bearing of the angle of the
	 *         velocity
	 */
	public static Location endPoint(Location startPoint, Velocity velocity) {
		return endPoint(startPoint.getX(), startPoint.getY(),
				velocity.getAngle(), velocity.getSpeed());
	}

	/**
	 * @param x
	 * @param y
	 * @param angle (in radians)
	 * @param speed
	 * @return the end point of a line with the length speed, starting at (x,
	 *         y), drawn on the bearing angle
	 */
	public static Location endPoint(double x, double y, double angle,
			double speed) {
		return new Location(x + speed * Math.cos(angle), y + speed
				* Math.sin(angle));
	}

	/**
	 * @param startPoint
	 * @param endPoint
	 * @return the bearing from startPoint to endPoint (in radians)
	 */
	public static double bearing(Location startPoint, Location endPoint) {
		return bearing(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return the bearing (in radians) from (x1, y1) to (x2, y2)
	 */
	public static double bearing(double x1, double y1, double x2, double y2) {
		return Math.atan2(y2 - y1, x2 - x1) % (Math.PI * 2);
	}
}
