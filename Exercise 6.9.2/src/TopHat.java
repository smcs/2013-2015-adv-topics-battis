import objectdraw.*;
import java.awt.*;

public class TopHat {
	// Height of top and bottom parts of hat
	private static final double OVAL_HEIGHT = 10;

	// Components of hat
	private FilledOval topOval, bottomOval;
	private FilledRect cylinder;

	private Location topLeft; // Top-left corner of hat

	private boolean isBlack; // If hat is black or not

	// Draw black hat
	public TopHat(double left, double top, double width, double height,
			DrawingCanvas canvas) {
		topOval = new FilledOval(left + width / 6, top, 2 * width / 3,
				OVAL_HEIGHT, canvas);
		cylinder = new FilledRect(left + width / 6, top + OVAL_HEIGHT / 2,
				2 * width / 3, height - OVAL_HEIGHT / 2, canvas);
		bottomOval = new FilledOval(left, top + height - OVAL_HEIGHT, width,
				OVAL_HEIGHT, canvas);
		topLeft = new Location(left, top);
		isBlack = true;
	}

	// Move hat components
	public void move(double dx, double dy) {
		topOval.move(dx, dy);
		cylinder.move(dx, dy);
		bottomOval.move(dx, dy);
		topLeft.translate(dx, dy);
	}

	public void moveTo(double xLoc, double yLoc) {
		this.move(xLoc - topLeft.getX(), yLoc - topLeft.getY());
	}

	public boolean contains(Location point) {
		return (topOval.contains(point) || cylinder.contains(point) || bottomOval
				.contains(point));
	}

	// Set color of hat components to aColor
	public void setColor(Color aColor) {
		topOval.setColor(aColor);
		cylinder.setColor(aColor);
		bottomOval.setColor(aColor);
	}

	public void changeColor() {
		// If hat is black change color to red,
		// else (hat is red) change color to black
		if (isBlack) {
			this.setColor(Color.RED);
			isBlack = false;
		} else {// else (hat is red) change
			// color to black
			this.setColor(Color.BLACK);
			isBlack = true;
		}
	}
}
