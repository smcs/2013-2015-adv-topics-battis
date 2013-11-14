import java.util.*;
import objectdraw.*;

public class FastFlock extends Flock {
	public static final int DIVISIONS = 10;
	protected LinkedList[][] locales;
	protected double horizontalScale, verticalScale;
	
	public FastFlock(int flockSize, int iterations, DrawingCanvas canvas) {
		super(flockSize, iterations, canvas);
		
		horizontalScale = (double) canvas.getWidth() / (double) DIVISIONS;
		verticalScale = (double) canvas.getHeight() / (double) DIVISIONS;
		locales = new LinkedList[DIVISIONS][DIVISIONS];
		for (int x = 0; x < locales.length; x++) {
			for (int y = 0; y < locales[x].length; y++) {
				locales[x][y] = new LinkedList<Boid>();
			}
		}
		
		for (int i = 0; i < boids.length; i++) {
			register(boids[i], null);
		}
	}
	
	public int localeX(double xCoord) {
		return (int) Math.floor(xCoord / horizontalScale);
	}
	
	public int localeY(double yCoord) {
		return (int) Math.floor(yCoord / verticalScale);
	}
	
	public LinkedList<Boid> locate(Location l) {
		return locales[localeX(l.getX())][localeY(l.getY())];
	}
	
	public LinkedList<Boid> locate(Boid b) {
		return locate(b.getLocation());
	}
	
	public synchronized void register(Boid b, Location oldLocation) {
		if (oldLocation != null) {
			locate(oldLocation).remove(b);
		}
		locate(b).add(b);
	}
	
	public LinkedList<Boid> ring(Boid b, int distance) {
		if (distance == 0) {
			return locate(b);
		} else {
			int lX = localeX(b.getLocation().getX());
			int lY = localeY(b.getLocation().getY());
			LinkedList<Boid> neighbors = new LinkedList<Boid>();
			for (
					int x = Math.max(0, lX - distance);
					x < Math.min(locales.length, lX + distance);
					x++
				) {
				if (lY - distance > 0) {
					neighbors.addAll(locales[x][lY - distance]);
				}
				if (lY + distance < locales.length) {
					neighbors.addAll(locales[x][lY + distance]);
				}
			}
			for (
					int y = Math.max(0, lY - distance);
					y < Math.min(locales[lX].length, lY + distance);
					y++
				) {
				if (lX - distance > 0) {
					neighbors.addAll(locales[lX - distance][y]);
				}
				if (lX + distance < locales.length) {
					neighbors.addAll(locales[lX + distance][y]);
				}
			}
			return neighbors;
		}
	}
	
	public synchronized Boid[] subflock(Boid b, int size) {
		LinkedList<Boid> subflock = new LinkedList<Boid>();
		LinkedList<Boid> neighbors;
		double distance, maxDistance = 0;
		int neighborCircumference = 0;
		boolean done = false;
		do {
			neighbors = ring(b, neighborCircumference);
			System.out.printf("%d ", neighborCircumference);
			ListIterator<Boid> neighborIterator = neighbors.listIterator();
			while (neighborIterator.hasNext()) {
				Boid neighbor = neighborIterator.next();
				comparisons++;
				distance = b.distanceTo(neighbor);
				if (subflock.size() < size) {
					subflock.add(neighbor);
					if (distance > maxDistance) {
						maxDistance = distance;
					}
				} else if (distance < maxDistance) {
					ListIterator<Boid> subflockIterator = subflock.listIterator();
					while (subflockIterator.hasNext()) {
						Boid flockmate = subflockIterator.next();
						comparisons++;
						if (b.distanceTo(flockmate) == maxDistance) {
							subflockIterator.set(neighbor);
							maxDistance = distance;
							break;
						}
					}
				}
			}
			double xAlignment = (((b.getLocation().getX() % horizontalScale) / horizontalScale) % 0.5) * horizontalScale;
			double yAlignment = (((b.getLocation().getY() % verticalScale) / verticalScale) % 0.5) * horizontalScale;
			if (subflock.size() < size) {
				neighborCircumference++;
			} else if (maxDistance > (horizontalScale * neighborCircumference) + xAlignment || maxDistance > (verticalScale * neighborCircumference) + yAlignment) {
				neighborCircumference++;
			} else {
				done = true;
			}
		} while (!done);
		System.out.println();
		return subflock.toArray(new Boid[0]);
	}
}
