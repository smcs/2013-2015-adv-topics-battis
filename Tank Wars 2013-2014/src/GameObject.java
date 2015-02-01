import objectdraw.*;
import java.awt.*;

/**
 * An object that can be involved in a collision.
 * 
 * @author Seth Battis
 */
public class GameObject extends ActiveObject {

	/**
	 * @param other Another object on the canvas.
	 * @return True if the other object visually overlaps with this game object.
	 */
	public boolean overlaps(FilledRect other) {
		return false;
	}
	
	/**
	 * @param other Another object on the canvas.
	 * @return True if the other object visually overlaps with this game object.
	 */
	public boolean overlaps(FilledOval other) {
		return false;
	}

	/**
	 * @return The current location of this game object.
	 */
	public Location getLocation() {
		return null;
	}
	
	// TODO Find a better place for this method. Doesn't really belong in this class...
	/**
	 * A handy helper function to generate a random color within a
	 * specific palette. Code from {@link http://stackoverflow.com/a/43235/294171}
	 * @param mix
	 * @return
	 */
	protected Color generateRandomColor() {
		Color mix = new Color(255, 255, 255);
	    RandomIntGenerator random = new RandomIntGenerator(0, 256);
	    int red = random.nextValue();
	    int green = random.nextValue();
	    int blue = random.nextValue();

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }

	    Color color = new Color(red, green, blue);
	    return color;
	}
}
