import objectdraw.*;

public class BoundingCircle extends FramedOval {

	public BoundingCircle(double x, double y, double width, double height,
			DrawingCanvas canvas) {
		super(x, y, width, height, canvas);
		// TODO Auto-generated constructor stub
	}
	
	public Location getCenter() {
		return new Location(getX() + getWidth() / 2.0, getY() + getHeight() / 2.0);
	}
	
	public double getRadius() {
		return getWidth() / 2.0;
	}

}
