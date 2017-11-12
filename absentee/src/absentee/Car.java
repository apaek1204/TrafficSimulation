package absentee;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.ImageView;

public abstract class Car extends Observable implements Observer{
	public double maxVelocity;
	public double curVelocity;
	public Point curPos;
	public double breakDistance;
	public Point destination;
	public ImageView carImage;
	public Point size;
	public int direction = 0;	//0 = left, 1 = right, 2 = up, 3 = down
	public Car(double maxVel, double breakDis, Point cur, Point des, int dir, Point s){
		maxVelocity = maxVel;
		curVelocity = maxVel;
		curPos = cur;
		breakDistance = breakDis;
		destination = des;
		direction = dir;
		size = s;
	}
	public Point move(Map curMap){
		//every time move is called, add the current velocity to the car's position
		setChanged();
		notifyObservers();
		switch(direction){
			case 0: curPos.x -= curVelocity;
					break;
			case 1: curPos.x += curVelocity;
					break;
			case 2: curPos.y -= curVelocity;
					break;
			case 3: curPos.y += curVelocity;
					break;
			default: break;
		}
		//direction = getNextDirection();
		return curPos;
	}
	public void setDirection(int dir){
		direction = dir;
	}
	public int getNextDirection(){
		Random rand = new Random();
		int  n = rand.nextInt(4);
		return n;
	}
	@Override
	public void update(Observable obs, Object arg1){
		//if the observable is a car
		if(obs instanceof Car){
			//match car speed
			Car tempcar = (Car) obs;

			switch(direction){
				case 0: if(curPos.x <= tempcar.curPos.x + tempcar.size.x){
							//System.out.println("Case 0");
							curVelocity = 0;
						};
						break;
				case 1: if(curPos.x + size.x >= tempcar.curPos.x){
							//System.out.println("Case 1");
							curVelocity = 0;
						};
						break;
				case 2: if(curPos.y <= tempcar.curPos.y + tempcar.size.y){
							//System.out.println("Case 2");
							curVelocity = 0;
						};
						break;
				case 3: if(curPos.y + size.y >= tempcar.curPos.y){
							curVelocity = 0;
						};
						break;
				default:
						curVelocity = maxVelocity;
						break;
			}

		}
		/*
		//else if its a stoplight
		else if(obs instanceof Stoplight){
			Stoplight tempStop = (Stoplight) obs;
			//check if red or not, and change velocity as necessary
			if(direction == 0 || direction == 1){
				if(tempStop.lights[0] == 1){
					curVelocity = maxVelocity;
				}
				else{
					curVelocity = 0;
				}
			}
			else{
				if(tempStop.lights[3] == 1){
					curVelocity = maxVelocity;
				}
				else{
					curVelocity = 0;
				}
			}
		}
		*/
	}
}
