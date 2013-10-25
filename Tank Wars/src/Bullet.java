import objectdraw.*;

public class Bullet extends GameObject {
	private static final double BULLET_SIZE = 3;
	private static final double BULLET_VELOCITY = 3;
	
	private FilledOval body;
	private GameObject[] gameObjects;
	private Tank source;
	private Velocity velocity;
	private TankWars game;
	
	public Bullet (Tank aTank, Location aLocation, Velocity aVelocity, GameObject[] someGameObjects, TankWars aGame) {
		source = aTank;
		velocity = new Velocity(aVelocity);
		velocity.setSpeed(velocity.getSpeed() + BULLET_VELOCITY);
		gameObjects = someGameObjects;
		game = aGame;
		body = new FilledOval(aLocation, BULLET_SIZE, BULLET_SIZE, game.getCanvas());
		body.setColor(source.getColor());
		
		start();
	}
	
	@Override
	public void run() {
		double dx, dy;
		boolean impact = false;
		
		while (!game.isGameOver() && !impact) {
			if (velocity.getSpeed() != 0) {
				dx = Math.cos(velocity.getAngle()) * velocity.getSpeed();
				dy = Math.sin(velocity.getAngle()) * velocity.getSpeed();
				this.move(dx, dy);
			}
			
			if (body.getX() < 0 || body.getY() < 0 || body.getX() > game.getCanvas().getWidth() || body.getY() > game.getCanvas().getHeight()) {
				impact = true;
			}
			
			for (int i = 0; i < gameObjects.length; i++) {
				if (gameObjects[i] != null && gameObjects[i].overlaps(body)) {
					impact = true;
					if (gameObjects[i] instanceof Tank) {
						((Tank) gameObjects[i]).reset();
					}
				}
			}
			pause(game.REFRESH_RATE);
		}
		
		body.removeFromCanvas();
		source.expireBullet(this);
	}
	
	public void move(double dx, double dy) {
		body.move(dx, dy);
	}
	
	@Override
	public Location getLocation() {
		return body.getLocation();
	}
}
