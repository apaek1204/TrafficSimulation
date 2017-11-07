package absentee;

import java.awt.Point;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point cur, Point des, int dir){
		Car tempCar = new BasicCar(10, 15, cur, cur, dir);
		return tempCar;
	}
}
