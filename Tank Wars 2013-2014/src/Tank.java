import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A single Tank, animated as a thread.
 * 
 * @author Seth Battis
 */
public class Tank extends GameObject {
	
	/**
	 * The body of the tank.
	 */
	private FilledOval body;
	
	/**
	 * The cannon of the tank.
	 */
	private Line cannon;

	/**
	 * The game within which the tank is playing.
	 */
	private TankWars game;
	
	/**
	 * All of the game objects that the tank or its bullets can collide
	 * with.
	 */
	private GameObject[] gameObjects;
	
	/**
	 * All of this tank's bullets (it has a limited quantity to fire at
	 * any given time).
	 */
	private Bullet[] bullets;
	
	/**
	 * The current velocity of the tank.
	 */
	private Velocity velocity;

	/**
	 * Construct a new tank.
	 * @param aGame The game within which the tank is playing.
	 * @param aControlSet A control set for this tank (should be unique within the game).
	 * @param someGameObjects The other game objects that the tank (and its bullets) will test for collisions.
	 */
	public Tank(TankWars aGame, ControlSet aControlSet, GameObject[] someGameObjects) {		
		game = aGame;
		
		new KeyWatcher(this, aControlSet, game);
		
		gameObjects = someGameObjects;		
		bullets = new Bullet[TankWars.NUM_BULLETS];
		velocity = new Velocity();
		
		body = new FilledOval(0, 0, TankWars.TANK_SIZE, TankWars.TANK_SIZE, game.getCanvas());
		cannon = new Line (0, 0, 0, 0, game.getCanvas());
		body.setColor(generateRandomColor());
		cannon.setColor(body.getColor());

		reset();
		start();
	}

	/**
	 * Animate the actions of the tank (until the game is over).
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		double dx, dy;
		
		while (!game.isGameOver()) {
			if (velocity.getSpeed() != 0) {
				dx = Math.cos(velocity.getAngle()) * velocity.getSpeed();
				dy = Math.sin(velocity.getAngle()) * velocity.getSpeed();
				this.move(dx, dy);
			}
			
			for(int i = 0; i < gameObjects.length; i++) {
				if (gameObjects[i] != null && gameObjects[i] != this && gameObjects[i].overlaps(body)) {
					velocity.setSpeed(0);
					break;
				}
			}
			
			if (body.getX() < 0 || body.getY() < 0 || body.getX() > game.getCanvas().getWidth() || body.getY() > game.getCanvas().getHeight()) {
				velocity.setSpeed(0);
			}
			pause(TankWars.REFRESH_RATE);
		}
	}

	/**
	 * Reset a tank that is either new to the game or that has been hit
	 * by a bullet. Tanks are positioned randomly, but no closer than
	 * TankWars.MARGIN to other game objects and to the edge of the
	 * game board.
	 */
	public void reset() {
		double minimumDistance = 0;
		double distance = 0;
		RandomDoubleGenerator widthGen = new RandomDoubleGenerator(TankWars.MARGIN, game.getCanvas().getWidth() - TankWars.MARGIN);
		RandomDoubleGenerator heightGen = new RandomDoubleGenerator(TankWars.MARGIN, game.getCanvas().getHeight() - TankWars.MARGIN);
		RandomIntGenerator angleGen = new RandomIntGenerator(0, 360);
		Location location = new Location(0, 0), other;

		// keep generating random locations until one works
		while (minimumDistance < TankWars.MARGIN) {
			minimumDistance = TankWars.MARGIN;
			location = new Location (widthGen.nextValue(), heightGen.nextValue());
			
			// check the proposed location against the locations of other game objects
			for (int i = 0; i < gameObjects.length; i++) {
				if (gameObjects[i] != null && gameObjects[i] != this) {
					other = gameObjects[i].getLocation();
					
					// use the Pythagorean theorem to compute the distance to other game objects
					distance = Math.sqrt(Math.pow(location.getX() - other.getX(), 2) + Math.pow(location.getY() - other.getY(), 2));
					if (distance < minimumDistance) {
						minimumDistance = distance;
					}
				}
			}
		}
		
		// set up the tank at that location
		body.moveTo(location);
		velocity = new Velocity();
		velocity.setAngle(TankWars.ANGLE_INCREMENT * angleGen.nextValue());
		alignBarrel();
	}
	
	/**
	 * Turn the tank TankWars.ANGLE_INCREMENT radians to the left.
	 */
	public void turnLeft() {
		velocity.setAngle(velocity.getAngle() - TankWars.ANGLE_INCREMENT);
		alignBarrel();
	}
	
	/**
	 * Turn the tank TankWars.ANGLE_INCREMENT radians to the right.
	 */
	public void turnRight() {
		velocity.setAngle(velocity.getAngle() + TankWars.ANGLE_INCREMENT);
		alignBarrel();
	}
	
	/**
	 * Increase the speed of the tank by TankWars.SPEED_INCREMENT.
	 */
	public void speedUp() {
		velocity.setSpeed(velocity.getSpeed() + TankWars.SPEED_INCREMENT);
	}
	
	/**
	 * Decrease the speed of the tank by TanksWars.SPEED_INCREMENT.
	 */
	public void slowDown() {
		velocity.setSpeed(velocity.getSpeed() - TankWars.SPEED_INCREMENT);
	}
	
	/**
	 * If the tank has not currently maxed out its limited supply of
	 * bullets, fire another bullet. Bullets are fired in the direction
	 * in which the tank is facing, at the same velocity (plus a little
	 * bit) as the tank itself.
	 */
	public void fire() {
		// walk through the bullets array looking for an empty slot
		for(int i = 0; i < bullets.length; i++) {
			if (bullets[i] == null) {
				bullets[i] = new Bullet(this, cannon.getEnd(), velocity, gameObjects, game);
				break;
			}
		}
	}
	
	/**
	 * @return True if the other shape overlaps the body of the tank.
	 */
	@Override
	public boolean overlaps (FilledOval other) {
		return body.overlaps(other);
	}
	
	/**
	 * @return True if the other shape overlaps the body of the tank.
	 */
	@Override
	public boolean overlaps (FilledRect other) {
		return body.overlaps(other);
	}
	
	/**
	 * Advance the tank to its next location.
	 * @param dx Amount to adjust the X-coordinate.
	 * @param dy Amount to adjust the Y-coordinate.
	 */
	public void move(double dx, double dy) {
		body.move(dx, dy);
		cannon.move(dx, dy);
	}
	
	/**
	 * Realign the barrel to point in the current direction of travel.
	 */
	private void alignBarrel() {
		cannon.setStart(body.getX() + TankWars.TANK_SIZE / 2, body.getY() + TankWars.TANK_SIZE / 2);
		cannon.setEnd(cannon.getStart().getX() + (TankWars.TANK_SIZE / 2 + TankWars.CANNON_LENGTH) * Math.cos(velocity.getAngle()), cannon.getStart().getY() + (TankWars.TANK_SIZE / 2 + TankWars.CANNON_LENGTH) * Math.sin(velocity.getAngle()));
	}
	
	/**
	 * @return The color of the tank.
	 */
	public Color getColor() {
		return body.getColor();
	}
	
	/**
	 * Stop tracking an "expired" bullet (one that has collided with
	 * another game object or left the canvas).
	 * @precondition bullet has, in fact, collided with another game object or left the canvas AND removeFromCanvas() has been called on its graphical objects.
	 * @postcondition If bullet was fired by this tank and was currently being tracked, it is no longer being tracked.
	 * @param bullet The bullet to expire.
	 */
	public void expireBullet(Bullet bullet) {
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] == bullet) {
				bullets[i] = null;
				break;
			}
		}
	}
	
	/**
	 * @return The current location of the tank.
	 */
	@Override
	public Location getLocation() {
		return body.getLocation();
	}
}
