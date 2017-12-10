package absentee;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Test;

public class TestRoad {

	@Test
	public void test() {
		Point start = new Point(0,100);
		ArrayList <Point> endList = new ArrayList<Point>();
		Point end = new Point(20,100);
		endList.add(end);
		Point size = new Point(20,14);
		Road road1 = new Road(3, new Point(0, 0), new Point(0, 3), 0);
		Stoplight sl = new Stoplight(0, 1);
		Intersection i = new Intersection(sl, end);
		road1.addIntersection(i);
		ArrayList<Point> des = new ArrayList<Point>();
		des.add(new Point(0, 1));
		Car car1 = new BasicCar(0, 0, new Point2D.Double(100, 0), des, road1, new Point(0, 0)); //maxVel, breakDis, cur, des, dir, s

		if(road1.canEnter(size)){
			road1.Enter(car1);
		}
		//test that road is full and cannot add another car
		assert(road1.canEnter(size) == false);


		//fail("Not yet implemented");
	}

}
