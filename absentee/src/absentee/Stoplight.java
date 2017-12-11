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
		int ud = lights[3];
		int lr = lights[1];
		if(ud == 1 && lr == 0) {
			lights[0] = 0;
			lights[1] = 0;
			lights[2] = 2;
			lights[3] = 2;
		}
		else if(ud == 2 && lr == 0) {
			lights[0] = 1;
			lights[1] = 1;
			lights[2] = 0;
			lights[3] = 0;
		}
		else if(ud == 0 && lr == 1) {
			lights[0] = 2;
			lights[1] = 2;
			lights[2] = 0;
			lights[3] = 0;
		}
		else if(ud == 0 && lr == 2) {
			lights[0] = 0;
			lights[1] = 0;
			lights[2] = 1;
			lights[3] = 1;
		}
		else {
			System.out.println("OTHER");
		}
//		for( int i = 0; i < 4; i++) {
////			else if(lights[i] == 2) {
////				lights[i] = 0;
////			}
//			if(lights[i] == 1) {
//				lights[i] = 2;
//			}
//			else if(lights[i] == 2) {
//				lights[i] = 0;
//			}
//			else if(lights[i]==0) {
//				lights[i] = 1;
//			}
//		}
	}
	//change the stoplight
	public void changeLight(int lr, int ud) {
		lights[0] = lr;
		lights[1] = lr;
		lights[2] = ud;
		lights[3] = ud;
	}
}
