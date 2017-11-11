package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class Intersection {
	Car currentCar;
	ArrayList<Road> roads;
	Stoplight stoplight;
	Point pos;
	public Intersection(Stoplight s, Point p) {
		roads = new ArrayList<Road>();
		stoplight = s;
		currentCar = null;
		pos = p;
	}
	public void addRoad(Road r) {
		this.roads.add(r);
	}
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down 
		int nextDir = car.getNextDirection();
		if(currentCar != null) {
			return false;
		}

		if(stoplight.lights[nextDir] == 0 || stoplight.lights[nextDir] == 2) return false;

		return true;
	}
	public void Enter(Car car) {
		currentCar = car;
	}

	public void Exit() {
		
		int nextDir = currentCar.getNextDirection();
		Road nextRoad = roads.get(nextDir);
		if(nextRoad.canEnter(currentCar.size)) {
			currentCar.curVelocity = currentCar.maxVelocity;
			nextRoad.Enter(currentCar);
			currentCar = null;
		}
		currentCar.curVelocity = 0;
	}
}
