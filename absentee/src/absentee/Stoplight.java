package absentee;


public class Stoplight{
	//0 red, 1 green, 2 yellow
	int[] lights;
	public Stoplight(int lr, int ud) {
		lights = new int[4];
		lights[0] = lr;
		lights[1] = lr;
		lights[2] = ud;
		lights[3] = ud;
	}
	//swap the lights of the stoplight
	public void swap()
	{
		for( int i = 0; i < 4; i++) {
			if(lights[i] == 1) {
				lights[i] = 2;
			}
			else if(lights[i] == 2) {
				lights[i] = 0;
			}
			else {
				lights[i] = 1;
			}
		}
	}
	//change the stoplight
	public void changeLight(int lr, int ud) {
		lights[0] = lr;
		lights[1] = lr;
		lights[2] = ud;
		lights[3] = ud;
	}
}
