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
	public int direction;
	public Road(int len, Point start,Point end, int dir){
		streetLength = len;
		endPoint = end;
		startPoint = start;
		this.intersection = null;
		direction = dir;
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
	public int getCarSize()
	{
		return carsList.size();
	}
	public boolean getIntsection()
	{
		if(this.intersection != null)
			return true;
		return false;
	}
	public void Enter(Car car){
		if(lastCar != null){
			lastCar.addObserver(car);
		}
		carsList.add(car);
		lastCar = car;
		car.deleteObservers();
	}
	public boolean canEnter(Point carSize){
		int totalSize = 0;
		if(carsList.size() > 0)
		{
			if(direction == 0 || direction == 1){
				for(int i=0; i<carsList.size(); i++){
					totalSize += carsList.get(i).size.x;
				}

			}
			else{
				for(int i=0; i<carsList.size(); i++){
					totalSize += carsList.get(i).size.y;
				}
			}
			if(direction == 0 || direction == 1){
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
		return false;
	}

	public void Exit(){
		if(canExit()) {
			Car nextCar = carsList.get(0);
			if(nextCar == null || intersection == null)
					return;
			//System.out.println(intersection.canEnter(nextCar));
			if(intersection.canEnter(nextCar)) {
				//System.out.println("Trying to pop car");
				intersection.Enter(nextCar);
				carsList.remove(0);
				if(carsList.isEmpty()){
					lastCar = null;
				}
			}
		}
	}
	private boolean canExit(){
		//0 = left, 1 = right, 2 = up, 3 = down
		if(carsList.size()>0){
			//see if car edge is at edge of the road
			switch(direction){
				case 0: if(carsList.get(0).curPos.x <= endPoint.x-20){
							//System.out.println("Case 0");
							carsList.get(0).curVelocity = 0;
							return true;
						};
						break;
				case 1: if(carsList.get(0).curPos.x >= endPoint.x-carsList.get(0).size.x){
							carsList.get(0).curVelocity = 0;
							return true;
						};
						break;
				case 2: if(carsList.get(0).curPos.y <= endPoint.y-20){
							//System.out.println("Case 2");
							carsList.get(0).curVelocity = 0;
							return true;
						};
						break;
				case 3: if(carsList.get(0).curPos.y >= endPoint.y-carsList.get(0).size.y){
							carsList.get(0).curVelocity = 0;
							return true;
						};
						break;
				default: break;
			}
		}
		return false;
	}
}
