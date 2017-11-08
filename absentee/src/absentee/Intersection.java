package absentee;

public class Intersection {
	Car currentCar;
	Road[] roads;
	Stoplight stoplight;
	public Intersection(Road[] r, Stoplight s) {
		roads = r;
		stoplight = s;
		currentCar = null;
	}
	public Boolean canEnter(Car car) {
		//0 left, 1 right, 2 up, 3 down 
		int nextDir = car.getNextDirection();
		if(currentCar != null) {
			return false;
		}

		if(s.lights[nextDir] == 0) return false;
		
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
