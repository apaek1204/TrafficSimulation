package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class Roundabout {
	Car currentCar;
	ArrayList<Road> roads;
	Point pos;
	public Roundabout(Point p) {
		roads = new ArrayList<Road>();
		currentCar = null;
		pos = p;
	}
	public void addRoad(Road r) {
		this.roads.add(r);
	}
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down
		if(currentCar != null) {
			return false;
		}
		return true;
	}
	public void Enter(Car car) {
		currentCar = car;
		car.curVelocity=car.maxVelocity;
	}
	public void Exit() {
		//handle three way
		//System.out.println("Intersect Car: " + this.currentCar);
		if(currentCar == null)
			return;
		int nextDir = currentCar.getNextDirection();
		currentCar.setDirection(nextDir);
		Road nextRoad = roads.get(nextDir);
		/*if(nextRoad.canEnter(1currentCar.size)) {
			currentCar.curVelocity = currentCar.maxVelocity;
			nextRoad.Enter(currentCar);
			currentCar = null;
		}*/
		if(currentCar != null){
			nextRoad.Enter(currentCar);
			currentCar = null;
		}
		//currentCar.curVelocity = 0;
	}
}