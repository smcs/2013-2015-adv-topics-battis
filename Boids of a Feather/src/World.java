import objectdraw.*;

public class World extends WindowController {
	
	private static final int FLOCK_SIZE = 150;
	public static final int REFRESH_RATE = 10;
	public static final int SUBFLOCK_SIZE = 10;
	public static final double BORDER_AVOIDANCE = Boid.SIZE; // pixels
	public static final double BOID_AVOIDANCE = Boid.SIZE / 2; // pixels
	
	private Flock boids;
	
	public void begin() {
		boids = new Flock(FLOCK_SIZE, canvas);
	}
}
