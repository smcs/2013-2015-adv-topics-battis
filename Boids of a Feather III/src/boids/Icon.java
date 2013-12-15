package boids;

import objectdraw.*;

public class Icon extends ActiveObject {
    
    private Boid boid;
    private Environment environment;
    private FilledOval icon;
    private Text caption;
        
    public Icon(Boid boid, Environment environment, DrawingCanvas canvas) {
	this.boid = boid;
	this.environment = environment;
	icon = new FilledOval(boid.getLocation(), environment.getIconWidth(), environment.getIconHeight(), canvas);
	caption = new Text (boid.getVelocity().toString(), boid.getLocation(), canvas);
	start();
    }
    
    public void run() {
	while(boid.isUpdating()) {
	    icon.moveTo(boid.getLocation());
	    caption.setText(boid.getVelocity().toString());
	    caption.moveTo(boid.getLocation());
	    pause(environment.getIconRefreshDelay());
	}
    }
}
