package absentee;

import java.util.Observable;
import java.util.Observer;

public abstract class Car extends Observable implements Observer{
	private double maxVelocity;
	private int curX, curY;
	private double breakDistance;
	private int desX, desY;
	public Car(){

	}
	public void move(Map curMap){
		notifyObservers();

	}

	public void update(Observable car, Object arg1){
		Car tempcar = (Car) car;
	}
}
