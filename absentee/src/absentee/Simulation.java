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


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	private void startSailing() {


	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = new AnchorPane();
		level_map = new Map();
		level_map.drawMap(root.getChildren(), 20);
		scene = new Scene(root,750,750);
		factory = new VehicleFactory();
		Point start = new Point(100,0);
		carList.add(factory.createBasicCar(5, 0, start, start, 3,1));
		Image carImage = new Image("file:src/images/basicCar.png",20,20, true, true);
		for(int i=0; i<carList.size(); i++){
			carList.get(i).carImage = new ImageView(carImage);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
			root.getChildren().add(carList.get(i).carImage);
		}

		stage.setTitle("Tokyo Drift");
		stage.setScene(scene);
		stage.show();
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
			System.out.println(carList.get(i).curPos.x);
			System.out.println(carList.get(i).direction);
			carList.get(i).carImage.setX(carList.get(i).curPos.x);
			carList.get(i).carImage.setY(carList.get(i).curPos.y);
		}
	}

}
