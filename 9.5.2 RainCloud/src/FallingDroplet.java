import objectdraw.*;
import java.awt.*;

public class FallingDroplet extends ActiveObject {
	private static final int SIZE = 4;
	private static final int Y_STEP = 10;
	private static final int DELAY_TIME = 50;
	
	private FilledOval dropletGraphic;
	private FilledRect collector;
	
	public FallingDroplet(Location initialLocation, DrawingCanvas canvas, FilledRect aCollector) {
		dropletGraphic = new FilledOval(initialLocation, SIZE, SIZE, canvas);
		collector = aCollector;
		start();
	}
	
	public void run() {
		while (dropletGraphic.getY() < collector.getY()) {
			dropletGraphic.move(0, Y_STEP);
			pause(DELAY_TIME);
		}
		
		dropletGraphic.removeFromCanvas();
		if (collector.getY() > 0) {
			collector.setHeight(collector.getHeight() + SIZE / 4);
			collector.move(0, -SIZE/4);
		}
	}
}
