package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class Intersection {
	Car currentCar;
	ArrayList<Road> roads;
	Stoplight stoplight;
	Point pos;
	int nextDir;
	public Intersection(Stoplight s, Point p) {
		roads = new ArrayList<Road>();
		stoplight = s;
		currentCar = null;
		pos = p;
	}
	//Connect road to intersection
	public void addRoad(Road r) {
		this.roads.add(r);
	}
	//If intersection does not have a car in it and current stoplight is green, allow car to enter
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down
		int nextDir = car.road.direction;
		if(currentCar != null) {
			return false;
		}

		if(stoplight.lights[nextDir] == 0 || stoplight.lights[nextDir] == 2) return false;

		return true;
	}
	//enter a car into intersection
	public void Enter(Car car) {
		currentCar = car;
		car.curVelocity=car.maxVelocity;
		ArrayList <Road> tempList = new ArrayList<Road>();
		for(int i=0; i<roads.size(); i++){
			if(roads.get(i).roadClosed){
				tempList.add(null);
				//System.out.println("road closed");
				//System.out.println(roads.get(i).getStart() + ", " + roads.get(i).getEnd());
			}
			else if(roads.get(i).direction == i){
				tempList.add(roads.get(i));
			}
			else{
				tempList.add(null);
			}

		}
		nextDir = currentCar.getNextDirection(tempList);
	}
	//make a car exit intersection
	public void Exit() {
		if(currentCar == null)
			return;

		currentCar.setRoad(roads.get(nextDir));
		Road nextRoad = roads.get(nextDir);
		if(currentCar != null){
			nextRoad.Enter(currentCar);
			currentCar = null;
		}

		//currentCar.curVelocity = 0;
	}
}
