import objectdraw.*;
import java.awt.*;

public class FunnyFace {
	private static final double FACE_HEIGHT = 60, // Dimensions of the face
			FACE_WIDTH = 60, EYE_OFFSET = 20, // Eye location and size
			EYE_DIAMETER = 8, MOUTH_WIDTH = FACE_WIDTH / 2, // Dimensions of the
			MOUTH_HEIGHT = 10; // mouth
	private FramedOval head, leftEye, rightEye, mouth; // Parts of the face //
														// Create pieces of
														// funny face

	public FunnyFace(double left, double top, DrawingCanvas canvas) {
		head = new FramedOval(left, top, FACE_WIDTH, FACE_HEIGHT, canvas);
		mouth = new FramedOval(left + (FACE_WIDTH - MOUTH_WIDTH) / 2, top + 2
				* FACE_HEIGHT / 3, MOUTH_WIDTH, MOUTH_HEIGHT, canvas);
		leftEye = new FramedOval(left + EYE_OFFSET - EYE_DIAMETER / 2, top
				+ EYE_OFFSET, EYE_DIAMETER, EYE_DIAMETER, canvas);
		rightEye = new FramedOval(left + FACE_WIDTH - EYE_OFFSET - EYE_DIAMETER
				/ 2, top + EYE_OFFSET, EYE_DIAMETER, EYE_DIAMETER, canvas);
	}

	// Move funny face by (dx, dy)
	public void move(double dx, double dy) {
		head.move(dx, dy);
		leftEye.move(dx, dy);
		rightEye.move(dx, dy);
		mouth.move(dx, dy);
	}

	// Determine whether point is inside funny face
	public boolean contains(Location point) {
		return head.contains(point);
	}

	public void moveTo(Location loc) {
		this.move(loc.getX() - head.getX(), loc.getY() - head.getY());
	}

	public double getX() {
		return head.getX();
	}

	public double getY() {
		return head.getY();
	}

	public double getWidth() {
		return head.getWidth();
	}
}