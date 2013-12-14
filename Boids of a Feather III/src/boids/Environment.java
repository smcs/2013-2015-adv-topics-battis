package boids;

import objectdraw.WindowController;

public class Environment extends WindowController {

    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH;
    
    private double iconWidth = CANVAS_WIDTH / 100;
    private double iconHeight = iconWidth;
    
    private double stepDelay = 100;
    private double iconRefreshDelay = stepDelay;
    
    private int flockSize = 100;
    private Flock flock;
    
    private double minimumSpeed = 1;
    private double maximumSpeed = 3;
    private double maximumTurnAngle = Math.PI / 180 * 10;
    private int simulationSteps = -1; // value > 0 limits number of simulated steps
    private double minimumEnemyDistance = iconWidth;

    private double subflockRange = CANVAS_WIDTH / 10;

    private double wallWeight = 20;
    private double crashWeight = 0;
    private double friendsWeight = 0;
    private double flowWeight = 0;
    
   public void begin() {
	this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
	flock = new Flock(this, canvas);
	new StatusDisplay(flock, this, canvas);
    }
   
   public double getIconWidth() {
       return iconWidth;
   }
   
   public double getIconHeight() {
       return iconHeight;
   }
   
   public double getStepDelay() {
       return stepDelay;
   }
   
   public double getIconRefreshDelay() {
       return iconRefreshDelay;
   }
   
   public int getFlockSize() {
       return flockSize;
   }
   
   public double getMinimumSpeed() {
       return minimumSpeed;
   }
   
   public double getMaximumSpeed() {
       return maximumSpeed;
   }
   
   public double getMaximumTurnAngle() {
       return maximumTurnAngle;
   }
   
   public int getSimulationSteps() {
       return simulationSteps;
   }
   
   public double getMinimumEnemyDistance() {
       return minimumEnemyDistance;
   }
   
   public double getSubflockRange() {
       return subflockRange;
   }
   
   public double getWallWeight() {
       return wallWeight;
   }
   
   public double getCrashWeight() {
       return crashWeight;
   }
   
   public double getFriendsWeight() {
       return friendsWeight;
   }
   
   public double getFlowWeight() {
       return flowWeight;
   }
}
