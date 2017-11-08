package absentee;

public class Intersection {
	Car currentCar;
	Road[] roads = new Road[4];
	public void Enter(Car car) {
		currentCar = car;
	}
	public void Exit() {

	}
}
