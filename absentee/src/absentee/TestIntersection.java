package absentee;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class TestIntersection {

	@Test
	public void test() {
		Stoplight s = new Stoplight(0, 0);
		Road r = new Road(3, new Point(0, 0), new Point(0, 3));
		Road[] roads = new Road[4];
		roads[0] = r;
		roads[1] = r;
		roads[2] = r;
		roads[3] = r;
		Intersection i = new Intersection(s, new Point(0, 4));
		Car c = new BasicCar(0, 0, new Point(0, 0), new Point(0, 0), 0, new Point(0, 0));
		//cannot enter because red light
		assertFalse(i.canEnter(c));
		
		//change to green light
		s.changeLight(1, 1);
		assertTrue(i.canEnter(c));
		
		//enter car
		i.Enter(c);
		Car c2 = new BasicCar(0, 0, new Point(0, 0), new Point(0, 0), 0, new Point(0, 0));
		assertFalse(i.canEnter(c2));
	}

}
