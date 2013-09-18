import objectdraw.*;
import java.awt.*;

public class TouchyWindow extends WindowController {

	public void onMousePress(Location point) {
		Text myText = new Text("I'm Touched", 40, 50, canvas);
	}
	
	public void onMouseRelease(Location point) {
		canvas.clear();
	}
}
