import objectdraw.*;
import java.awt.event.*;
import java.util.*;

public class KeyboardManager implements KeyListener {

	private Vector<Boolean> pressedKeys;
	
	public KeyboardManager(DrawingCanvas canvas) {
		pressedKeys = new Vector<Boolean>();
		canvas.addKeyListener(this);
		canvas.requestFocus();
	}

	private void resize(int keyCode) {
		if (keyCode >= pressedKeys.size()) {
			pressedKeys.setSize(keyCode + 1);
		}
		
		if (pressedKeys.get(keyCode) == null) {
			pressedKeys.set(keyCode, false);
		}
	}
	
	public boolean isPressed(int keyCode) {
		resize(keyCode);
		return pressedKeys.get(keyCode);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		resize(e.getKeyCode());
		pressedKeys.set(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		resize(e.getKeyCode());
		pressedKeys.set(e.getKeyCode(), false);
	}

}
