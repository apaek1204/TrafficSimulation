package absentee;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Test;

public class TestRoutePlanning {

	@Test
	public void test() {
		Point2D.Double start = new Point2D.Double(0,100);
		ArrayList <Point> endList = new ArrayList<Point>();
		Point end = new Point(20,100);
		endList.add(end);
		Point size = new Point(20,14);
		Road road1 = new Road(100, new Point(0,100), end, 3);

		Car car1 = new BasicCar(10, 5, start, endList, road1, size); //maxVel, breakDis, cur, des, road, s
		ArrayList<Road> roadList1 = new ArrayList<Road>();
		//test if only 1 direction is possible

		roadList1.add(null);
		roadList1.add(null);
		roadList1.add(null);
		roadList1.add(road1);
		assert(car1.getNextDirection(roadList1) == 3);

		//test if 2 directions are available and one of them is the way it wants to go
		//car starts at 0,100 and ends at 20,100 so should want to go to the right, direction = 1

		ArrayList<Road> roadList2 = new ArrayList<Road>();
		Road road2 = new Road(100, new Point(0,100), end, 1);
		roadList2.add(null);
		roadList2.add(road2);
		roadList2.add(null);
		roadList2.add(road1);
		assert(car1.getNextDirection(roadList2) == 1);



		//fail("Not yet implemented");
	}

}
