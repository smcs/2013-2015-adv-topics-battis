import objectdraw.*;
import java.awt.*;

public class Chase extends WindowController {
	private static final double TIME_LIMIT = 1.5; // Time available to click
	private static final double HAT_HEIGHT = 60;
	private static final double HAT_OFFSET = 5; // Overlap between hat and face
	private FunnyFace funFace; // Funny face to be chased
	private TopHat hat;// Hat for funny face
	private Timer stopWatch;// Timer to see if click is fast enough
	private Text infoText;// Text item to be displayed during game

	// Generators to get new x and y coords.
	private RandomIntGenerator randXGen, randYGen;
	private boolean playing; // Is player playing or waiting to start

	// Set up timer and funny face for game

	public void begin() {
		stopWatch = new Timer();
		randXGen = new RandomIntGenerator(0, canvas.getWidth());
		randYGen = new RandomIntGenerator(0, canvas.getHeight());
		funFace = new FunnyFace(canvas.getWidth() / 2, canvas.getHeight() / 3,
				canvas);
		hat = new TopHat(funFace.getX(), funFace.getY() + HAT_OFFSET
				- HAT_HEIGHT, funFace.getWidth(), HAT_HEIGHT, canvas);
		infoText = new Text("Click to start chasing the FunnyFace.",
				canvas.getWidth() / 3, canvas.getHeight() / 2, canvas);
		playing = false;
	}

	// Determine if user won and move funny face if necessary
	public void onMouseClick(Location point) {
		if (!playing) { // Start playing
			playing = true;
			infoText.setText("Click quickly on the FunnyFace to win!");
			funFace.moveTo(new Location(randXGen.nextValue(), randYGen
					.nextValue()));
			hat.moveTo(funFace.getX(), funFace.getY() + HAT_OFFSET - HAT_HEIGHT);
			stopWatch.reset();
		} else if (!(funFace.contains(point) || hat.contains(point))) { // missed
																		// the
																		// funny
																		// face
			infoText.setText("You missed!");
			funFace.moveTo(new Location(randXGen.nextValue(), randYGen
					.nextValue()));
			hat.moveTo(funFace.getX(), funFace.getY() + HAT_OFFSET - HAT_HEIGHT);
			hat.changeColor();
			stopWatch.reset();
		} else if (stopWatch.elapsedSeconds() <= TIME_LIMIT) { // got it in time
			playing = false;
			infoText.setText("You got the FunnyFace in time. Click to restart.");
		} else { // user was too slow
			infoText.setText("Too slow!");
			funFace.moveTo(new Location(randXGen.nextValue(), randYGen
					.nextValue()));
			hat.moveTo(funFace.getX(), funFace.getY() + HAT_OFFSET - HAT_HEIGHT);
			hat.changeColor();
			stopWatch.reset();
		}
	}
}