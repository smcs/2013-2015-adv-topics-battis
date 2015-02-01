import objectdraw.*;

import java.awt.*;

public class Bullet extends Sprite implements Runnable {
	
	private static final double RADIUS = 1;
	private static final double MOVE = 5;
	private static final int PAUSE = 50;
	
	private FilledOval bullet;
	private double heading;
	
	public Bullet(Location start, double heading, DrawingCanvas canvas) {
		super();
		bullet = new FilledOval(start, RADIUS * 2, RADIUS * 2, canvas);
		bullet.setColor(Color.GREEN);
		this.heading = heading;
	}
	
	private void move() {
		bullet.move(bullet.getX() + Math.cos(heading) * MOVE, bullet.getY() + Math.sin(heading) * MOVE);
	}
	
	public void run() {
		while (overlapping().size() == 0) {
			move();
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	public Location getCenter() {
		return new Location(bullet.getX() + RADIUS, bullet.getY() + RADIUS);
	}

	public double getRadius() {
		return RADIUS;
	}

	public BoundingCircle getBoundingCircle() {
		return new BoundingCircle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), bullet.getCanvas());	
	}
}
