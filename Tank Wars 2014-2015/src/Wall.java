import java.awt.Color;

import objectdraw.*;

public class Wall extends Sprite {
	private static final double WIDTH = 10;
	private FilledRect wall;

	public Wall(DrawingCanvas canvas) {
		wall = new FilledRect(Math.random() * canvas.getWidth(), Math.random()
				* canvas.getHeight(), WIDTH, WIDTH, canvas);
		wall.setColor(Color.LIGHT_GRAY);
		while (overlapping().size() != 0) {
			wall.moveTo(Math.random() * canvas.getWidth(), Math.random()
					* canvas.getHeight());
		}
		register();
	}

	public boolean overlaps(Sprite other) {
		if (other.getClass() == Wall.class) {
			return overlaps((Wall) other);
		} else {
			return other.overlaps(this);
		}
	}

	public boolean overlaps(Wall other) {
		return MyMath.overlaps(this.getBoundingBox(), other.getBoundingBox());
	}

	public BoundingBox getBoundingBox() {
		BoundingBox box = new BoundingBox(wall.getLocation(), wall.getWidth(),
				wall.getHeight(), null);
		return box;
	}
}
