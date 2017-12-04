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
		int nextDir = car.road.direction;
		if(currentCar != null) {
			return false;
		}

		if(stoplight.lights[nextDir] == 0 || stoplight.lights[nextDir] == 2) return false;

		return true;
	}
	public void Enter(Car car) {
		currentCar = car;
		car.curVelocity=car.maxVelocity;
	}

	public void Exit() {
		//System.out.println("Intersect Car: " + this.currentCar);
		if(currentCar == null)
			return;
		ArrayList <Road> tempList = new ArrayList<Road>();
		for(int i=0; i<roads.size(); i++){
			if(roads.get(i).roadClosed){
				tempList.add(null);
			}
			else if(roads.get(i).direction == i){
				tempList.add(roads.get(i));
			}
			else{
				tempList.add(null);
			}

		}
		int nextDir = currentCar.getNextDirection(tempList);
		currentCar.setRoad(roads.get(nextDir));
		Road nextRoad = roads.get(nextDir);
//		currentCar.directionList.remove(0);
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
