import objectdraw.*;
import java.awt.*;

public class GameObject extends ActiveObject {

	public boolean overlaps(FilledRect other) {
		return false;
	}
	
	public boolean overlaps(FilledOval other) {
		return false;
	}
	
	public Location getLocation() {
		return null;
	}
}
