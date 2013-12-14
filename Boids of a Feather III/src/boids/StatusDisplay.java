package boids;

import objectdraw.*;

public class StatusDisplay extends ActiveObject {
    
    private Flock flock;
    private Environment environment;
    private DrawingCanvas canvas;
    private Text wallWeight, crashWeight, friendsWeight, flowWeight;

    public StatusDisplay(Flock flock, Environment environment, DrawingCanvas canvas) {
	this.flock = flock;
	this.environment = environment;
	this.canvas = canvas;
	wallWeight = new Text (environment.getWallWeight(), 0, 0, canvas);
	crashWeight = new Text (environment.getCrashWeight(), 0, 20, canvas);
	friendsWeight = new Text (environment.getFriendsWeight(), 0, 40, canvas);
	flowWeight = new Text (environment.getFlowWeight(), 0, 60, canvas);
	start();
    }
    
    public void run() {
	while (flock.isUpdating()) {
	    wallWeight.setText(environment.getWallWeight());
	    crashWeight.setText(environment.getCrashWeight());
	    friendsWeight.setText(environment.getFriendsWeight());
	    flowWeight.setText(environment.getFlowWeight());
	    pause(environment.getIconRefreshDelay());
	}
    }
}
