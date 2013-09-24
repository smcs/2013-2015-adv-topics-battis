import objectdraw.*;
import java.awt.*;

public class GrowMan extends WindowController {
	// Amount each body part grow by (should be even)
	private static final int GROW = 2;
	// Initial size of head
	private static final int HEAD_SIZE = 6;
	private static final int LIMB_SIZE = 5; // Initial displacement of ends //
											// of limbs from body, both
											// horizontally and vertically
	private static final int HEAD_START = 50; // x and y coordinate of //
												// initial starting point
	// Coordinates of body parts
	private static final int BODY_X = HEAD_START + HEAD_SIZE / 2;
	private static final int NECK_Y = HEAD_START + HEAD_SIZE;
	private static final int ARMPIT_Y = HEAD_START + 2 * HEAD_SIZE;
	private static final int BODY_END = HEAD_START + 3 * HEAD_SIZE;
	private static final int FEET_Y = BODY_END + LIMB_SIZE;
	private static final int ARMS_Y = ARMPIT_Y - LIMB_SIZE;
	private static final int LEFT_X = BODY_X - LIMB_SIZE;
	private static final int RIGHT_X = BODY_X + LIMB_SIZE;
	// Instance variables for body parts
	private FramedOval head;
	private Line body, leftArm, rightArm, leftLeg, rightLeg;

	// Draw all body parts
	public void begin() {
		head = new FramedOval(HEAD_START, HEAD_START, HEAD_SIZE, HEAD_SIZE,
				canvas);
		body = new Line(BODY_X, NECK_Y, BODY_X, BODY_END, canvas);
		leftArm = new Line(BODY_X, ARMPIT_Y, LEFT_X, ARMS_Y, canvas);
		rightArm = new Line(BODY_X, ARMPIT_Y, RIGHT_X, ARMS_Y, canvas);
		leftLeg = new Line(BODY_X, BODY_END, LEFT_X, FEET_Y, canvas);
		rightLeg = new Line(BODY_X, BODY_END, RIGHT_X, FEET_Y, canvas);
	}

	// Make each body part grow after mouse click
	public void onMouseClick(Location point) {
		head.setWidth(head.getWidth() + GROW);
		head.setHeight(head.getHeight() + GROW);
		head.move(-GROW / 2, -GROW / 2);
		body.setEnd(new Location(BODY_X, body.getEnd().getY() + GROW));
		body.move(0, GROW / 2);
		leftArm.setEnd(new Location(leftArm.getEnd().getX() - GROW / 2, leftArm
				.getEnd().getY() - GROW / 2));
		leftArm.move(0, GROW);
		rightArm.setEnd(new Location(rightArm.getEnd().getX() + GROW / 2,
				rightArm.getEnd().getY() - GROW / 2));
		rightArm.move(0, GROW);
		leftLeg.setEnd(new Location(leftLeg.getEnd().getX() - GROW / 2, leftLeg
				.getEnd().getY() + GROW / 2));
		leftLeg.move(0, 3 * GROW / 2);
		rightLeg.setEnd(new Location(rightLeg.getEnd().getX() + GROW / 2,
				rightLeg.getEnd().getY() + GROW / 2));
		rightLeg.move(0, 3 * GROW / 2);
	}
}