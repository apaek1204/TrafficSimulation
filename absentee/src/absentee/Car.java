package absentee;

import java.awt.Point;
import java.util.ArrayList;
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
	public ArrayList<Point> destination;
	public ImageView carImage;
	public Point size;
	private Image carL, carU, carD, carR;


	public int direction = 0;	//0 = left, 1 = right, 2 = up, 3 = down
	public Car(double maxVel, double breakDis, Point cur, ArrayList<Point> des, int dir, Point s){
		maxVelocity = maxVel;
		curVelocity = maxVel;
		curPos = cur;
		breakDistance = breakDis;
		destination = des;
		direction = dir;
		size = s;
		if(dir == 0 || dir == 1){
			carL = new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
			carR = new Image("file:src/images/basicCarRight.png",size.x,size.y, true, true);
			carU = new Image("file:src/images/basicCarUp.png",size.y,size.x, true, true);
			carD = new Image("file:src/images/basicCarDown.png",size.y,size.x, true, true);
		}
		else{
			carL = new Image("file:src/images/basicCarLeft.png",size.y,size.x, true, true);
			carR = new Image("file:src/images/basicCarRight.png",size.y,size.x, true, true);
			carU = new Image("file:src/images/basicCarUp.png",size.x,size.y, true, true);
			carD = new Image("file:src/images/basicCarDown.png",size.x,size.y, true, true);
		}
	}
	public Point move(){
		//every time move is called, add the current velocity to the car's position
		//System.out.println(curPos);
		switch(direction){
			//0 = left, 1 = right, 2 = up, 3 = down
			//4 = turn left
			case 0: curPos.x -= curVelocity;
					carImage.setImage(carL);
					break;
			case 1: curPos.x += curVelocity;
					carImage.setImage(carR);
					break;
			case 2: curPos.y -= curVelocity;
					carImage.setImage(carU);
					break;
			case 3: curPos.y += curVelocity;
					carImage.setImage(carD);
					break;
			default: break;
		}
		//direction = getNextDirection();
		setChanged();
		notifyObservers();

		return curPos;
	}
	public void setDirection(int dir){
		Image carI;
		//changes car image based on new direction
		//hasnt been tested
		switch(dir){
			case 0:
				switch(direction){
				case 2:
					carI= new Image("file:src/images/basicCarUpLeft.png",size.x*1.44,size.y*1.44, true, true);
					carImage.setImage(carI);
					curPos.x -= 5;
					curPos.y -= 1;
					carImage.setX(curPos.x);
					carImage.setY(curPos.y);
					curPos.x -= 5;
					curPos.y -=1;
					break;
				case 3:
					carI= new Image("file:src/images/basicCarDownLeft.png",size.x*1.44,size.y*1.44, true, true);
					carImage.setImage(carI);
					curPos.x -= 5;
					curPos.y += 5;
					carImage.setX(curPos.x);
					carImage.setY(curPos.y);
					curPos.x -= 5;
					curPos.y += 5;
					break;
			}
			direction = dir;
				break;
			case 1:
				switch(direction){
					case 2:
						carI= new Image("file:src/images/basicCarUpRight.png",size.x*1.44,size.y*1.44, true, true);
						carImage.setImage(carI);
						curPos.x += 5;
						curPos.y -= 1;
						carImage.setX(curPos.x);
						carImage.setY(curPos.y);
						curPos.x += 5;
						curPos.y -=1;
						break;
					case 3:
						carI= new Image("file:src/images/basicCarDownRight.png",size.x*1.44,size.y*1.44, true, true);
						carImage.setImage(carI);
						curPos.x += 5;
						curPos.y += 10;
						carImage.setX(curPos.x);
						carImage.setY(curPos.y);
						curPos.x += 5;
						curPos.y += 10;
						size = new Point(size.y, size.x);
						break;
				}
				direction = dir;
				break;
			case 2:
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
			case 3:
				carI= new Image("file:src/images/basicCarLeft.png",size.x,size.y, true, true);
				carImage = new ImageView(carI);
				carImage.setX(curPos.x);
				carImage.setY(curPos.y);
				break;
		}


	}
	public int getNextDirection(){
		return 1;
		/*
		double deltaX = destination.get(0).x - curPos.x;
		double deltaY = destination.get(0).y - curPos.y;
		if(Math.abs(deltaX) > Math.abs(deltaY)){
			if(deltaX>0){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			if(deltaY>0){
				return 3;
			}
			else{
				return 2;
			}
		}*/
	}
	public void popDestination(){
		if(destination.get(0) != null){
			destination.remove(0);
		}
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
