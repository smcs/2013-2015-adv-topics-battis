import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class Tank extends GameObject {
	
	private FilledOval body;
	private Line cannon;
	
	private TankWars game;
	private GameObject[] gameObjects;
	private Bullet[] bullets;
	private Velocity velocity;

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

	public void reset() {
		double minimumDistance = 0;
		double distance = 0;
		RandomDoubleGenerator widthGen = new RandomDoubleGenerator(TankWars.MARGIN, game.getCanvas().getWidth() - TankWars.MARGIN);
		RandomDoubleGenerator heightGen = new RandomDoubleGenerator(TankWars.MARGIN, game.getCanvas().getHeight() - TankWars.MARGIN);
		RandomIntGenerator angleGen = new RandomIntGenerator(0, 360);
		Location location = new Location(0, 0), other;

		while (minimumDistance < TankWars.MARGIN) {
			minimumDistance = TankWars.MARGIN;
			location = new Location (widthGen.nextValue(), heightGen.nextValue());
			for (int i = 0; i < gameObjects.length; i++) {
				if (gameObjects[i] != null && gameObjects[i] != this) {
					other = gameObjects[i].getLocation();
					distance = Math.sqrt(Math.pow(location.getX() - other.getX(), 2) + Math.pow(location.getY() - other.getY(), 2));
					if (distance < minimumDistance) {
						minimumDistance = distance;
					}
				}
			}
		}
		body.moveTo(location);
		velocity = new Velocity();
		velocity.setAngle(TankWars.ANGLE_INCREMENT * angleGen.nextValue());
		alignBarrel();
	}
	
	public void turnLeft() {
		velocity.setAngle(velocity.getAngle() - TankWars.ANGLE_INCREMENT);
		alignBarrel();
	}
	
	public void turnRight() {
		velocity.setAngle(velocity.getAngle() + TankWars.ANGLE_INCREMENT);
		alignBarrel();
	}
	
	public void speedUp() {
		velocity.setSpeed(velocity.getSpeed() + TankWars.SPEED_INCREMENT);
	}
	
	public void slowDown() {
		velocity.setSpeed(velocity.getSpeed() - TankWars.SPEED_INCREMENT);
	}
	
	public void fire() {
		for(int i = 0; i < bullets.length; i++) {
			if (bullets[i] == null) {
				bullets[i] = new Bullet(this, cannon.getEnd(), velocity, gameObjects, game);
				break;
			}
		}
	}
	
	@Override
	public boolean overlaps (FilledOval other) {
		return body.overlaps(other);
	}
	
	@Override
	public boolean overlaps (FilledRect other) {
		return body.overlaps(other);
	}
	
	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy) {
		body.move(dx, dy);
		cannon.move(dx, dy);
	}
	
	private void alignBarrel() {
		cannon.setStart(body.getX() + TankWars.TANK_SIZE / 2, body.getY() + TankWars.TANK_SIZE / 2);
		cannon.setEnd(cannon.getStart().getX() + (TankWars.TANK_SIZE / 2 + TankWars.CANNON_LENGTH) * Math.cos(velocity.getAngle()), cannon.getStart().getY() + (TankWars.TANK_SIZE / 2 + TankWars.CANNON_LENGTH) * Math.sin(velocity.getAngle()));
	}
	
	public Color getColor() {
		return body.getColor();
	}
	
	public void expireBullet(Bullet bullet) {
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] == bullet) {
				bullets[i] = null;
				break;
			}
		}
	}
	
	@Override
	public Location getLocation() {
		return body.getLocation();
	}
}
