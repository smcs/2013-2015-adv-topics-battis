import objectdraw.*;

public class BoidDrawer extends ActiveObject {
	private Boid boid;
	private Line body, leftWing, rightWing;
	
	public BoidDrawer (Boid b, DrawingCanvas canvas) {
		boid = b;
		body = new Line(boid.getLocation(), boid.getLocation(), canvas);
		leftWing = new Line(boid.getLocation(), boid.getLocation(), canvas);
		rightWing = new Line(boid.getLocation(), boid.getLocation(), canvas);
		redraw();
		start();
	}

	private void redraw() {
		Location location = boid.getLocation();
		double heading = boid.getHeading();
		body.setStart(
				location.getX() - (Math.cos(heading) * Boid.SIZE * (1 - Boid.HEAD_TO_BODY_RATIO)),
				location.getY() - (Math.sin(heading) * Boid.SIZE * (1 - Boid.HEAD_TO_BODY_RATIO))
			);
		body.setEnd(
				location.getX() + (Math.cos(heading) * Boid.SIZE * Boid.HEAD_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading) * Boid.SIZE * Boid.HEAD_TO_BODY_RATIO)
			);
		
		leftWing.setStart(boid.getLocation());
		leftWing.setEnd(
				location.getX() + (Math.cos(heading - Math.PI + Boid.WING_RAKE) * Boid.SIZE * Boid.WING_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading - Math.PI + Boid.WING_RAKE) * Boid.SIZE * Boid.WING_TO_BODY_RATIO)
			);
		
		rightWing.setStart(location);
		rightWing.setEnd(
				location.getX() + (Math.cos(heading + Math.PI - Boid.WING_RAKE) * Boid.SIZE * Boid.WING_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading + Math.PI - Boid.WING_RAKE) * Boid.SIZE * Boid.WING_TO_BODY_RATIO)
			);
	}
	
	public void run() {
		while (true) {
			redraw();
			pause(World.REFRESH_RATE);
		}
	}
}
