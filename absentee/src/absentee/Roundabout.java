package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class Roundabout {
	Car currentCar;
	ArrayList<Road> roads;
	Point pos;
	int nextDir;
	public Roundabout(Point p) {
		roads = new ArrayList<Road>();
		currentCar = null;
		pos = p;
	}
	//Connect a road to roundabout
	public void addRoad(Road r) {
		this.roads.add(r);
	}
	//If there is no car in roundabout, let the next car enter
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down
		if(currentCar != null) {
			return false;
		}
		return true;
	}
	
	//make a car enter roundabout
	public void Enter(Car car) {

		currentCar = car;
		car.curVelocity=car.maxVelocity;
		ArrayList <Road> tempList = new ArrayList<Road>();
		ArrayList<Road> tempList2 = new ArrayList<Road>();
		tempList2.add(roads.get(0));
		tempList2.add(null);
		tempList2.add(roads.get(2));
		tempList2.add(null);
		for(int i=0; i<roads.size(); i++){
			if(roads.get(i).roadClosed){
				tempList.add(null);
			}
			else if(roads.get(i).direction == i){
				if(currentCar.road.direction == 2 && currentCar.getNextDirection(tempList2) == 0){
					tempList.add(null);
				}
				else if(currentCar.road.direction == 0 && currentCar.getNextDirection(tempList2) == 2){
					tempList.add(null);
				}
				else{
					tempList.add(roads.get(i));
				}
			}
			else{
				tempList.add(null);
			}

		}
		nextDir = currentCar.getNextDirection(tempList);
	}
	//make the car currently in roundabout exit roundabout
	public void Exit() {
		//handle three way
		if(currentCar == null)
			return;

		currentCar.enterRoundabout(currentCar.road.direction, nextDir);
		Road nextRoad = roads.get(nextDir);
		if(currentCar.road.direction == 2 && nextDir == 0){
			System.out.println("nextDir: " + nextDir);
			System.out.println("nextRoadDir: " + nextRoad.direction);
			System.out.println("nextRoadStart: " + nextRoad.getStart() + " nextRoadEnd: "+ nextRoad.getEnd());
			System.out.println("curvel: " + currentCar.curVelocity);
			System.out.println("curPos: " + currentCar.curPos);
		}
		if(currentCar != null){
			nextRoad.Enter(currentCar);
			System.out.println("nextRoadSize: " + nextRoad.carsList.size());
			currentCar = null;
		}
	}
}
