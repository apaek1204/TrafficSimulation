
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
		stage.setTitle("Car Simulation");
		stage.setScene(scene);
		stage.show();
		this.startSailing();
	}

}
