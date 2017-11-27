package absentee;

import java.awt.Point;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Map {

	boolean[][] grid = new boolean[50][50];
	final int dimensions = 50;
	private ArrayList<Road> roadList = new ArrayList<Road>();
	private static Map mapSingleton = new Map(); //Make map a singleton
	private EntryPoint startPoint;
	private Road startRoad;
	private Road nextRoad;
	private Intersection startInt;
	private ArrayList<Intersection> intList = new ArrayList<Intersection>();
	private Map() {
		this.startPoint = new EntryPoint(new Point(100,0));
		this.startRoad = new Road(100,new Point(100,0), new Point(100,100),3);
		this.startPoint.addRoad(this.startRoad);
		this.startInt = new Intersection(new Stoplight(0,0),new Point(100,100));
		this.startRoad.addIntersection(this.startInt);
		Road a = new Road(100,new Point(0,100), new Point(100,100), 1);
		Road b = new Road(100,new Point(100,100), new Point(340,100),1);
		Road c = new Road(240,new Point(100,100), new Point(100,340),3);
		Road d = new Road(100,new Point(0,340), new Point(100,340),1);
		Road e = new Road(410,new Point(340,340), new Point(340,750),1);
		Road f = new Road(100,new Point(340,340), new Point(440,340),3);
		Intersection temp = new Intersection(new Stoplight(0,0), new Point(340,340));
		d.addIntersection(temp);
		f.addIntersection(temp);
		e.addIntersection(temp);
		temp.addRoad(d);
		temp.addRoad(f);
		temp.addRoad(c);
		temp.addRoad(e);
		a.addIntersection(this.startInt);
		b.addIntersection(this.startInt);
		c.addIntersection(temp);
		this.startInt.addRoad(a);
		this.startInt.addRoad(b);
		this.startInt.addRoad(this.startRoad);
		this.startInt.addRoad(c);
		this.intList.add(this.startInt);
		this.intList.add(temp);
		this.roadList.add(a);
		this.roadList.add(b);
		this.roadList.add(c);
		this.roadList.add(this.startRoad);
		this.roadList.add(d);
		this.roadList.add(e);
		this.roadList.add(f);

	}

	public ArrayList<Road> getRoads(){
		return this.roadList;
	}

	public EntryPoint getEntryPoint() {
		return this.startPoint;
	}

	public static Map getInstance(){
		return mapSingleton;
	}

	public ArrayList<Intersection> getIntersection()
	{
		return this.intList;
	}

	public void drawGreenInt(ObservableList<Node> root) {
		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				Rectangle rect = new Rectangle(x*20,y*20,20,20);
				rect.setStroke(Color.GREEN);
				rect.setFill(Color.GREEN);
				root.add(rect);
			}
		}
	}

	public void drawRedInt(ObservableList<Node> root) {
		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				Rectangle rect = new Rectangle(x*20,y*20,20,20);
				rect.setStroke(Color.RED);
				rect.setFill(Color.RED);
				root.add(rect);
			}
		}
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
