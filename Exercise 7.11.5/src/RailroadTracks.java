import objectdraw.*;
import java.awt.*;

// Draws railroad tracks
public class RailroadTracks extends WindowController {
	private static final int MAX_NUM_BARS = 10;
	private static final double INIT_BAR_X = 25;
	private static final double INIT_BAR_Y = 250;
	private static final int INIT_BAR_LENGTH = 200;
	private static final int BAR_LENGTH_DECREASE = 20;
	private static final int BAR_HEIGHT = 12;
	private static final int BAR_OFFSET_X = BAR_LENGTH_DECREASE / 2;
	private static final int BAR_OFFSET_Y = 25;
	private static final int CROSS_OFFSET = 10;

	public void begin() {
		int numBars = 0;
		double barX = INIT_BAR_X;
		double barY = INIT_BAR_Y;
		double barLength = INIT_BAR_LENGTH;
		while (numBars < MAX_NUM_BARS) {
			new FilledRect(barX, barY, barLength, BAR_HEIGHT, canvas);
			numBars++;
			if (numBars < MAX_NUM_BARS) {
				barX = barX + BAR_OFFSET_X;
				barY = barY - BAR_OFFSET_Y;
				barLength = barLength - BAR_LENGTH_DECREASE;
			}
		}
		new Line(barX + CROSS_OFFSET, barY - CROSS_OFFSET / 2, INIT_BAR_X
				+ CROSS_OFFSET, INIT_BAR_Y + BAR_HEIGHT + CROSS_OFFSET, canvas);
		new Line(barX + barLength - CROSS_OFFSET, barY - CROSS_OFFSET,
				INIT_BAR_X + INIT_BAR_LENGTH - CROSS_OFFSET, INIT_BAR_Y
						+ BAR_HEIGHT + CROSS_OFFSET, canvas);
	}
}