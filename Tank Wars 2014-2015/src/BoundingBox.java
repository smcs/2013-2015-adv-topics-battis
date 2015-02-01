import objectdraw.*;

public class BoundingBox extends FramedRect {

	public BoundingBox(double x, double y, double width, double height,
			DrawingCanvas canvas) {
		super(x, y, width, height, canvas);
	}

	public BoundingBox(Location location, double width, double height,
			DrawingCanvas canvas) {
		super(location, width, height, canvas);
	}

}
