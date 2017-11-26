package absentee;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.Image;
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
	public Point move(){
		//every time move is called, add the current velocity to the car's position
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
		setChanged();
		notifyObservers();

		return curPos;
	}
	public void setDirection(int dir){
		direction = dir;
		Image carI;
		//changes car image based on new direction
		//hasnt been tested
		switch(direction){
			case 0: curPos.x -= curVelocity;
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
			case 1: curPos.x += curVelocity;
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
			case 2: curPos.y -= curVelocity;
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
			case 3: curPos.y += curVelocity;
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
		}
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
			int stopDistance = 1;
			int distanceToObstacle;
			switch(direction){

				case 0:
					distanceToObstacle = curPos.x - (tempcar.curPos.x + tempcar.size.x);
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					System.out.println("vel is = " + curVelocity);
					break;
				case 1:
					distanceToObstacle = (curPos.x + size.x - (tempcar.curPos.x))*-1;
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					System.out.println("vel is = " + curVelocity);
					break;
				case 2:
					distanceToObstacle = curPos.y - (tempcar.curPos.y + tempcar.size.y);
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					System.out.println("vel is = " + curVelocity);
					break;
				case 3:
					distanceToObstacle = (curPos.y +size.y - tempcar.curPos.y)* -1;
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					System.out.println("vel is = " + curVelocity);
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
