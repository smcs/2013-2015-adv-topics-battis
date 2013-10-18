import objectdraw.*;
import java.awt.*;

public class AnimatedRain extends WindowController {
	private FilledRect collector;
	private RainCloud cloud;
	
	public void begin() {
		collector = new FilledRect(0, canvas.getHeight(), canvas.getWidth(), 1, canvas);
		cloud = new RainCloud(canvas, collector);
	}
}
