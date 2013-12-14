package boids;
public class Velocity {
	private double angle, speed;

	public Velocity(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public double getAngle() {
		return angle;
	}

	public double setAngle(double angle) {
	    double oldAngle = this.angle;
	    this.angle = angle;
	    return oldAngle;
	}
}
