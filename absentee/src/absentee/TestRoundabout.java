package absentee;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Test;

public class TestRoundabout {

	@Test
	public void test() {
		Road r = new Road(3, new Point(0, 0), new Point(0, 3), 0);
		Roundabout ro = new Roundabout(new Point(0, 0));
		ro.addRoad(r);
		ro.addRoad(r);
		ro.addRoad(r);
		ro.addRoad(r);
		ArrayList<Point> des = new ArrayList<Point>();
		des.add(new Point(0, 1));
		Car c = new BasicCar(0, 0, new Point2D.Double(100, 0), des, r, new Point(0, 0));
		assertTrue(ro.canEnter(c));
		ro.Enter(c);
		assertFalse(ro.canEnter(c));
	}

}
