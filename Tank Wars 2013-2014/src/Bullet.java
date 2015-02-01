import objectdraw.*;

/**
 * A single bullet, animated as a thread.
 * 
 * @author Seth Battis
 */
public class Bullet extends GameObject {
	
	/**
	 * The shape of the bullet.
	 */
	private FilledOval body;
	
	/**
	 * All of the objects in the game that the bullet could collide with.
	 */
	private GameObject[] gameObjects;
	
	/**
	 * The tank that fired the bullet.
	 */
	private Tank source;
	
	/**
	 * The velocity of the bullet.
	 */
	private Velocity velocity;
	
	/**
	 * The game within which the bullet was fired.
	 */
	private TankWars game;
	
	/**
	 * Construct a bullet as fired from a particular tank.
	 * @param aTank The tank from which the bullet was fired.
	 * @param aLocation The starting location for the bullet.
	 * @param aVelocity The initial velocity of the bullet.
	 * @param someGameObjects The game objects that the bullet will check for collisions.
	 * @param aGame The game within which the bullet was fired.
	 */
	public Bullet (Tank aTank, Location aLocation, Velocity aVelocity, GameObject[] someGameObjects, TankWars aGame) {
		source = aTank;
		velocity = new Velocity(aVelocity);
		velocity.setSpeed(velocity.getSpeed() + TankWars.BULLET_VELOCITY);
		gameObjects = someGameObjects;
		game = aGame;
		body = new FilledOval(aLocation, TankWars.BULLET_SIZE, TankWars.BULLET_SIZE, game.getCanvas());
		body.setColor(source.getColor());
		
		start();
	}
	
	/**
	 * Animate the path of the bullet (until it hits something or
	 * leaves the canvas).
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		// the change in x and y coordinates for the bullet at each step along its path
		double dx = 0, dy = 0;
		
		// assume the bullet has not yet hit anything (which would stop its animation)
		boolean impact = false;
		
		// don't bother to calculate the change in location if the bullet's not moving
		if (velocity.getSpeed() != 0) {
			dx = Math.cos(velocity.getAngle()) * velocity.getSpeed();
			dy = Math.sin(velocity.getAngle()) * velocity.getSpeed();
		}

		while (!game.isGameOver() && !impact) {
			
			// only bother to move the bullet if it has a speed
			if (velocity.getSpeed() != 0) {
				this.move(dx, dy);
			}
			
			// leaving the canvas counts as "impact"
			if (body.getX() < 0 || body.getY() < 0 || body.getX() > game.getCanvas().getWidth() || body.getY() > game.getCanvas().getHeight()) {
				impact = true;
			}
			
			// check all of the gameObjects to see if the bullet has hit one of them
			for (int i = 0; i < gameObjects.length; i++) {
				if (gameObjects[i] != null && gameObjects[i].overlaps(body)) {
					impact = true;
					
					// if the bullet has hit a tank, force it to reset
					if (gameObjects[i] instanceof Tank) {
						((Tank) gameObjects[i]).reset();
						// TODO add one to the score of source
					}
				}
			}
			
			pause(TankWars.REFRESH_RATE);
		}
		
		// remove the bullet from the game (so that others can be fired)
		body.removeFromCanvas();
		source.expireBullet(this);
	}
	
	/**
	 * Advance the bullet to its next location
	 * @param dx The change in X-coordinate.
	 * @param dy The change in Y-coordinate.
	 */
	public void move(double dx, double dy) {
		body.move(dx, dy);
	}
	
	/**
	 * @return The location of the bullet on the canvas.
	 */
	@Override
	public Location getLocation() {
		return body.getLocation();
	}
}
