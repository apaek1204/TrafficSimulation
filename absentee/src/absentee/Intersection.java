/**
 * This is the class for intersection
 *
 * @author      Qu Yaoxian, Nick Smith, Andrew Paek, Douglas Schmieder
 *
 *
 * This work complies with the JMU Honor Code.
 */

package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class Intersection {
	Car currentCar;
	ArrayList<Road> roads;
	Stoplight stoplight;
	Point pos;
	int nextDir;
	
	/**
     * constructor for the Intersection class
     *
     * @param s    a Stoplight object
     * @param p    a Point object that represents the position of the intersection
     */
	public Intersection(Stoplight s, Point p) {
		roads = new ArrayList<Road>();
		stoplight = s;
		currentCar = null;
		pos = p;
	}
	
	/**
     * addRoad method Connect road to intersection
     *
     * @param r    a Road object 
     */
	public void addRoad(Road r) {
		this.roads.add(r);
	}
	
	
	/**
     * canEnter allows car to enter if intersection does not have a car in it and current stoplight is green
     *
     * @param car    a Car object
     * @return       a boolean indicating whether the car can enter or not
     */
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
	
	/**
     * Enter method enters a car into intersection
     *
     * @param car		a car object
     */
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
	
	/**
     * Exit method makes a car exit inersection
     *
     */
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
