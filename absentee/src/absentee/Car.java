package absentee;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;

public abstract class Car extends Observable implements Observer{
	public double maxVelocity;
	public Point curPos;
	public double breakDistance;
	public Point destination;
	public ImageView carImage;

	public int direction = 0;	//0 = left, 1 = right, 2 = up, 3 = down
	public Car(double maxVel, double breakDis, Point cur, Point des, int dir){
		maxVelocity = maxVel;
		curPos = cur;
		breakDistance = breakDis;
		destination = des;
		direction = dir;
	}
	public Point move(Map curMap){
		notifyObservers();
		switch(direction){
			case 0: curPos.x -= maxVelocity;
					break;
			case 1: curPos.x += maxVelocity;
					break;
			case 2: curPos.y -= maxVelocity;
					break;
			case 3: curPos.y += maxVelocity;
					break;
			default: break;
		}

		return curPos;
	}
	public void setDirection(int dir){
		direction = dir;
	}

	public void update(Observable car, Object arg1){
		Car tempcar = (Car) car;
	}
}
