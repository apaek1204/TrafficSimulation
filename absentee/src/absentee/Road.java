package absentee;
import java.awt.Point;
import java.util.ArrayList;

public class Road {
	//private Queue<Car> cars = new LinkedList<Car>();
	private ArrayList<Car> carsList = new ArrayList<Car>();
	Car lastCar=null;
	private int streetLength;
	private Point endPoint;
	private Point startPoint;
	private Intersection intersection;
	public Road(int len, Point end, Point start){
		streetLength = len;
		endPoint = end;
		startPoint = start;
		intersection = null;
	}
	public void addIntersection(Intersection i) {
		this.intersection = i;
	}
	public Point getStart(){
		return startPoint;
	}
	public Point getEnd(){
		return endPoint;
	}
	public void Enter(Car car){
		if(lastCar != null){
			lastCar.addObserver(car);
		}

		carsList.add(car);
		lastCar = car;

	}
	public boolean canEnter(Point carSize){
		int totalSize = 0;
		if(carsList.get(0).direction == 0 || carsList.get(0).direction == 1){
			for(int i=0; i<carsList.size(); i++){
				totalSize += carsList.get(i).size.x;
			}

		}
		else{
			for(int i=0; i<carsList.size(); i++){
				totalSize += carsList.get(i).size.y;
			}
		}
		if(carsList.get(0).direction == 0 || carsList.get(0).direction == 1){
			if(streetLength - totalSize >= carSize.x){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(streetLength - totalSize >= carSize.x){
				return true;
			}
			else{
				return false;
			}
		}

	}

	public void Exit(){
		if(canExit()) {
			Car nextCar = carsList.get(0);
			nextCar.deleteObserver(carsList.get(1));
			if(intersection.canEnter(nextCar)) {
				nextCar.curVelocity = nextCar.maxVelocity;
				carsList.remove(0);
				if(carsList.isEmpty()){
					lastCar = null;
				}
			} else {
				nextCar.curVelocity = 0;
			}
		}
	}
	private boolean canExit(){
		//0 = left, 1 = right, 2 = up, 3 = down
		switch(carsList.get(0).direction){
			case 0: if(carsList.get(0).curPos.x <= endPoint.x){
						return true;
					};
					break;
			case 1: if(carsList.get(0).curPos.x >= endPoint.x+carsList.get(0).size.x){
						return true;
					};
					break;
			case 2: if(carsList.get(0).curPos.y <= endPoint.y){
						return true;
					};
					break;
			case 3: if(carsList.get(0).curPos.y >= endPoint.y+carsList.get(0).size.y){
						return true;
					};
					break;
			default: break;
		}
		return false;
	}
}
