import objectdraw.*;


public class MyMath {
	public static double distance(Location a, Location b) {
		return distance (a.getX(), a.getY(), b.getX(), b.getY());
	}
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public static boolean overlaps (BoundingBox a, BoundingBox b) {
		if ( // whole rectangle overlap
				( // horizontal overlap
					( // a's left edge overlaps b
						(a.getX() >= b.getX()) && (a.getX() <= (b.getX() + b.getWidth()))
					) || 
					(  // a's right edge overlaps b
						((a.getX() + a.getWidth()) >= b.getX()) && ((a.getX() + a.getWidth()) <= (b.getX() + b.getWidth()))
					)
				) &&
				( // vertical overlap
					( // a's top edge overlaps b
						(a.getY() >= b.getY()) && (a.getY() <= (b.getY() + b.getHeight()))
					) ||
					( // a's bottom edge overlaps b
						((a.getY() + a.getHeight()) >= b.getY()) && ((a.getY() + a.getHeight()) <= (b.getY() + b.getHeight()))
					)
				)
			) {
			return true;
		}
		return false;
	}
	
	public static boolean overlaps(BoundingCircle circle, BoundingBox box) {
		return overlaps(box, circle);
	}
	
	public static boolean overlaps(BoundingBox box, BoundingCircle circle) {
		/* the center of the circle is in the box or... */
		Location center = circle.getCenter();
		if (box.contains(center) ||
				(
					/* ...the center is within the distance of the radius from an edge of the box or... */
					(
						(center.getX() >= box.getX()) && (center.getX() <= (box.getX() + box.getWidth())) &&
							(
								(Math.abs(center.getY() - box.getY()) < circle.getRadius()) ||
								(Math.abs(center.getY() - (box.getY() + box.getHeight())) < circle.getRadius())
							)
					) ||
					(
						(center.getY() >= box.getY()) && (center.getY() <= (box.getY() + box.getHeight())) &&
							(
								(Math.abs(center.getX() - box.getX()) < circle.getRadius()) ||
								(Math.abs(center.getX() - (box.getX() + box.getWidth())) < circle.getRadius())
							)
					)
				) ||
				
				/* ...the center is within the distance of a radius from a corner of the box */
				(distance(center, box.getLocation()) < circle.getRadius()) ||
				(distance(center.getX(), center.getY(), box.getLocation().getX() + box.getWidth(), box.getLocation().getY()) < circle.getRadius()) ||
				(distance(center.getX(), center.getY(), box.getLocation().getX(), box.getLocation().getY() + box.getHeight()) < circle.getRadius()) ||
				(distance(center.getX(), center.getY(), box.getLocation().getX() + box.getWidth(), box.getLocation().getY() + box.getHeight()) < circle.getRadius())
			) {
			return true;
		}
		return false;
	}
	
	public static boolean overlaps(BoundingCircle a, BoundingCircle b) {
		if (MyMath.distance(a.getCenter(), b.getCenter()) < (a.getRadius() + b.getRadius())) {
			return true;
		}
		return false;
		
	}
}
