package absentee;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point2D.Double cur, ArrayList<Point> des, Road r, Point s){
		Car tempCar = new BasicCar(maxVel, breakDis, cur, des, r, s);
		return tempCar;
	}
}
