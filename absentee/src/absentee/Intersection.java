package absentee;

public class Intersection {
	Car currentCar;
	Street[] roads = new Street[4];
	public void Enter(Car car) {
		currentCar = car;
	}
	public void Exit() {

	}
}
