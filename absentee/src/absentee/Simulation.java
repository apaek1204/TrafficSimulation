package absentee;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
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
	AnchorPane root;
	Scene scene;
	VehicleFactory factory;
	ArrayList<Car> carList = new ArrayList<Car>();
	Timer timer  = new Timer();
	ArrayList<Point> startPoints = new ArrayList<Point>();
	int scale = 20;
	int timeRun = 0;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	public void setupStartPoints()
	{
		startPoints.add(new Point(100, 0));
		startPoints.add(new Point(340, 0));
		/*
		for(int i = 100; i <= 580; i+=240)
		{
			Point start = new Point(i,0);
			startPoints.add(start);
			start = new Point(i, 750);
			startPoints.add(start);
			start = new Point(0, i);
			startPoints.add(start);
			start = new Point(750, i);
			startPoints.add(start);
		}
		*/
	}

	public void setCarImages(Point size)
	{

		Image carImage = new Image("file:src/images/basicCar.png",size.x,size.y, true, true);
		for(int i=0; i<carList.size(); i++){
			carList.get(i).carImage = new ImageView(carImage);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
			root.getChildren().add(carList.get(i).carImage);
		}

	}

	private void startSailing() {


	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		root = new AnchorPane();
		level_map = Map.getInstance();
		level_map.drawMap(root.getChildren(), 20);
		scene = new Scene(root,750,750);
		factory = new VehicleFactory();

		setupStartPoints();
		//Point start = new Point(100,0);
		Point size = new Point(20,20);


		carList.add(factory.createBasicCar(5, 0, startPoints.get(0), startPoints.get(0), 3,size));
		carList.add(factory.createBasicCar(5, 0, startPoints.get(1), startPoints.get(1), 3,size));
		setCarImages(size);


		stage.setTitle("Tokyo Drift");
		stage.setScene(scene);
		stage.show();
		this.startSailing();
		//start timer
				timer.schedule(
					    new TimerTask() {

					        @Override
					        public void run() {
					        	windowClosed(stage);
					        	runGame();
					        }
					    }, 0, 200);
	}

	public void windowClosed(Stage stageTmp)
	{
		if(stageTmp == null)
		{
			timer.cancel();
		}
	}

	public void runGame(){
		/*
		Point size = new Point(20,20);
		timeRun += 200;
		if(timeRun - 10000 == 0)
		{
			timeRun = 0;
			carList.add(factory.createBasicCar(5, 0, startPoints.get(0), startPoints.get(0), 3,size));
		}
*/

		for(int i=0; i<carList.size(); i++){
			carList.get(i).move(level_map);
			System.out.println("Car xPosition: " + carList.get(i).curPos.x);
			System.out.println("Car yPosition: " + carList.get(i).curPos.y);
			System.out.println("Car Direction: " + carList.get(i).direction);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
		}
	}

}
