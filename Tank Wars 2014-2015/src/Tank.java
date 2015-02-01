import objectdraw.*;

public class Tank extends Sprite implements Runnable {
	private static final double RADIUS = 5;
	private static final double LENGTH = 9;
	private static final double MOVE = 5;
	private static final double TURN = Math.PI / 36;
	private static final int PAUSE = 100;
	private static final int FORWARD = 1;
	private static final int BACKWARD = -1;
	private static final int LEFT = -1;
	private static final int RIGHT = 1;

	private KeyboardManager keyboardManager;
	private KeyboardBinding keyboardBinding;
	private DrawingCanvas canvas;
	private FilledOval body;
	private Line barrel;
	private double heading;

	public Tank(KeyboardManager km, KeyboardBinding kb, DrawingCanvas dc) {
		super();
		keyboardManager = km;
		keyboardBinding = kb;
		canvas = dc;

		heading = Math.random() * Math.PI * 2.0;
		body = new FilledOval(Math.random() * canvas.getWidth(), Math.random()
				* canvas.getHeight(), RADIUS * 2, RADIUS * 2, canvas);
		barrel = new Line(0, 0, 0, 0, canvas);
		redraw();

		new Thread(this).start();
	}

	private void move(int direction) {
		body.move(Math.cos(heading) * MOVE * direction, Math.sin(heading)
				* MOVE * direction);
		redraw();
	}

	private void turn(int direction) {
		heading += direction * TURN;
		redraw();
	}

	private void redraw() {
		barrel.setStart(body.getX() + RADIUS, body.getY() + RADIUS);
		barrel.setEnd(barrel.getStart().getX() + Math.cos(heading) * LENGTH,
				barrel.getStart().getY() + Math.sin(heading) * LENGTH);
	}

	@Override
	public void run() {
		while (true) {
			if (keyboardManager.isPressed(keyboardBinding.left())) {
				turn(LEFT);
			}
			if (keyboardManager.isPressed(keyboardBinding.right())) {
				turn(RIGHT);
			}
			if (keyboardManager.isPressed(keyboardBinding.up())) {
				move(FORWARD);
			}
			if (keyboardManager.isPressed(keyboardBinding.down())) {
				move(BACKWARD);
			}
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}
	
	public boolean overlaps(Sprite other) {
		if (other.getClass() == Tank.class) {
			return overlaps((Tank) other);
		} else if (other.getClass() == Wall.class) {
			return overlaps((Wall) other);
		} else if (other.getClass() == Bullet.class) {
			return overlaps((Bullet) other);
		} else {
			return other.overlaps(this);
		}
	}
	
	public boolean overlaps(Tank t) {
		// TODO account for barrels as well
		return MyMath.overlaps(getBoundingCircle(), t.getBoundingCircle());
	}
	
	public boolean overlaps(Wall w) {
		// TODO account for barrel
		return MyMath.overlaps(getBoundingCircle(), w.getBoundingBox());
	}
	
	public boolean overlaps(Bullet b) {
		return MyMath.overlaps(getBoundingCircle(), b.getBoundingCircle());
	}
	
	public Location getCenter() {
		return new Location(body.getX() + RADIUS, body.getY() + RADIUS);
	}
	
	public double getRadius() {
		return RADIUS;
	}
	
	public BoundingCircle getBoundingCircle() {
		return new BoundingCircle(body.getX(), body.getY(), body.getWidth(), body.getHeight(), body.getCanvas());	
	}
}
