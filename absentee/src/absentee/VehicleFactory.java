package absentee;

import java.awt.Point;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point cur, Point des, int dir, double s){
		Car tempCar = new BasicCar(10, 15, cur, cur, dir, s);
		return tempCar;
	}
}
