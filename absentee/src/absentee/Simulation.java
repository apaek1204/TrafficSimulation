package absentee;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;
public class Simulation extends Application{

	Map level_map;
	Scene scene;
	VehicleFactory factory;
	ArrayList<Car> carList = new ArrayList<Car>();
	ArrayList<Intersection> oneIntersection = new ArrayList<Intersection>();
	ArrayList<Road> roadList = new ArrayList<Road>();
	Timer timer  = new Timer();
	Timer carTimer = new Timer();
	TimerTask carTask;
	ArrayList<Point> startPoints = new ArrayList<Point>();
	Landmarks arr[] = Landmarks.values();
	ArrayList<Point> landmarks = new ArrayList<Point>();

	//ReschedulableTimer rTimer = new ReschedulableTimer();

	EntryPoint startingPoint;
	int scale = 20;
	Double start;
	AnchorPane root;
	int timeRun = 1000000;
	int carMin = 2000;
	int carMax = 15000;
	int carTime = 5000; //Between 2000 and 15000
	Slider slider = new Slider(carMin, carMax, 5000);
	int oldTime = carTime;
	int animationTime = 100;
	int changeOnce = 1;
	Roundabout oneRound;
	//JSlider slider = new JSlider(2000, 15000, carTime);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	public void setupStartPoints()
	{
		startPoints.add(new Point(100, 0));
	}


	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		root = new AnchorPane();
		level_map = Map.getInstance();
		level_map.drawMap(root.getChildren(), 20);
		scene = new Scene(root,750,750);
		factory = new VehicleFactory();

		roadList = level_map.getRoads();
		this.startingPoint = level_map.getEntryPoint();
		this.oneIntersection = level_map.getIntersection();
		this.oneRound = level_map.getRound();
		for (Landmarks tmpL : arr)
        {
        	//System.out.println(tmpL.getPoint());
        	landmarks.add(tmpL.getPoint());
        }

		ArrayList<Road> closeRoads = level_map.getClosedRoads();
		Random closeRGenerator = new Random();
		int numChosen = closeRGenerator.nextInt(5);
		switch(numChosen)
		{
			case 0:
				closeRoads.get(0).closeRoad();
				System.out.println("Close: " + closeRoads.get(0).getStart() + "," + closeRoads.get(0).getEnd());
				break;
			case 1:
				closeRoads.get(1).closeRoad();
				System.out.println("Close: " + closeRoads.get(1).getStart() + "," + closeRoads.get(1).getEnd());
				break;
			case 2:
				closeRoads.get(2).closeRoad();
				System.out.println("Close: " + closeRoads.get(2).getStart() + "," + closeRoads.get(2).getEnd());
				break;
			case 3:
				closeRoads.get(3).closeRoad();
				System.out.println("Close: " + closeRoads.get(3).getStart() + "," + closeRoads.get(3).getEnd());
				break;
			case 4:
				closeRoads.get(4).closeRoad();
				System.out.println("Close: " + closeRoads.get(4).getStart() + "," + closeRoads.get(4).getEnd());
				break;
		}


		//start = startingPoint.getPoint();

		/*
		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    System.out.println(new_val.doubleValue());
                    opacityValue.setText(String.format("%.2f", new_val));
            }
        });
        */
		//slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1000f);
        slider.setBlockIncrement(100f);
		root.getChildren().add(slider);


		setupStartPoints();

		stage.setTitle("Celadon City/Tokyo Drift/Detroit");
		stage.setScene(scene);
		stage.show();

		/* Old TImer for reference
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				//runGame(root.getChildren());
			}
		}.start();
		*/

		// Here is another timer that is an extension of Animation Timer and slows it down.  (See class AnimationTimerExtension.java)
		new AnimationTimerExtension(100){ //Handles overall running of the simulation
			@Override
			public void handle() {
				runGame(root.getChildren());
				exitRoads(root.getChildren());
				exitEntryPoints(root.getChildren());
			}
		}.start();

		new AnimationTimerExtension(3000){ //Handles overall running of the simulation
			@Override
			public void handle() {
				changeLights(root.getChildren());
			}
		}.start();

		carTimer.schedule(carTask = new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		                makeCar(root.getChildren());
		            	//label.update();
		                //javafxcomponent.doSomething();
		            }
		        });
		    }
		}, carTime, carTime);

		/*
		carTimer.schedule(
			new TimerTask() {

	        @Override
	        public void run() {
	            makeCar(root.getChildren());
	        }
		}, carTime, carTime);
		*/

		/*
		new AnimationTimerExtension(carTime){//Handles creation of a new car at set intervals
			@Override
			public void handle() {
				//if(carList.size()<1)
				makeCar(root.getChildren());
			}
		}.start();
		*/

		//start timer /** Old Code **/
		timer.schedule(
	    new TimerTask() {

	        @Override
	        public void run() {
	            endGame();
	        }
		}, timeRun, timeRun);
	}

	public void changeLights(ObservableList<Node> rootNodeList)
	{
		//System.out.println("Change Light");
		for(int i = 0; i < oneIntersection.size();i++) {
			this.oneIntersection.get(i).stoplight.swap();
		}
		if(this.oneIntersection.get(0).stoplight.lights[3] == 1) {
			this.level_map.drawGreenInt(root.getChildren());
		}
		else if(this.oneIntersection.get(0).stoplight.lights[3] == 0) {
			this.level_map.drawRedInt(root.getChildren());
		}
		else {
			this.level_map.drawYellowInt(root.getChildren());
		}
		//this.oneIntersection.stoplight.changeLight(0, 1);
	}

	public void exitEntryPoints(ObservableList<Node> rootNodeList)
	{
		this.startingPoint.Exit();
	}

	public void exitRoads(ObservableList<Node> rootNodeList)
	{
		for(int i = 0; i < oneIntersection.size();i++) {
			this.oneIntersection.get(i).Exit();
		}
		//this.oneIntersection.Exit();
		for(int i = 0; i < roadList.size(); i++)
		{
			//System.out.println("We are here");
			//if(roadList.get(i).getIntsection())
				//System.out.println("Road " + i + " has an intersection.");
			roadList.get(i).Exit();
			this.oneRound.Exit();
		}
	}

	//Set the image of a new car when it is made


	//Create a new car
	public void makeCar(ObservableList<Node> rootNodeList)
	{

		//System.out.println("Adding a car");
		Point size = new Point(20,35);

		//System.out.println("Start: " + start);
		start = new Point2D.Double(100, 0);
		//No destination stuff here
		ArrayList<Point> des = new ArrayList<Point>();
		/*
		des.add(new Point(340,100));
		des.add(new Point(340,340));
		*/
		Random randomGenerator = new Random();
		int numChosen = randomGenerator.nextInt(3) + 1;
		for(int i = 0; i < numChosen; i++)
		{
			int tmpNumber = randomGenerator.nextInt(10);
			//while(!des.contains(landmarks.get(tmpNumber)))
			//{
			//tmpNumber = randomGenerator.nextInt(10);
				des.add(landmarks.get(tmpNumber));
			//}
		}


		ArrayList<Road> startRoads = level_map.getStartRoads();
		int value = randomGenerator.nextInt(startRoads.size());
		Road temproad = startRoads.get(value);
		if(temproad.direction == 0 || temproad.direction == 1) {
			size = new Point(35,20);
		}
		Image carImage = new Image("file:src/images/basicCar.png",size.x,size.y, true, true);
		switch(value) {
			case 0:
				start = new Point2D.Double(100, 0);
				break;
			case 1:
				start = new Point2D.Double(340, 750);
				break;
			case 2:
				start = new Point2D.Double(580, 0);
				break;
		}
		Car tmpCar = factory.createBasicCar(10, 20, start,des, temproad,size);
		tmpCar.carImage = new ImageView(carImage);
		switch(value) {
		case 0:
			tmpCar.carImage.setRotate(0);
			break;
		case 1:
			tmpCar.carImage.setRotate(180);
			break;
		case 2:
			tmpCar.carImage.setRotate(0);
			break;
	}
		tmpCar.carImage.setX(tmpCar.curPos.x);
		tmpCar.carImage.setY(tmpCar.curPos.y);
		rootNodeList.add(tmpCar.carImage);
		carList.add(tmpCar);
		//this.startingPoint.Enter(tmpCar);

		temproad.Enter(tmpCar);
	}

	//Move each car that is made
	public void runGame(ObservableList<Node> rootNodeList){
		carTime = (int)slider.getValue();
		if(oldTime != carTime)
		{
			System.out.println("Changing Time");
			if(changeOnce == 1)
			{
				carTask.cancel();
				changeOnce = 0;
			}
			oldTime = carTime;
			carTimer.schedule(carTask = new TimerTask() {
			    public void run() {
			         Platform.runLater(new Runnable() {
			            public void run() {
			                makeCar(root.getChildren());
			            	//label.update();
			                //javafxcomponent.doSomething();
			            }
			        });
			    }
			}, carTime, carTime);
			changeOnce = 1;
		}

		//System.out.println((int)slider.getValue());
		for(int i = 0; i < roadList.size(); i++)
		{
			//System.out.println("Road: " + i);
			if(roadList.get(i).getCarSize() > 0)
			{
				//System.out.println("Road: " + i);
				//System.out.println("Size: " + roadList.get(i).getCarSize());
			}
		}
		for(int i=0; i<carList.size(); i++){
			carList.get(i).move();
			//System.out.println("Car xPosition: " + carList.get(i).curPos.x);
			//System.out.println("Car yPosition: " + carList.get(i).curPos.y);
			//System.out.println("Car Direction: " + carList.get(i).direction);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
		}
	}

	public void endGame()
	{
		System.exit(0);
	}

}
