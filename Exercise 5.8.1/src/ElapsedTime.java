import objectdraw.*;
import java.awt.*;

public class ElapsedTime extends WindowController {
	private static final Location TEXT_POS = new Location(30, 50);

	private double lastTime;

	private boolean hasClicked; // Ensures no value displayed after first click
	
	private Text message;

	public void begin() {
		message = new Text("Please click the mouse at least twice.", TEXT_POS,
				canvas);

		hasClicked = false;
	}

	public void onMouseClick(Location point) {
		if (hasClicked) {
			message.setText((System.currentTimeMillis() - lastTime) / 1000.0
					+ " seconds elapsed between the last two mouse clicks.");
		}
		lastTime = System.currentTimeMillis();
		hasClicked = true;
	}
}