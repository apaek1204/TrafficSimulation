package absentee;

public class VehicleFactory {
	public Car createBasicCar(){
		Car tempCar = new BasicCar();
		return tempCar;
	}
}
