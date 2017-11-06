
public class Position {
	int directions[];
	boolean isOccupied;
	void enter() {
		this.isOccupied = true;
	}
	void exit() {
		this.isOccupied = false;
	}	
}
