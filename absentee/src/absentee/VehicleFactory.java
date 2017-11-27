package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point cur, ArrayList<Point> des, int dir, Point s){
		Car tempCar = new BasicCar(maxVel, breakDis, cur, des, dir, s);
		return tempCar;
	}
}
