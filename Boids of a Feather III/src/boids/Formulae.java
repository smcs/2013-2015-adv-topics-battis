package boids;

import objectdraw.*;

public class Formulae {
    public static double distance(Location a, Location b) {
	return distance(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static double distance(double ax, double ay, double bx, double by) {
	return Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
    }

    public static Location endPoint(Location location, Velocity velocity) {
	return new Location(location.getX()
		+ (velocity.getSpeed() * Math.cos(velocity.getAngle())),
		location.getY()
			+ (velocity.getSpeed() * Math.sin(velocity.getSpeed())));
    }

    public static double heading(Location start, Location end) {
	return Math.acos((start.getY() - end.getY()) / distance(start, end));
    }
}
