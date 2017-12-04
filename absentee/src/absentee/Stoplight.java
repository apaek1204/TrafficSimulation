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
	public void swap()
	{
/*		int first = lights[0];
		int second = lights[2];
		lights[0] = second;
		lights[1] = second;
		lights[2] = first;
		lights[3] = first;*/
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
	public void changeLight(int lr, int ud) {
		lights[0] = lr;
		lights[1] = lr;
		lights[2] = ud;
		lights[3] = ud;
	}
}
