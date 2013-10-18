import objectdraw.*;
import java.awt.*;

public class RainCloud extends ActiveObject{
	private static final int MAX_DROPS = 300;
	private static final int DELAY_TIME = 100;
	
	private DrawingCanvas canvas;
	private FilledRect collector;
	
	public RainCloud(DrawingCanvas aCanvas, FilledRect aCollector) {
		canvas = aCanvas;
		collector = aCollector;
		start();
	}
	
	public void run() {
		RandomIntGenerator xGenerator = new RandomIntGenerator(0, canvas.getWidth());
		int dropCount = 0;
		while (dropCount < MAX_DROPS) {
			new FallingDroplet(new Location(xGenerator.nextValue(), 0), canvas, collector);
			pause(DELAY_TIME);
			dropCount++;
		}
	}
}
