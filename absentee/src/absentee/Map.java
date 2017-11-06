import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Map {
	boolean[][] grid = new boolean[25][25];
	Position [][] position = new Position[25][25];
	final int dimensions = 25;
	public void drawMap(ObservableList<Node> root,int scale) {
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.GREEN);
				rect.setFill(Color.GREEN);
				root.add(rect);
				grid[x][y] = false;
				position[x][y] = new Position();
			}
		}
	}
}
