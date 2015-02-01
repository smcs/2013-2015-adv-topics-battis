import java.util.*;

public class Sprite {
	protected static Vector<Sprite> sprites;
	
	public Sprite() {
		register();
	}
	
	protected void register() {
		if (Sprite.sprites == null) {
			Sprite.sprites = new Vector<Sprite>();
		}
		
		sprites.add(this);
	}
	
	public boolean overlaps(Sprite other) {
		return false;
	}
	
	public Vector<Sprite> overlapping() {
		Iterator<Sprite> iterator = sprites.iterator();
		Vector<Sprite> overlapped = new Vector<Sprite>();
		while (iterator.hasNext()) {
			Sprite other = iterator.next();
			if ((other != this) && (other.overlaps(this))) {
				overlapped.add(other);
			}
		}
		return overlapped;
	}
}
