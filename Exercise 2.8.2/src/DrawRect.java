import objectdraw.*;
import java.awt.*;

public class DrawRect extends WindowController {
	private FramedRect frame;

	public void onMousePress(Location point) {
		frame = new FramedRect(point, 100, 100, canvas);
	}

	public void onMouseDrag(Location point) {
		frame.moveTo(point);
	}

	public void onMouseRelease(Location point) {
		new FilledRect(point, 100, 100, canvas);
	}
}