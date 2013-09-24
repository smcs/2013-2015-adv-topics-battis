import objectdraw.*;
import java.awt.*;

public class RandomBox extends WindowController { // Box starting location
	private static final Location ORIGIN = new Location(0, 0);
	// Box components
	private FilledRect box;
	private FramedRect frame;
	// Box attributes to be randomized and printed
	private int boxWidth;
	private int boxHeight;
	private Location boxLocation;
	private Color boxColor;
	// Random number generators
	private RandomIntGenerator boxGen = new RandomIntGenerator(20, 120);
	private RandomIntGenerator colorGen = new RandomIntGenerator(0, 255);

	private void newBox() {
		if (box != null) {
			box.hide();
		}
		if (frame != null) {
			frame.hide();
		}
		// Create box, randomize attributes, center it on screen,
		boxWidth = boxGen.nextValue();
		boxHeight = boxGen.nextValue();
		box = new FilledRect(ORIGIN, boxWidth, boxHeight, canvas);
		boxColor = new Color(colorGen.nextValue(), colorGen.nextValue(),
				colorGen.nextValue());
		box.setColor(boxColor);
		boxLocation = new Location((canvas.getWidth() - boxWidth) / 2,
				(canvas.getHeight() - boxHeight) / 2);
		box.moveTo(boxLocation);
		// Add frame
		frame = new FramedRect(boxLocation, boxWidth, boxHeight, canvas);
		box.show();
		frame.show();
	}
	
	public void begin() {
		newBox();
	}

	public void onMouseClick(Location point) {
		// print box attributes
		System.out.println("The box is located at: " + boxLocation);
		System.out.println("It is " + boxWidth + " pixels wide and "
				+ boxHeight + " pixels high");
		System.out.println("The red color component is: " + boxColor.getRed());
		System.out.println("The green color component is: "
				+ boxColor.getGreen());
		System.out
				.println("The blue color component is: " + boxColor.getBlue());
	}

	public void onMouseExit(Location point) { // Hide box components
	}

	public void onMouseEnter(Location point) {
		newBox();
	}
}