import objectdraw.*;

public class Boid {
	public static final double SIZE = 15;
	public static final double SPEED_VARIANCE = 0.5;
	public static final double SPEED = 1;
	public static final double TURN = Math.PI / 12;
	public static final double TURN_VARIANCE = 0.5;
	public static final double HEAD_TO_BODY_RATIO = 0.3;
	public static final double WING_TO_BODY_RATIO = 0.5;
	public static final double WING_RAKE = Math.PI / 4;
	
	private Location location;
	private DrawingCanvas canvas;
	private Flock flock;
	private Line body, leftWing, rightWing;
	private double heading;
	private double step;
	
	public Boid(Flock f, DrawingCanvas c) {
		flock = f;
		canvas = c;

		RandomIntGenerator widthGen = new RandomIntGenerator(0, canvas.getWidth());
		RandomIntGenerator heightGen = new RandomIntGenerator(0, canvas.getHeight());
		RandomDoubleGenerator headingGen = new RandomDoubleGenerator(0, Math.PI * 2);
		RandomDoubleGenerator speedGen = new RandomDoubleGenerator(SPEED - SPEED_VARIANCE, SPEED + SPEED_VARIANCE);

		do {
			location = new Location (widthGen.nextValue(), heightGen.nextValue());
		} while (
				location.getX() < World.BORDER_AVOIDANCE ||
				location.getY() < World.BORDER_AVOIDANCE ||
				location.getX() > canvas.getWidth() - World.BORDER_AVOIDANCE ||
				location.getY() > canvas.getHeight() - World.BORDER_AVOIDANCE
			);
		heading = headingGen.nextValue();
		step = speedGen.nextValue();
		
		new BoidDrawer(this, canvas);
	}

	private boolean marginAvoidance() {
		if (
				location.getX() < World.BORDER_AVOIDANCE ||
				location.getY() < World.BORDER_AVOIDANCE ||
				location.getX() > canvas.getWidth() - World.BORDER_AVOIDANCE ||
				location.getY() > canvas.getHeight() - World.BORDER_AVOIDANCE
			) {
			turnRight();
			return true;
		}
		return false;
	}
	
	private boolean boidAvoidance(Boid other) {
		if (distanceTo(other) < World.BOID_AVOIDANCE) {
			turnLeft();
			return true;
		}
		return false;
	}
	
	private void flock() {
		Boid[] subflock = flock.subflock(this, World.SUBFLOCK_SIZE);
		double flockHeading = 0;
		Location flockLocation = new Location(0, 0);
		
		for (int i = 0; i < subflock.length; i++) {
			if (boidAvoidance(subflock[i])) {
				return;
			}
			flockHeading += subflock[i].getHeading();
			flockLocation.translate(subflock[i].getLocation().getX(), subflock[i].getLocation().getX());
		}
		flockHeading /= subflock.length;
		flockLocation = new Location(
				flockLocation.getX() / subflock.length,
				flockLocation.getY() / subflock.length
			);
		if (flockHeading < heading) {
			turnLeft();
		} else if (flockHeading > heading) {
			turnRight();
		} else {
			double headingToFlock = Math.acos((location.getX() - flockLocation.getX()) / location.distanceTo(flockLocation));
			if (headingToFlock < heading) {
				turnLeft();
			} else {
				turnRight();
			}
		}
	}
	
	private void step() {
		if (!marginAvoidance()) {
			flock();
		}
		location.translate(
				Math.cos(heading) * step,
				Math.sin(heading) * step
			);
		canvas.repaint();
	}
	
	public void iteration() {
		step();
	}
	
	public Location getLocation() {
		return location;
	}
	
	public double distanceTo(Boid other) {
		return location.distanceTo(other.location);
	}
	
	public double getHeading() {
		return heading;
	}
	
	private void turnLeft() {
		RandomDoubleGenerator turnGen = new RandomDoubleGenerator(TURN * (1-TURN_VARIANCE), TURN * (1+TURN_VARIANCE));
		heading -= turnGen.nextValue();
	}
	
	private void turnRight() {
		RandomDoubleGenerator turnGen = new RandomDoubleGenerator(TURN * (1-TURN_VARIANCE), TURN * (1+TURN_VARIANCE));
		heading += turnGen.nextValue();
	}
}
