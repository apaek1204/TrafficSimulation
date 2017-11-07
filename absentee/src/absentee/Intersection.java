package absentee;

public class Intersection {
	Car currentCar;
	Road[] roads;
	Stoplight s;
	public Boolean canEnter(Car car) {
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
