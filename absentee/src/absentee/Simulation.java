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


	private void startSailing() {


	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = new AnchorPane();
		level_map = Map.getInstance();
		level_map.drawMap(root.getChildren(), 20);
		scene = new Scene(root,750,750);
		factory = new VehicleFactory();

		setupStartPoints();
		Point start = new Point(100,0);
		Point size = new Point(20,20);
		carList.add(factory.createBasicCar(5, 0, start, start, 3,size));


		Image carImage = new Image("file:src/images/basicCar.png",size.x,size.y, true, true);
		for(int i=0; i<carList.size(); i++){
			carList.get(i).carImage = new ImageView(carImage);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
			root.getChildren().add(carList.get(i).carImage);
		}

		stage.setTitle("Tokyo Drift");
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(event -> {
			timer.cancel();
			timer.purge();
		});
		this.startSailing();
		//start timer
				timer.schedule(
					    new TimerTask() {

					        @Override
					        public void run() {
					            runGame();
					        }
					    }, 0, 200);
	}
	public void runGame(){
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
