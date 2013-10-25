
public class Velocity {
	private double angle = 0;
	private double speed = 0;
	
	public Velocity() {
		// use defaults
	}
	
	public Velocity(double anAngle, double aSpeed) {
		angle = anAngle;
		speed = aSpeed;
	}
	
	public Velocity(Velocity otherVelocity) {
		angle = otherVelocity.angle;
		speed = otherVelocity.speed;
	}

	public double getAngle() {
		return angle;
	}
	
	public double setAngle(double newAngle) {
		double oldAngle = angle;
		angle = newAngle;
		return oldAngle;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double setSpeed(double newSpeed) {
		double oldSpeed = speed;
		speed = newSpeed;
		return oldSpeed;
	}
}
