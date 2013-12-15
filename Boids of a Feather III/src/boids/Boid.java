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
		Boid b;
		double cumulativeNeighborAngle = 0, cumulativeNeighborSpeed = 0, cumulativeNeighborX = 0, cumulativeNeighborY = 0, cumulativeCrashX = 0, cumulativeCrashY = 0, newHeading, nextSpeed, weight = 0;
		int crashCount = 0;
		Velocity neighborVelocity;
		Location neighborLocation, crashLocation, canvasCenter = new Location(
				canvas.getWidth() / 2, canvas.getHeight() / 2), nextLocation = Formulae
				.endPoint(location, velocity);

		/* examine the locations and velocities of our neighbors */
		LinkedList<Boid> neighbors = flock.getNeighbors(this);
		ListIterator<Boid> neighborsIterator = neighbors.listIterator();
		while (neighborsIterator.hasNext()) {
			b = neighborsIterator.next();
			cumulativeNeighborAngle += b.getAngle();
			cumulativeNeighborSpeed += b.getSpeed();
			cumulativeNeighborX += b.getX();
			cumulativeNeighborY += b.getY();
			if (Formulae.distance(location, b.getLocation()) < environment
					.getCrashRadius()) {
				crashCount++;
				cumulativeCrashX += b.getX();
				cumulativeCrashY += b.getY();
			}
		}
		/* average velocity of all neighbors */
		neighborVelocity = new Velocity(cumulativeNeighborAngle
				/ neighbors.size(), cumulativeNeighborSpeed / neighbors.size());

		/* average location of all neighbors */
		neighborLocation = new Location(cumulativeNeighborX / neighbors.size(),
				cumulativeNeighborY / neighbors.size());

		/* average location of potential crashes */
		crashLocation = new Location(cumulativeCrashX / crashCount,
				cumulativeCrashY / crashCount);

		/*
		 * start calculating the new heading as the weighted sum of the heading
		 * towards the average neighbor location and the average heading of all
		 * neighbors
		 */
		newHeading = environment.getFriendsWeight()
				* (Formulae.heading(location, neighborLocation))
				+ environment.getFlowWeight() * neighborVelocity.getAngle();
		weight = environment.getFriendsWeight() + environment.getFlowWeight();

		if (!environment.wrapAround()) {
			/*
			 * if our next step on our current heading is outside of the canvas,
			 * add the weighted heading back towards the center of the canvas
			 */
			if (nextLocation.getX() < environment.getWallMargin()
					|| nextLocation.getX() > canvas.getWidth()
							- environment.getWallMargin()
					|| nextLocation.getY() < environment.getWallMargin()
					|| nextLocation.getY() > canvas.getHeight()
							- environment.getWallMargin()) {
				newHeading += environment.getWallWeight()
						* Formulae.heading(location, canvasCenter);
				weight += environment.getWallWeight();
			}
		}
		/*
		 * if we are in danger of crashing into any other boids add the weighted
		 * heading directly away from the average location of our potential
		 * crashes
		 */
		if (crashCount > 0) {
			newHeading += environment.getCrashWeight()
					* Formulae.heading(location, crashLocation) + Math.PI;
			weight += environment.getCrashWeight();
		}

		/* normalize our new weighted heading into just a heading */
		if (weight > 0) {
			newHeading = newHeading / weight;
		} else {
			newHeading = velocity.getAngle();
		}

		/*
		 * adjust our current heading towards the new heading, within the limits
		 * of our maximum turn angle
		 */
		if (Math.abs(velocity.getAngle() - newHeading) < environment
				.getMaximumTurnAngle()) {
			velocity.setAngle(newHeading);
		} else if (newHeading < velocity.getAngle()) {
			velocity.setAngle(velocity.getAngle()
					- environment.getMaximumTurnAngle());
		} else {
			velocity.setAngle(velocity.getAngle()
					+ environment.getMaximumTurnAngle());
		}

		// TODO adjust current speed to "go with the flow"

		location = Formulae.endPoint(location, velocity);
		if (environment.wrapAround()) {
			double newX = location.getX();
			double newY = location.getY();
			if (newX < 0) {
				newX = canvas.getWidth() + newX;
			} else if (newX > canvas.getWidth()) {
				newX = newX % canvas.getWidth();
			}
			if (newY < 0) {
				newY = canvas.getHeight() + newY;
			} else if (newY > canvas.getHeight()) {
				newY = newY % canvas.getHeight();
			}
			location = new Location(newX, newY);
		}
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
