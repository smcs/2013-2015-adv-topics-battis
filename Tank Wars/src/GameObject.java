import objectdraw.*;
import java.awt.*;

public class GameObject extends ActiveObject {

	public boolean overlaps(FilledRect other) {
		return false;
	}
	
	public boolean overlaps(FilledOval other) {
		return false;
	}
	
	public Location getLocation() {
		return null;
	}
	
	/**
	 * http://stackoverflow.com/a/43235/294171
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
