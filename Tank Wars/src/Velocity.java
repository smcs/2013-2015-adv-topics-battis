/**
 * Store a velocity, that is: a speed and a compass direction (in radians).
 * 
 * @author Seth Battis
 */
public class Velocity {
	
	/**
	 * The compass direction of the velocity (in radians).
	 */
	private double angle = 0;
	
	/**
	 * The speed of the velocity.
	 */
	private double speed = 0;

	/**
	 * Default constructor (angle = 0 or East, speed = 0)
	 */
	public Velocity() {}
	
	/**
	 * Construct a specific velocity.
	 * @param anAngle A compass direction (in radians).
	 * @param aSpeed A speed.
	 */
	public Velocity(double anAngle, double aSpeed) {
		angle = anAngle;
		speed = aSpeed;
	}

	/**
	 * Copy constructor.
	 * @param otherVelocity Another velocity to copy.
	 */
	public Velocity(Velocity otherVelocity) {
		angle = otherVelocity.angle;
		speed = otherVelocity.speed;
	}

	/**
	 * @return The current compass direction of this velocity (in radians)
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * @param newAngle A new compass direction for this velocity (in radians).
	 * @return The previous compass direction for this velocity (in radians).
	 */
	public double setAngle(double newAngle) {
		double oldAngle = angle;
		angle = newAngle;
		return oldAngle;
	}

	/**
	 * @return The current speed of this velocity.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param newSpeed A new speed for this velocity.
	 * @return The previous speed of this velocity.
	 */
	public double setSpeed(double newSpeed) {
		double oldSpeed = speed;
		speed = newSpeed;
		return oldSpeed;
	}
}
