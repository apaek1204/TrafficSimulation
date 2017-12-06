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
	public String carType;
	public ArrayList<Integer> directionList= new ArrayList<Integer>();

	//public int direction = 0;	//0 = left, 1 = right, 2 = up, 3 = down
	public Car(double maxVel, double breakDis, Point2D.Double cur, ArrayList<Point> des,Road r, Point s, String cT){
		maxVelocity = maxVel;
		curVelocity = maxVel;
		curPos = cur;
		breakDistance = breakDis;
		destination = des;
		road = r;
		size = s;
		carType = cT;
//		directionList.add(3);
//		directionList.add(3);
//		directionList.add(1);
//		directionList.add(2);
	}
	boolean enter = false;
	public Point2D.Double move(){
		//every time move is called, add the current velocity to the car's position
		int length;
		switch(road.direction){
			//0 = left, 1 = right, 2 = up, 3 = down
			case 0:
					curPos.x -= curVelocity;
					break;
			case 1:
					curPos.x += curVelocity;
					break;
			case 2:
					curPos.y -= curVelocity;
					break;
			case 3:
					curPos.y += curVelocity;
					break;

			default: break;
		}
		setChanged();
		notifyObservers();
		if(destination.size() > 0){
			if(curPos.x <= destination.get(0).x && curPos.x + size.x >= destination.get(0).x && curPos.y <= destination.get(0).y && curPos.y + size.y >= destination.get(0).y){
			popDestination();
			}
		}
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
	public boolean enterRoundabout(int enterDir, int exitDir){
		//enter = direction its entering from, exit = direction its exiting to
		Timer rotationTimer1 = new Timer();
		Timer rotationTimer2 = new Timer();
		Timer rotationTimer3 = new Timer();
		Timer rotationTimer4 = new Timer();
		Timer rotationTimer5 = new Timer();
		if(enterDir == 0 && exitDir == 0){
			rotationTimer1.schedule(
					new TimerTask() {
				        @Override
				        public void run() {
				        	Platform.runLater(new Runnable() {
				        		public void run() {
						        	counter++;
						        	curPos.x -= 0/timerRate;
									curPos.y -= 9/timerRate;
									carImage.setRotate(carImage.getRotate() + 90/timerRate);
									carImage.setX(curPos.x);
									carImage.setY(curPos.y);
									if(counter >= timerRate){
										rotationTimer1.cancel();
										counter=0;
										rotationTimer2.schedule(
												new TimerTask() {
											        @Override
											        public void run() {
											        	Platform.runLater(new Runnable() {
											        		public void run() {
													        	counter++;
													        	curPos.x -= 17/timerRate;
																curPos.y -= 18/timerRate;
																carImage.setRotate(carImage.getRotate() - 90/timerRate);
																carImage.setX(curPos.x);
																carImage.setY(curPos.y);
																if(counter >= timerRate){
																	rotationTimer2.cancel();
																	counter=0;
																	rotationTimer3.schedule(
																			new TimerTask() {
																		        @Override
																		        public void run() {
																		        	Platform.runLater(new Runnable() {
																		        		public void run() {
																				        	counter++;
																				        	curPos.x -= 20/timerRate;
																							curPos.y += 18/timerRate;
																							carImage.setRotate(carImage.getRotate() - 90/timerRate);
																							carImage.setX(curPos.x);
																							carImage.setY(curPos.y);
																							if(counter >= timerRate){
																								rotationTimer3.cancel();
																								counter=0;
																								rotationTimer4.schedule(
																										new TimerTask() {
																									        @Override
																									        public void run() {
																									        	Platform.runLater(new Runnable() {
																									        		public void run() {
																											        	counter++;
																											        	curPos.x -= 10/timerRate;
																														curPos.y += 9/timerRate;
																														carImage.setRotate(carImage.getRotate() + 90/timerRate);
																														carImage.setX(curPos.x);
																														carImage.setY(curPos.y);
																														if(counter >= timerRate){
																															rotationTimer4.cancel();
																															counter=0;

																														}
																									        		}
																									        	});
																									        }
																										}, 0, 4*timerRate);
																							}
																		        		}
																		        	});
																		        }
																			}, 0, 8*timerRate);
																}
											        		}
											        	});
											        }
												}, 0, 8*timerRate);
									}
				        		}
				        	});
				        }
					}, 0, 4*timerRate);
			carImage.setRotate(-90);

		}
		else if(enterDir == 0 && exitDir == 2){
			rotationTimer1.schedule(
					new TimerTask() {
				        @Override
				        public void run() {
				        	Platform.runLater(new Runnable() {
				        		public void run() {
						        	counter++;
						        	curPos.x -= 0/timerRate;
									curPos.y -= 9/timerRate;
									carImage.setRotate(carImage.getRotate() + 90/timerRate);
									carImage.setX(curPos.x);
									carImage.setY(curPos.y);
									if(counter >= timerRate){
										rotationTimer1.cancel();
										counter=0;
										rotationTimer2.schedule(
												new TimerTask() {
											        @Override
											        public void run() {
											        	Platform.runLater(new Runnable() {
											        		public void run() {
													        	counter++;
													        	curPos.x -= 17/timerRate;
																curPos.y -= 18/timerRate;
																carImage.setRotate(carImage.getRotate() - 90/timerRate);
																carImage.setX(curPos.x);
																carImage.setY(curPos.y);
																if(counter >= timerRate){
																	rotationTimer2.cancel();
																	counter=0;
																	rotationTimer4.schedule(
																			new TimerTask() {
																		        @Override
																		        public void run() {
																		        	Platform.runLater(new Runnable() {
																		        		public void run() {
																				        	counter++;
																				        	curPos.x -= 5/timerRate;
																							curPos.y -= 9/timerRate;
																							carImage.setRotate(carImage.getRotate() + 90/timerRate);
																							carImage.setX(curPos.x);
																							carImage.setY(curPos.y);
																							if(counter >= timerRate){
																								rotationTimer4.cancel();
																								counter=0;

																							}
																		        		}
																		        	});
																		        }
																			}, 0, 4*timerRate);
																}
											        		}
											        	});
											        }
												}, 0, 8*timerRate);
									}
				        		}
				        	});
				        }
					}, 0, 4*timerRate);
			carImage.setRotate(180);

		}
		else if(enterDir == 2 && exitDir == 2){
			rotationTimer1.schedule(
					new TimerTask() {
				        @Override
				        public void run() {
				        	Platform.runLater(new Runnable() {
				        		public void run() {
						        	counter++;
						        	curPos.x += 0/timerRate;
									curPos.y -= 8/timerRate;
									carImage.setRotate(carImage.getRotate() + 90/timerRate);
									carImage.setX(curPos.x);
									carImage.setY(curPos.y);
									if(counter >= timerRate){
										rotationTimer1.cancel();
										counter=0;
										rotationTimer2.schedule(
												new TimerTask() {
											        @Override
											        public void run() {
											        	Platform.runLater(new Runnable() {
											        		public void run() {
													        	counter++;
													        	curPos.x += 20/timerRate;
																curPos.y -= 15/timerRate;
																carImage.setRotate(carImage.getRotate() - 90/timerRate);
																carImage.setX(curPos.x);
																carImage.setY(curPos.y);
																if(counter >= timerRate){
																	rotationTimer2.cancel();
																	counter=0;
																	rotationTimer3.schedule(
																			new TimerTask() {
																		        @Override
																		        public void run() {
																		        	Platform.runLater(new Runnable() {
																		        		public void run() {
																				        	counter++;
																				        	curPos.x -= 20/timerRate;
																							curPos.y -= 25/timerRate;
																							carImage.setRotate(carImage.getRotate() - 90/timerRate);
																							carImage.setX(curPos.x);
																							carImage.setY(curPos.y);
																							if(counter >= timerRate){
																								rotationTimer3.cancel();
																								counter=0;
																								rotationTimer4.schedule(
																										new TimerTask() {
																									        @Override
																									        public void run() {
																									        	Platform.runLater(new Runnable() {
																									        		public void run() {
																											        	counter++;
																											        	curPos.x -= 0/timerRate;
																														curPos.y -= 9/timerRate;
																														carImage.setRotate(carImage.getRotate() + 90/timerRate);
																														carImage.setX(curPos.x);
																														carImage.setY(curPos.y);
																														if(counter >= timerRate){
																															rotationTimer4.cancel();
																															counter=0;

																														}
																									        		}
																									        	});
																									        }
																										}, 0, 4*timerRate);
																							}
																		        		}
																		        	});
																		        }
																			}, 0, 8*timerRate);
																}
											        		}
											        	});
											        }
												}, 0, 8*timerRate);
									}
				        		}
				        	});
				        }
					}, 0, 4*timerRate);
			carImage.setRotate(180);

		}
		else{
			rotationTimer1.schedule(
					new TimerTask() {
				        @Override
				        public void run() {
				        	Platform.runLater(new Runnable() {
				        		public void run() {
						        	counter++;
						        	curPos.x += 0/timerRate;
									curPos.y -= 8/timerRate;
									carImage.setRotate(carImage.getRotate() + 90/timerRate);
									carImage.setX(curPos.x);
									carImage.setY(curPos.y);
									if(counter >= timerRate){
										rotationTimer1.cancel();
										counter=0;
										rotationTimer2.schedule(
												new TimerTask() {
											        @Override
											        public void run() {
											        	Platform.runLater(new Runnable() {
											        		public void run() {
													        	counter++;
													        	curPos.x += 20/timerRate;
																curPos.y -= 15/timerRate;
																carImage.setRotate(carImage.getRotate() - 90/timerRate);
																carImage.setX(curPos.x);
																carImage.setY(curPos.y);
																if(counter >= timerRate){
																	rotationTimer2.cancel();
																	counter=0;
																	rotationTimer3.schedule(
																			new TimerTask() {
																		        @Override
																		        public void run() {
																		        	Platform.runLater(new Runnable() {
																		        		public void run() {
																				        	counter++;
																				        	curPos.x -= 20/timerRate;
																							curPos.y -= 25/timerRate;
																							carImage.setRotate(carImage.getRotate() - 90/timerRate);
																							carImage.setX(curPos.x);
																							carImage.setY(curPos.y);
																							if(counter >= timerRate){
																								rotationTimer3.cancel();
																								counter=0;
																								rotationTimer4.schedule(
																										new TimerTask() {
																									        @Override
																									        public void run() {
																									        	Platform.runLater(new Runnable() {
																									        		public void run() {
																											        	counter++;
																											        	curPos.x -= 20/timerRate;
																														curPos.y += 18/timerRate;
																														carImage.setRotate(carImage.getRotate() - 90/timerRate);
																														carImage.setX(curPos.x);
																														carImage.setY(curPos.y);
																														if(counter >= timerRate){
																															rotationTimer4.cancel();
																															counter=0;
																															rotationTimer5.schedule(
																																	new TimerTask() {
																																        @Override
																																        public void run() {
																																        	Platform.runLater(new Runnable() {
																																        		public void run() {
																																		        	counter++;
																																		        	curPos.x -= 10/timerRate;
																																					curPos.y += 7/timerRate;
																																					carImage.setRotate(carImage.getRotate()+ 90/timerRate);
																																					carImage.setX(curPos.x);
																																					carImage.setY(curPos.y);
																																					if(counter >= timerRate){
																																						rotationTimer5.cancel();
																																						counter=0;

																																					}
																																        		}
																																        	});
																																        }
																																	}, 0, 4*timerRate);

																														}
																									        		}
																									        	});
																									        }
																										}, 0, 8*timerRate);
																							}
																		        		}
																		        	});
																		        }
																			}, 0, 8*timerRate);
																}
											        		}
											        	});
											        }
												}, 0, 8*timerRate);
									}
				        		}
				        	});
				        }
					}, 0, 4*timerRate);
			carImage.setRotate(-90);

		}




		return false;

	}
//	public int getNextDirection(){
//
//		return directionList.get(0);
//	}
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
	}
}
