package absentee;

import java.util.Observable;
import java.util.Observer;

public abstract class Car extends Observable implements Observer{
	private double maxVelocity;
	//private point curPos;
	private double breakDistance;
	//private point destinations;

	public Car(){

	}
	public void move(){
		notifyObservers();

	}
	public void update(Observable car, Object arg1){
		Car tempcar = (Car) car;

	}
}
