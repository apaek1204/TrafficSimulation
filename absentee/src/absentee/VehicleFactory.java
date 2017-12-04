package absentee;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class VehicleFactory {

	public Car createBasicCar(double maxVel, double breakDis, Point2D.Double cur, ArrayList<Point> des, Road r, Point s){
		Random rand = new Random();
		int randCar = rand.nextBoolean() ? 1 : -1;
		Car tempCar;
		if(randCar == 1) {
			tempCar = new BasicCar(maxVel, breakDis, cur, des, r, s);
		} else {
			tempCar = new Bus(maxVel, breakDis, cur, des, r, s);
		}
		return tempCar;
	}
}
