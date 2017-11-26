package absentee;

import java.awt.Point;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point cur, Point des, int dir, Point s){
		Car tempCar = new BasicCar(maxVel, breakDis, cur, des, dir, s);
		return tempCar;
	}
}
