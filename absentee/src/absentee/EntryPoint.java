package absentee;

import java.awt.Point;
import java.util.ArrayList;

public class EntryPoint {
	private Car Currcar;
	private Point StartPoint;
	private Road NextRoad = null;
	public EntryPoint(Point point) {
		this.NextRoad = null;
		this.StartPoint = point;
		this.Currcar = null;
	}
	public void Enter(Car car) {
		this.Currcar = car;
	}
	public void addRoad(Road r) {
		this.NextRoad = r;
	}
	public void Exit() {
		if(NextRoad.canEnter(this.Currcar.size)) {
			NextRoad.Enter(this.Currcar);
			this.Currcar = null;
		}
	}
}
