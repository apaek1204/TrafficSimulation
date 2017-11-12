package absentee;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
	Intersection oneIntersection;
	ArrayList<Road> roadList = new ArrayList<Road>();
	Timer timer  = new Timer();
	ArrayList<Point> startPoints = new ArrayList<Point>();

	EntryPoint startingPoint;
	int scale = 20;
	int timeRun = 0;
	Point start;
	AnchorPane root;


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
		//start = startingPoint.getPoint();

		setupStartPoints();

		stage.setTitle("Tokyo Drift");
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
		new AnimationTimerExtension(1000){ //Handles overall running of the simulation
			@Override
			public void handle() {
				runGame(root.getChildren());
				exitRoads(root.getChildren());
				exitEntryPoints(root.getChildren());
			}
		}.start();

		new AnimationTimerExtension(20000){//Handles creation of a new car at set intervals
			@Override
			public void handle() {
				makeCar(root.getChildren());
			}
		}.start();

		////start timer /** Old Code **/
		//timer.schedule(
	   // new TimerTask() {

	    //    @Override
	    //    public void run() {
	    //        runGame(root.getChildren());
	    //    }
		//}, 0, 200);
	}

	public void exitEntryPoints(ObservableList<Node> rootNodeList)
	{
		this.startingPoint.Exit();
	}

	public void exitRoads(ObservableList<Node> rootNodeList)
	{
		this.oneIntersection.Exit();
		for(int i = 0; i < roadList.size(); i++)
		{
			//System.out.println("We are here");
			//if(roadList.get(i).getIntsection())
				//System.out.println("Road " + i + " has an intersection.");
			roadList.get(i).Exit();
		}
	}

	//Set the image of a new car when it is made
	public void setCarImages(Point size, ObservableList<Node> rootNodeList){
		Image carImage = new Image("file:src/images/basicCar.png",size.x,size.y, true, true);
		int i = carList.size() - 1;

		carList.get(i).carImage = new ImageView(carImage);
		carList.get(i).carImage.setX(carList.get(i).curPos.x);
		carList.get(i).carImage.setY(carList.get(i).curPos.y);
		rootNodeList.add(carList.get(i).carImage);

		/*
		for(int i=0; i<carList.size(); i++){
			carList.get(i).carImage = new ImageView(carImage);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
			rootNodeList.add(carList.get(i).carImage);
		}
		*/
	}

	//Create a new car
	public void makeCar(ObservableList<Node> rootNodeList)
	{
		System.out.println("Adding a car");
		Point size = new Point(20,20);

		//System.out.println("Start: " + start);
		start = new Point(100, 0);
		Car tmpCar = factory.createBasicCar(5, 0, start,start, 3,size);
		carList.add(tmpCar);
		//this.startingPoint.Enter(tmpCar);
		roadList.get(3).Enter(tmpCar);
		setCarImages(size, rootNodeList);
	}

	//Move each car that is made
	public void runGame(ObservableList<Node> rootNodeList){

		for(int i = 0; i < roadList.size(); i++)
		{
			//System.out.println("Road: " + i);
			if(roadList.get(i).getCarSize() > 0)
			{
				System.out.println("Road: " + i);
				System.out.println("Size: " + roadList.get(i).getCarSize());
			}
		}
		for(int i=0; i<carList.size(); i++){
			carList.get(i).move(level_map);
			//System.out.println("Car xPosition: " + carList.get(i).curPos.x);
			//System.out.println("Car yPosition: " + carList.get(i).curPos.y);
			//System.out.println("Car Direction: " + carList.get(i).direction);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
		}
	}

}
