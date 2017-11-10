package absentee;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Map {

	boolean[][] grid = new boolean[50][50];
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
	public void drawSegmentHoriz(int x1, int x2, int y2,ObservableList<Node> root,int scale) {
			for(int x = x1; x < x2; x++) {
				Rectangle rect = new Rectangle(x*scale,y2*scale,scale,scale);
				rect.setStroke(Color.GREY);
				rect.setFill(Color.GREY);
				root.add(rect);
			}		
	}
	public void drawSegmentVert(int y1, int y2, int x2,ObservableList<Node> root,int scale) {
		for(int y = y1; y < y2; y++) {
			Rectangle rect = new Rectangle(x2*scale,y*scale,scale,scale);
			rect.setStroke(Color.GREY);
			rect.setFill(Color.GREY);
			root.add(rect);
		}		
}
	public void drawMap(ObservableList<Node> root,int scale) {
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.GREEN);
				rect.setFill(Color.GREEN);
				root.add(rect);
				grid[x][y] = false;
			}
		}
		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.RED);
				rect.setFill(Color.RED);
				root.add(rect);					
			}
		}
		drawSegmentHoriz(0,5,5,root,scale);
		drawSegmentHoriz(30,38,5,root,scale);
		drawSegmentHoriz(0,5,17,root,scale);
		drawSegmentHoriz(30,38,17,root,scale);
		drawSegmentHoriz(0,5,29,root,scale);
		drawSegmentHoriz(30,38,29,root,scale);
		drawSegmentVert(0,5,5,root,scale);
		drawSegmentVert(30,38,5,root,scale);
		drawSegmentVert(0,5,17,root,scale);
		drawSegmentVert(30,38,17,root,scale);
		drawSegmentVert(0,5,29,root,scale);
		drawSegmentVert(30,38,29,root,scale);		
		drawSegmentHoriz(6,17,5,root,scale);
		drawSegmentHoriz(18,29,5,root,scale);
		drawSegmentHoriz(6,17,17,root,scale);
		drawSegmentHoriz(18,29,17,root,scale);
		drawSegmentHoriz(6,17,29,root,scale);
		drawSegmentHoriz(18,29,29,root,scale);
		drawSegmentVert(6,17,5,root,scale);
		drawSegmentVert(18,29,5,root,scale);
		drawSegmentVert(6,17,17,root,scale);
		drawSegmentVert(18,29,17,root,scale);
		drawSegmentVert(6,17,29,root,scale);
		drawSegmentVert(18,29,29,root,scale);		
	}
}
