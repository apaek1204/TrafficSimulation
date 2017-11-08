package absentee;

import java.awt.Point;

public class Intersection {
	Car currentCar;
	Road[] roads;
	Stoplight stoplight;
	Point pos;
	public Intersection(Road[] r, Stoplight s, Point p) {
		roads = r;
		stoplight = s;
		currentCar = null;
		pos = p;
	}
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down 
		int nextDir = car.getNextDirection();
		if(currentCar != null) {
			return false;
		}

		if(stoplight.lights[nextDir] == 0) return false;

		return true;
	}
	public void Enter(Car car) {
		currentCar = car;
	}
	public void Exit() {
		int nextDir = currentCar.getNextDirection();
		Road nextRoad = roads[nextDir];
		if(nextRoad.canEnter(currentCar.size)) {
			nextRoad.Enter(currentCar);
		}
		currentCar = null;
	}
}
