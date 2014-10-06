import objectdraw.*;
import java.awt.*;

public class Dicey extends WindowController {
	// Text display coordinates
	private static final int LEFT_MARGIN = 10;
	private static final int MESSAGE_Y = 10;
	private static final int ROLL_Y = 60;
	private static final int RESULT_Y = 120;

	// The dice
	private int die1, die2, die3;

	// Generates new roll for the dice
	private RandomIntGenerator dieGen = new RandomIntGenerator(1, 6);

	// The display
	private Text message, roll, result;

	public void begin() {
		// Create display on canvas
		message = new Text("Click to roll", LEFT_MARGIN, MESSAGE_Y, canvas);
		roll = new Text("", LEFT_MARGIN, ROLL_Y, canvas);
		result = new Text("", LEFT_MARGIN, RESULT_Y, canvas);
	}

	public void onMouseClick(Location point) {
		// Roll dice and display rolls and result die1 = dieGen.nextValue();
		die1 = dieGen.nextValue();
		die2 = dieGen.nextValue();
		die3 = dieGen.nextValue();
		roll.setText("You rolled " + die1 + ", " + die2 + " and " + die3);
		if (die1 == die2) {
			if (die1 == die3) {
				// 3 of a kind
				result.setText("You rolled three " + die1 + "'s!");
			} else {// One pair
				result.setText("You rolled a pair of " + die1 + "'s!");
			}
		} else if (die1 == die3) {
			// One pair
			result.setText("You rolled a pair of " + die1 + "'s");
		} else if (die2 == die3) {
			// One pair
			result.setText("You rolled a pair of " + die2 + "'s");
		} else {// Nothing!
			result.setText("You did not roll anything interesting!");
		}
	}
}