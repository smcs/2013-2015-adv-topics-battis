package boids;

import objectdraw.*;

import java.util.*;

public class Boid {

	private Flock flock;
	private Environment environment;
	private DrawingCanvas canvas;
	private Location location;
	private Velocity velocity;

	public Boid(Flock flock, Environment environment, DrawingCanvas canvas) {
		Random generator = new Random();
		this.flock = flock;
		this.environment = environment;
		this.canvas = canvas;
		location = new Location(
				environment.getWallMargin() + generator.nextDouble()
						* (canvas.getWidth() - 2 * environment.getWallMargin()),
				environment.getWallMargin()
						+ generator.nextDouble()
						* (canvas.getHeight() - 2 * environment.getWallMargin()));
		velocity = new Velocity(generator.nextDouble() * (Math.PI * 2),
				environment.getMinimumSpeed()
						+ generator.nextDouble()
						* (environment.getMaximumSpeed() - environment
								.getMinimumSpeed()));
		new Icon(this, environment, canvas);
	}

	public void step() {
		/* start by setting our new bearing as our current bearing */
		double weight = 1;
		double newBearing = velocity.getAngle();

		/*
		 * a list of all of our neighbors who influence our navigational
		 * decisions
		 */
		LinkedList<Boid> neighbors = flock.getNeighbors(this);
		if (neighbors.size() > 0) {
			ListIterator<Boid> neighborsIterator = neighbors.listIterator();

			/* cumulative coordinates for our neighbors, potential collisions */
			double cumNeighborX = 0, cumNeighborY = 0, cumNeighborAngle = 0, cumNeighborSpeed = 0;
			double cumCollisionX = 0, cumCollisionY = 0, cumCollisionAngle = 0, cumCollisionSpeed = 0;

			/* how many potential collisions are present */
			int crashCount = 0;

			/*
			 * examine neighbors to identify potential collisions, calculate
			 * centers of mass, direction for neighbors and potential collisions
			 */
			while (neighborsIterator.hasNext()) {
				Boid b = neighborsIterator.next();
				cumNeighborX += b.getX();
				cumNeighborY += b.getY();
				cumNeighborAngle += b.getAngle();
				cumNeighborSpeed += b.getSpeed();
				if (Formulae.distance(location, b.getLocation()) < environment
						.getMaximumCollisionRadius()) {
					crashCount++;
					cumCollisionX += b.getX();
					cumCollisionY += b.getY();
					cumCollisionAngle += b.getAngle();
					cumCollisionSpeed += b.getSpeed();
				}
			}

			/*
			 * calculate average centers of mass, velocity for neighbors,
			 * potential collisions
			 */
			Location neighborLocation = new Location(cumNeighborX
					/ neighbors.size(), cumNeighborY / neighbors.size());
			Velocity neighborVelocity = new Velocity(cumNeighborAngle
					/ neighbors.size(), cumNeighborSpeed / neighbors.size());

			/* factor weighted rules into new bearing */
			weight += environment.getFriendsWeight();
			newBearing += environment.getFriendsWeight()
					* Formulae.bearing(location, neighborLocation);
			weight += environment.getFlowWeight();
			newBearing += environment.getFlowWeight()
					* neighborVelocity.getAngle();
			weight += environment.getStayInBoundsWeight();
			newBearing += environment.getStayInBoundsWeight()
					* headingToStayInBounds();
			if (crashCount > 0) { // avoid divide by zero errors
				Location collisionLocation = new Location(cumCollisionX
						/ crashCount, cumCollisionY / crashCount);
				Velocity collisionVelocity = new Velocity(cumCollisionAngle
						/ crashCount, cumCollisionSpeed / crashCount);
				weight += environment.getAvoidCollisionWeight();
				newBearing += environment.getAvoidCollisionWeight()
						* ((Formulae.bearing(location, collisionLocation) + Math.PI) % (Math.PI * 2));
			}
		}

		/*
		 * calculate final newBearing by taking average based on weights of
		 * rules applied
		 */
		newBearing /= weight;

		/*
		 * FIXME If the boid hits the center of the right side of the
		 * environment at a heading close enough to zero radians, it may
		 * oscillate back and forth trying to get to the heading PI... and end
		 * up leaving the screen.
		 */
		/*
		 * calculate actual new bearing with a limit on the maximum amount the
		 * boid can turn
		 */
		double turnToNewBearing = Math.PI
				- Math.abs(Math.abs(velocity.getAngle() - newBearing) - Math.PI);
		if (turnToNewBearing < environment.getMaximumBearingChange()) {
			velocity.setAngle(newBearing);
		} else if (Math.abs((velocity.getAngle() + environment
				.getMaximumBearingChange()) % (Math.PI * 2) - newBearing) < Math
				.abs((velocity.getAngle() - environment
						.getMaximumBearingChange())
						% (Math.PI * 2)
						- newBearing)) {
			velocity.setAngle(velocity.getAngle()
					+ environment.getMaximumBearingChange());
		} else {
			velocity.setAngle(velocity.getAngle()
					- environment.getMaximumBearingChange());
		}

		location = nextLocation();

	}

	/**
	 * 
	 * @return next location (if wrap-around is enabled, wrap-around on the
	 *         edges of the environment)
	 */
	private Location nextLocation() {
		Location nextLocation = Formulae.endPoint(location, velocity);
		if (environment.isWrapAround()) {
			if (nextLocation.getX() < 0) {
				nextLocation = new Location(canvas.getWidth()
						+ nextLocation.getX(), nextLocation.getY());
			} else if (nextLocation.getX() > canvas.getWidth()) {
				nextLocation = new Location(nextLocation.getX()
						- canvas.getWidth(), nextLocation.getY());
			}
			if (nextLocation.getY() < 0) {
				nextLocation = new Location(nextLocation.getX(),
						canvas.getHeight() + location.getY());
			} else if (nextLocation.getY() > canvas.getHeight()) {
				nextLocation = new Location(nextLocation.getX(),
						nextLocation.getY() - canvas.getHeight());
			}
		}
		return nextLocation;
	}

	private double headingToStayInBounds() {
		Location canvasCenter = new Location(canvas.getWidth() / 2,
				canvas.getHeight() / 2);
		Location nextLocation = Formulae.endPoint(location, velocity);

		/*
		 * if our next step on our current heading is outside of the canvas,
		 * calculate a heading back towards the center of the canvas
		 */
		if (nextLocation.getX() < environment.getWallMargin()
				|| nextLocation.getX() > (canvas.getWidth() - environment
						.getWallMargin())
				|| nextLocation.getY() < environment.getWallMargin()
				|| nextLocation.getY() > (canvas.getHeight() - environment
						.getWallMargin())) {
			return Formulae.bearing(location, canvasCenter);
		}

		return velocity.getAngle();
	}

	public Location getLocation() {
		/*
		 * I would normally return a copy of the location object here, but since
		 * Location objects cannot be modified, I don't need to protect myself
		 * against accidental modification!
		 */
		return location;
	}

	public double getY() {
		return location.getY();
	}

	public double getX() {
		return location.getX();
	}

	public double getSpeed() {
		return velocity.getSpeed();
	}

	public double getAngle() {
		return velocity.getAngle();
	}

	public boolean isUpdating() {
		return flock.isUpdating();
	}

	public Velocity getVelocity() {
		return new Velocity(velocity);
	}
}
