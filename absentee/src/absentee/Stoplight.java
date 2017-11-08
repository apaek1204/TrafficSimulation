package absentee;

public class Stoplight {
	//0 red, 1 green, 2 yellow
	int[] lights;
	public Stoplight(int lr, int ud) {
		lights = new int[4];
		lights[0] = lr;
		lights[1] = lr;
		lights[2] = ud;
		lights[3] = ud;
	}
}
