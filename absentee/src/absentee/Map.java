package absentee;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Map {

	boolean[][] grid = new boolean[50][50];
	Position [][] position = new Position[50][50];
	final int dimensions = 50;

	private static Map mapSingleton = new Map(); //Make map a singleton

	private Map() { }

	public static Map getInstance(){
		return mapSingleton;
	}

	protected static void demoMethod()
	{
		System.out.println("demoMethod for singleton");
	}

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
		for(int x = 5; x < dimensions; x+=12) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.GREY);
				rect.setFill(Color.GREY);
				root.add(rect);
			}
		}
		for(int y = 5; y < dimensions; y+=12) {
			for(int x = 0; x < dimensions; x++) {
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.GREY);
				rect.setFill(Color.GREY);
				root.add(rect);
			}
		}

	}
}
