package absentee;

import java.awt.Point;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
public class Map {

	boolean[][] grid = new boolean[50][50];
	final int dimensions = 50;
	private ArrayList<Road> roadList = new ArrayList<Road>();
	private ArrayList<Road> startRoads = new ArrayList<Road>();
	private ArrayList<Road> closeList = new ArrayList<Road>();
	private static Map mapSingleton = new Map(); //Make map a singleton
	private EntryPoint startPoint;
	private Road startRoad;
	private Road nextRoad;
	private Roundabout oneround;
	private Intersection startInt;
	private ArrayList<Intersection> intList = new ArrayList<Intersection>();
	private Map() {
		this.startPoint = new EntryPoint(new Point(100,0));
		this.startRoad = new Road(100,new Point(100,0), new Point(100,100),3);
		this.startPoint.addRoad(this.startRoad);
		this.startInt = new Intersection(new Stoplight(0,1),new Point(100,100));
		this.startRoad.addIntersection(this.startInt);
		Road a = new Road(100,new Point(0,100), new Point(100,100),1);
		Road b = new Road(240,new Point(100,100), new Point(340,100),1);
		Road c = new Road(120,new Point(100,100), new Point(100,220),3);
		Road d = new Road(100,new Point(100,340), new Point(0,340),0);
		Road e = new Road(240,new Point(100,340), new Point(100,580),3);
		Road f = new Road(240,new Point(340,340), new Point(100,340),0);
		Road g = new Road(100,new Point(0,580), new Point(100,580),1);
		Road h = new Road(240,new Point(100,580),new Point(340,580),1);
		Road i = new Road(170,new Point(100,580), new Point(100,750),3);
		Road j = new Road(100,new Point(340,100), new Point(340,0),2);
		Road j1 = new Road(240,new Point(340,100), new Point(580,100),1);
		Road j2 = new Road(100,new Point(340,320), new Point(340,220),2);
		Road k = new Road(240,new Point(580,340), new Point(360,340),0);
		Road l = new Road(240,new Point(340,580), new Point(340,360),2);
		Road m = new Road(240,new Point(340,580), new Point(580,580),1);
		Road n = new Road(170,new Point(340,750), new Point(340,580),2);
		Road o = new Road(240,new Point(580,100), new Point(580,340),3);
		Road p = new Road(100,new Point(580,0), new Point(580,100),3);
		Road q = new Road(170,new Point(580,100), new Point(750,100),1);
		Road r = new Road(170,new Point(750,340), new Point(580,340),0);
		Road s = new Road(170,new Point(580,580), new Point(750,580),1);
		Road t = new Road(240,new Point(580,340), new Point(580,580),3);
		Road u = new Road(170,new Point(580,580), new Point(580,750),3);
		Road c2 = new Road(120,new Point(100,220), new Point(100,340),3);
		Road j2b = new Road(120,new Point(340,220), new Point(340,100),2);
		Road nr = new Road(240,new Point(340,220), new Point(100,220),0);
		Road dummy = new Road(0,new Point(340,220), new Point(340,220),0);

		this.oneround = new Roundabout(new Point (340,340));
//		this.startRoads.add(a);
		this.startRoads.add(this.startRoad);
//		this.startRoads.add(g);
		this.startRoads.add(n);
//		this.startRoads.add(r);
		this.startRoads.add(p);

		Intersection temp = new Intersection(new Stoplight(0,1), new Point(100,340));
		Intersection temp2 = new Intersection(new Stoplight(0,1), new Point(100,580));
		Intersection temp3 = new Intersection(new Stoplight(0,1), new Point(340,100));
//		Intersection temp4 = new Intersection(new Stoplight(0,0), new Point(340,340));
		Intersection temp5 = new Intersection(new Stoplight(0,1), new Point(340,580));
		Intersection temp6 = new Intersection(new Stoplight(0,1), new Point(580,100));
		Intersection temp7 = new Intersection(new Stoplight(0,1), new Point(580,340));
		Intersection temp8 = new Intersection(new Stoplight(0,1), new Point(580,580));
		Intersection temp9 = new Intersection(new Stoplight(0,1), new Point(340,220));
		Intersection temp10 = new Intersection(new Stoplight(0,1), new Point(100,220));

		j2.addIntersection(temp9);
		temp9.addRoad(nr);
		temp9.addRoad(dummy);
		temp9.addRoad(j2b);
		temp9.addRoad(j2);

		c.addIntersection(temp10);
		nr.addIntersection(temp10);
		temp10.addRoad(dummy);
		temp10.addRoad(nr);
		temp10.addRoad(c);
		temp10.addRoad(c2);

		j2b.addIntersection(temp3);
		b.addIntersection(temp3);
		temp3.addRoad(b);
		temp3.addRoad(j1);
		temp3.addRoad(j);
		temp3.addRoad(j2);

		l.addRoundabout(this.oneround);
		k.addRoundabout(this.oneround);
		this.oneround.addRoad(f);
		this.oneround.addRoad(k);
		this.oneround.addRoad(j2);
		this.oneround.addRoad(l);

		n.addIntersection(temp5);
		h.addIntersection(temp5);
		temp5.addRoad(h);
		temp5.addRoad(m);
		temp5.addRoad(l);
		temp5.addRoad(n);

		p.addIntersection(temp6);
		j1.addIntersection(temp6);
		temp6.addRoad(j1);
		temp6.addRoad(q);
		temp6.addRoad(p);
		temp6.addRoad(o);

		o.addIntersection(temp7);
		r.addIntersection(temp7);
		temp7.addRoad(k);
		temp7.addRoad(r);
		temp7.addRoad(o);
		temp7.addRoad(t);

		t.addIntersection(temp8);
		m.addIntersection(temp8);
		temp8.addRoad(m);
		temp8.addRoad(s);
		temp8.addRoad(t);
		temp8.addRoad(u);

		c2.addIntersection(temp);
		f.addIntersection(temp);
		temp.addRoad(d);
		temp.addRoad(f);
		temp.addRoad(c);
		temp.addRoad(e);
		a.addIntersection(this.startInt);

		e.addIntersection(temp2);
		g.addIntersection(temp2);
		temp2.addRoad(g);
		temp2.addRoad(h);
		temp2.addRoad(e);
		temp2.addRoad(i);
		this.startInt.addRoad(a);
		this.startInt.addRoad(b);
		this.startInt.addRoad(this.startRoad);
		this.startInt.addRoad(c);
		this.intList.add(this.startInt);
		this.intList.add(temp2);
		this.intList.add(temp);

		this.intList.add(temp3);
//		this.intList.add(temp4);
		this.intList.add(temp5);
		this.intList.add(temp6);
		this.intList.add(temp7);
		this.intList.add(temp8);
		this.intList.add(temp9);
		this.intList.add(temp10);
		this.roadList.add(a);
		this.roadList.add(b);
		this.roadList.add(c);
		this.roadList.add(this.startRoad);
		this.roadList.add(d);
		this.roadList.add(e);
		this.roadList.add(f);
		this.roadList.add(g);
		this.roadList.add(h);
		this.roadList.add(i);
		this.roadList.add(j);
		this.roadList.add(k);
		this.roadList.add(l);
		this.roadList.add(m);
		this.roadList.add(n);
		this.roadList.add(o);
		this.roadList.add(p);
		this.roadList.add(q);
		this.roadList.add(r);
		this.roadList.add(s);
		this.roadList.add(t);
		this.roadList.add(u);
		this.roadList.add(j1);
		this.roadList.add(j2);
		this.roadList.add(nr);
		this.roadList.add(j2b);
		this.roadList.add(c2);

		// Add roads to closeList
		this.closeList.add(b);
		this.closeList.add(c);
		this.closeList.add(m);
		this.closeList.add(t);
	}

	public ArrayList<Road> getStartRoads(){
		return this.startRoads;
	}
	public Roundabout getRound() {
		return this.oneround;
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

	public ArrayList<Road> getClosedRoads()
	{
		return this.closeList;
	}

	public void drawYRInt(ObservableList<Node> root) {
		System.out.println("YR DRAWING");
		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				if(x==17 && y ==17) {
					continue;
				}
				Image tempImage = new Image("file:src/images/yr.png",137,137, true, true);
				ImageView tempView = new ImageView();
				tempView.setImage(tempImage);
				tempView.setX(x*20);
				tempView.setY(y*20);
				tempView.setFitHeight(20);
				tempView.setPreserveRatio(true);
				root.add(tempView);
			}
		}
		Image tempImage = new Image("file:src/images/yr.png",137,137, true, true);
		ImageView tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(100);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/yr.png",137,137, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(340);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
	}
	public void drawGRInt(ObservableList<Node> root) {

		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				if(x==17 && y ==17) {
					continue;
				}
				Image tempImage = new Image("file:src/images/gr.png",137,137, true, true);
				ImageView tempView = new ImageView();
				tempView.setImage(tempImage);
				tempView.setX(x*20);
				tempView.setY(y*20);
				tempView.setFitHeight(20);
				tempView.setPreserveRatio(true);
				root.add(tempView);
			}
		}
		Image tempImage = new Image("file:src/images/gr.png",137,137, true, true);
		ImageView tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(100);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/gr.png",137,137, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(340);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
	}
	public void drawRYInt(ObservableList<Node> root) {

		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				if(x==17 && y ==17) {
					continue;
				}
				Image tempImage = new Image("file:src/images/ry.png",137,137, true, true);
				ImageView tempView = new ImageView();
				tempView.setImage(tempImage);
				tempView.setX(x*20);
				tempView.setY(y*20);
				tempView.setFitHeight(20);
				tempView.setPreserveRatio(true);
				root.add(tempView);
			}
		}
		Image tempImage = new Image("file:src/images/ry.png",137,137, true, true);
		ImageView tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(100);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/ry.png",137,137, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(340);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
	}
	public void drawRGInt(ObservableList<Node> root) {

		for(int x= 5; x< 30; x+=12) {
			for(int y = 5; y<30; y+=12) {
				if(x==17 && y ==17) {
					continue;
				}
				Image tempImage = new Image("file:src/images/rg.png",137,137, true, true);
				ImageView tempView = new ImageView();
				tempView.setImage(tempImage);
				tempView.setX(x*20);
				tempView.setY(y*20);
				tempView.setFitHeight(20);
				tempView.setPreserveRatio(true);
				root.add(tempView);
			}
		}
		Image tempImage = new Image("file:src/images/rg.png",137,137, true, true);
		ImageView tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(100);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/rg.png",137,137, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(340);
		tempView.setY(220);
		tempView.setFitHeight(20);
		tempView.setPreserveRatio(true);
		root.add(tempView);
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
				if(x==17 && y ==17) {
					continue;
				}
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
		drawSegmentHoriz(6,17,11,root,scale);
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
		Circle circ = new Circle(350,350,30,Color.GREY);
		circ.setStroke(Color.GREY);
		circ.setFill(Color.GREY);
		root.add(circ);
		circ = new Circle(350,350,10,Color.GREEN);
		circ.setStroke(Color.GREEN);
		circ.setFill(Color.GREEN);
		root.add(circ);
		Image tempImage = new Image("file:src/images/1.png",146,169, true, true);
		ImageView tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(140);
		tempView.setY(370);
		root.add(tempView);

		tempImage = new Image("file:src/images/2.png",167,200, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(360);
		tempView.setY(140);
		tempView.setFitHeight(100);
		tempView.setPreserveRatio(true);
		root.add(tempView);

		tempImage = new Image("file:src/images/3.png",369,290, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(360);
		tempView.setY(250);
		tempView.setFitHeight(80);
		tempView.setPreserveRatio(true);
		root.add(tempView);

		tempImage = new Image("file:src/images/5.png",273,192, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(370);
		tempView.setY(460);
		tempView.setFitHeight(100);
		tempView.setPreserveRatio(true);
		root.add(tempView);

		tempImage = new Image("file:src/images/6.png",160,140, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(130);
		tempView.setY(620);
		tempView.setFitHeight(100);
		tempView.setPreserveRatio(true);
		root.add(tempView);

		tempImage = new Image("file:src/images/7.png",417,158, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(370);
		tempView.setY(10);
		tempView.setFitHeight(80);
		tempView.setPreserveRatio(true);
		root.add(tempView);

		tempImage = new Image("file:src/images/9.png",145,144, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(20);
		tempView.setY(20);
		tempView.setFitHeight(80);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/n7.png",101,80, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(620);
		tempView.setY(170);
		tempView.setFitHeight(80);
		tempView.setPreserveRatio(true);
		root.add(tempView);
		
		tempImage = new Image("file:src/images/n8.png",89,77, true, true);
		tempView = new ImageView();
		tempView.setImage(tempImage);
		tempView.setX(620);
		tempView.setY(390);
		tempView.setFitHeight(80);
		tempView.setPreserveRatio(true);
		root.add(tempView);
	}
}
