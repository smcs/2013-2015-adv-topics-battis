import objectdraw.*;

public class Boid extends ActiveObject {
	public static final double SIZE = 15;
	public static final double SPEED_VARIANCE = 0.5;
	public static final double SPEED = 1;
	private static final double TURN = Math.PI / 12;
	private static final double TURN_VARIANCE = 0.5;
	private static final double HEAD_TO_BODY_RATIO = 0.3;
	private static final double WING_TO_BODY_RATIO = 0.5;
	private static final double WING_RAKE = Math.PI / 4;
	
	private Location location;
	private DrawingCanvas canvas;
	private Flock flock;
	private Line body, leftWing, rightWing;
	private double heading;
	private double step;
	private int iterations, maxIterations;
	
	public Boid(Flock f, int iterations, DrawingCanvas c) {
		flock = f;
		maxIterations = iterations;
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
		body = new Line(location, location, canvas);
		leftWing = new Line(location, location, canvas);
		rightWing = new Line(location, location, canvas);
		alignToHeading();
	}

	private void alignToHeading() {
		body.setStart(
				location.getX() - (Math.cos(heading) * SIZE * (1 - HEAD_TO_BODY_RATIO)),
				location.getY() - (Math.sin(heading) * SIZE * (1 - HEAD_TO_BODY_RATIO))
			);
		body.setEnd(
				location.getX() + (Math.cos(heading) * SIZE * HEAD_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading) * SIZE * HEAD_TO_BODY_RATIO)
			);
		
		leftWing.setStart(location);
		leftWing.setEnd(
				location.getX() + (Math.cos(heading - Math.PI + WING_RAKE) * SIZE * WING_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading - Math.PI + WING_RAKE) * SIZE * WING_TO_BODY_RATIO)
			);
		
		rightWing.setStart(location);
		rightWing.setEnd(
				location.getX() + (Math.cos(heading + Math.PI - WING_RAKE) * SIZE * WING_TO_BODY_RATIO),
				location.getY() + (Math.sin(heading + Math.PI - WING_RAKE) * SIZE * WING_TO_BODY_RATIO)
			);
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
		iterations++;
		if (!marginAvoidance()) {
			flock();
		}
		location.translate(
				Math.cos(heading) * step,
				Math.sin(heading) * step
			);
	}
	
	public void run() {
		while (iterations < maxIterations) {
			step();
			alignToHeading();
			pause(World.REFRESH_RATE);
		}
		flock.reportFinished();
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
