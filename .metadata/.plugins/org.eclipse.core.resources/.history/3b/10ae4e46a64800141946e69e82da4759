import objectdraw.*;
import java.awt.*;

public class ExpandingCircles extends WindowController {

	private CircleCollection circles;
	
	public void begin() {
		circles = new CircleCollection(canvas);
	}
	
	public void onMouseClick(Location click) {
		if (circles.available(click)) {
			circles.add(click);
		}
	}
}
