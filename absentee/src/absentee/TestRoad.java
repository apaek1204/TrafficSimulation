/*package absentee;

import static org.junit.Assert.*;

import java.awt.Point;
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
		Road road1 = new Road(100, start, end);
		Stoplight sl = new Stoplight(0, 1);
		Intersection i = new Intersection(sl, end);
		road1.addIntersection(i);
		Car car1 = new BasicCar(5, 0, start, endList, 1, size); //maxVel, breakDis, cur, des, dir, s

		if(road1.canEnter(size)){
			road1.Enter(car1);
		}
		//test that road is full and cannot add another car
		assert(road1.canEnter(size) == false);


		//fail("Not yet implemented");
	}

}
*/