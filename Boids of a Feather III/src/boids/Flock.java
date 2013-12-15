package boids;

import java.util.*;

import objectdraw.*;

public class Flock extends ActiveObject {
    private Environment environment;
    private Vector<Boid> flock;
    private boolean isUpdating;

    public Flock(Environment environment, DrawingCanvas canvas) {
	this.environment = environment;
	isUpdating = true;
	flock = new Vector<Boid>();
	for (int i = 0; i < environment.getFlockSize(); i++) {
	    flock.add(new Boid(this, environment, canvas));
	}
	start();
    }

    public void run() {
	for (int steps = 0; environment.getDuration() < 0 || steps < environment.getDuration(); steps++) {
	    for (int i = 0; i < flock.size(); i++) {
		flock.get(i).step();
	    }
	    pause(environment.getStepDelay());
	}
	isUpdating = false;
    }

    public LinkedList<Boid> getNeighbors(Boid boid) {
	ListIterator<Boid> flockIterator = flock.listIterator();
	LinkedList<Boid> neighbors = new LinkedList<Boid>();
	Boid b;
	while (flockIterator.hasNext()) {
	    b = flockIterator.next();
	    if (b != boid) {
		if (Formulae.distance(b.getLocation(), boid.getLocation()) < environment.getSubflockRange()) {
		    neighbors.add(b);
		}
	    }
	}
	return neighbors;
    }

    public boolean isUpdating() {
	return isUpdating;
    }
}
