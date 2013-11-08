import objectdraw.*;

public class Flock {
	private Boid[] boids;
	private DrawingCanvas canvas;
	
	public Flock(int flockSize, DrawingCanvas canvas) {
		boids = new Boid[flockSize];
		for (int i = 0; i < boids.length; i++) {
			boids[i] = new Boid(this, canvas);
		}
		for (int i = 0; i < boids.length; i++) {
			boids[i].start();
		}
		
		this.canvas = canvas;
	}
	
	public Boid[] subflock(Boid b, int size) {
		Boid[] subflock = new Boid[size];
		double minDistance = 0, prevMin;
		int closest = 0;
		
		for (int i = 0; i < subflock.length; i++) {
			prevMin = minDistance;
			minDistance = Double.MAX_VALUE;
			for (int j = 0; j < boids.length; j++) {
				if (b != boids[j]) {
					double distance = b.distanceTo(boids[j]);
					if (
							(
								i == 0 && 
								distance < minDistance
							) ||
							(
								i > 0 &&
								distance < minDistance &&
								distance >= prevMin &&
								boids[j] != subflock[i-1]
							)
						) {
						minDistance = distance;
						closest = j;
					}
				}
			}
			subflock[i] = boids[closest];
		}
		return subflock;
	}
}
