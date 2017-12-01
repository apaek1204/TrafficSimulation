package absentee;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Car extends Observable implements Observer{
	public double maxVelocity;
	public double curVelocity;
	public Point2D.Double curPos;
	public double breakDistance;
	public ArrayList<Point> destination;
	public ImageView carImage;
	public Point size;
	public Road road;
	private int timerRate=5;
	public ArrayList<Integer> directionList= new ArrayList<Integer>();

	//public int direction = 0;	//0 = left, 1 = right, 2 = up, 3 = down
	public Car(double maxVel, double breakDis, Point2D.Double cur, ArrayList<Point> des,Road r, Point s){
		maxVelocity = maxVel;
		curVelocity = maxVel;
		curPos = cur;
		breakDistance = breakDis;
		destination = des;
		road = r;
		size = s;
		directionList.add(1);
		directionList.add(1);
		directionList.add(3);
		directionList.add(0);
		directionList.add(2);
		directionList.add(1);
		directionList.add(3);
		directionList.add(0);
		directionList.add(0);
		directionList.add(3);
		directionList.add(1);
		directionList.add(2);
		directionList.add(0);
	}
	public Point2D.Double move(){
		//every time move is called, add the current velocity to the car's position
		switch(road.direction){
			//0 = left, 1 = right, 2 = up, 3 = down
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
		setChanged();
		notifyObservers();

		return curPos;
	}
	int counter = 0;

	public void setRoad(Road r){
		//changes car image based on new direction
		Timer rotationTimer = new Timer();
		switch(r.direction){
			case 0:
				switch(road.direction){
					case 2:
						rotationTimer.schedule(
							new TimerTask() {
						        public void run() {
							        Platform.runLater(new Runnable() {
							            public void run() {
								        	counter++;
								        	curPos.x -= 10/timerRate;
											curPos.y -= 18/timerRate;
											carImage.setRotate(carImage.getRotate() - 90/timerRate);
											carImage.setX(curPos.x);
											carImage.setY(curPos.y);
											if(counter >= 5){
												rotationTimer.cancel();
												counter=0;
											}
							            }
							        });
						        }
							}, 0, 4*timerRate);
						break;
					case 3:
						rotationTimer.schedule(
							new TimerTask() {
						        public void run() {
						        	Platform.runLater(new Runnable() {
							            public void run() {
								        	counter++;
								        	curPos.x -= 10/timerRate;
											curPos.y += 18/timerRate;
											carImage.setRotate(carImage.getRotate() + 90/timerRate);
											carImage.setX(curPos.x);
											carImage.setY(curPos.y);
											if(counter >= 5){
												rotationTimer.cancel();
												counter=0;
											}
							            }
							        });
						        }
							}, 0, 4*timerRate);
						break;
				}
				road = r;
				break;
			case 1:
				switch(road.direction){
					case 2:
						rotationTimer.schedule(
								new TimerTask() {
							        public void run() {
							        	Platform.runLater(new Runnable() {
								            public void run() {
									        	counter++;
									        	curPos.x += 10/timerRate;
												curPos.y -= 18/timerRate;
												carImage.setRotate(carImage.getRotate() + 90/timerRate);
												carImage.setX(curPos.x);
												carImage.setY(curPos.y);
												if(counter >= 5){
													rotationTimer.cancel();
													counter=0;
												}
								            }
								        });
							        }
								}, 0, 4*timerRate);
							break;
					case 3:
						rotationTimer.schedule(
							new TimerTask() {
						        public void run() {
						        	Platform.runLater(new Runnable() {
						        		public void run() {
								        	counter++;
								        	curPos.x += 10/timerRate;
											curPos.y += 18/timerRate;
											carImage.setRotate(carImage.getRotate() - 90/timerRate);
											carImage.setX(curPos.x);
											carImage.setY(curPos.y);
											if(counter >= 5){
												rotationTimer.cancel();
												counter=0;
											}
							            }
							        });
						        }
							}, 0, 4*timerRate);
						break;
				}
				road = r;
				break;
			case 2:
				switch(road.direction){
					case 0:
						rotationTimer.schedule(
							new TimerTask() {
						        @Override
						        public void run() {
						        	Platform.runLater(new Runnable() {
						        		public void run() {
								        	counter++;
								        	curPos.x -= 10/timerRate;
											curPos.y -= 18/timerRate;
											carImage.setRotate(carImage.getRotate() + 90/timerRate);
											carImage.setX(curPos.x);
											carImage.setY(curPos.y);
											if(counter >= 5){
												rotationTimer.cancel();
												counter=0;
											}
						        		}
						        	});
						        }
							}, 0, 4*timerRate);
						break;
					case 1:
						rotationTimer.schedule(
								new TimerTask() {
							        @Override
							        public void run() {
							        	Platform.runLater(new Runnable() {
							        		public void run() {
									        	counter++;
									        	curPos.x += 10/timerRate;
												curPos.y -= 18/timerRate;
												carImage.setRotate(carImage.getRotate() - 90/timerRate);
												carImage.setX(curPos.x);
												carImage.setY(curPos.y);
												if(counter >= 5){
													rotationTimer.cancel();
													counter=0;
												}
							        		}
							        	});
							        }
								}, 0, 4*timerRate);
							break;
				}
				road = r;
				break;
			case 3:
				switch(road.direction){
					case 0:
						rotationTimer.schedule(
								new TimerTask() {
							        @Override
							        public void run() {
							        	Platform.runLater(new Runnable() {
							        		public void run() {
									        	counter++;
									        	curPos.x -= 10/timerRate;
												curPos.y += 18/timerRate;
												carImage.setRotate(carImage.getRotate() - 90/timerRate);
												carImage.setX(curPos.x);
												carImage.setY(curPos.y);
												if(counter >= 5){
													rotationTimer.cancel();
													counter=0;
												}
							        		}
							        	});
							        }
								}, 0, 4*timerRate);
							break;
					case 1:
						rotationTimer.schedule(
							new TimerTask() {
						        @Override
						        public void run() {
						        	Platform.runLater(new Runnable() {
						        		public void run() {
								        	counter++;
								        	curPos.x += 10/timerRate;
											curPos.y += 18/timerRate;
											carImage.setRotate(carImage.getRotate() + 90/timerRate);
											carImage.setX(curPos.x);
											carImage.setY(curPos.y);
											if(counter >= timerRate){
												rotationTimer.cancel();
												counter=0;
											}
						        		}
						        	});
						        }
							}, 0, 4*timerRate);

						break;
				}
				road = r;
				break;
		}


	}
	public int getNextDirection(){

		return directionList.get(0);
	}
	public int getNextDirection(ArrayList<Road> nextRoads){
		if(destination.size() > 0){
			double deltaX = destination.get(0).x - curPos.x;
			double deltaY = destination.get(0).y - curPos.y;
			if(Math.abs(deltaX) >= Math.abs(deltaY) ){
				if(deltaX>=0 && nextRoads.get(1) != null){
					return 1;
				}
				else if(deltaX < 0 && nextRoads.get(0) != null ){
					return 0;
				}
				else if(nextRoads.get(2) != null){
					return 2;
				}
				else if(nextRoads.get(3) != null){
					return 3;
				}
			}
			else if (Math.abs(deltaY) > Math.abs(deltaX)){
				if(deltaY>=0 && nextRoads.get(3) != null){
					return 3;
				}
				else if(deltaY<0 && nextRoads.get(2) != null){
					return 2;
				}
				else if(nextRoads.get(0) != null){
					return 0;
				}
				else if(nextRoads.get(1) != null){
					return 1;
				}
			}
		}
		return road.direction;
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
			int stopDistance = 0;
			double distanceToObstacle;
			switch(road.direction){
				case 0:
					distanceToObstacle = curPos.x - (tempcar.curPos.x + tempcar.size.y);
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					break;
				case 1:
					distanceToObstacle = (curPos.x + size.y - (tempcar.curPos.x))*-1;
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					break;
				case 2:
					distanceToObstacle = curPos.y - (tempcar.curPos.y + tempcar.size.y);
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
					break;
				case 3:
					distanceToObstacle = (curPos.y +size.y - tempcar.curPos.y)* -1;
					curVelocity = (maxVelocity / (breakDistance - stopDistance)) * (distanceToObstacle - stopDistance);
					curVelocity = Math.max(0.0, curVelocity);
					curVelocity = Math.min(maxVelocity, curVelocity);
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
