package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point cur, ArrayList<Point> des, Road r, Point s){
		Car tempCar = new BasicCar(maxVel, breakDis, cur, des, r, s);
		return tempCar;
	}
}
