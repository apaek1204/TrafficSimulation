package absentee;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLight {

	@Test
	public void test() {
		Stoplight s = new Stoplight(1, 0);
		s.swap();
		s.swap();
		s.swap();
		assert(s.lights[3] == 1);
		//fail("Not yet implemented");
	}

}
