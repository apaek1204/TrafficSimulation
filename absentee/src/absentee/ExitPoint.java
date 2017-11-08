package absentee;

import java.awt.Point;

public class ExitPoint {
	private Car Currcar;
	private Point ExitPoint;
	public ExitPoint(Point point) {
		this.ExitPoint = point;
		this.Currcar = null;
	}
	public void Enter(Car car) {
		this.Currcar = car;
	}
	public void Exit() {
		this.Currcar = null;
	}
}