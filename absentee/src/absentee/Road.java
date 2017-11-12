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
		this.intersection = null;
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

	}
	public boolean canEnter(Point carSize){
		int totalSize = 0;
		if(carsList.size() > 0)
		{
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
		return false;
	}

	public void Exit(){
		if(canExit()) {
			Car nextCar = carsList.get(0);
			if(nextCar == null || intersection == null)
					return;
			//System.out.println(intersection.canEnter(nextCar));
			if(intersection.canEnter(nextCar)) {
				System.out.println("Trying to pop car");
				if(carsList.size() > 1)
				{
					nextCar.deleteObserver(carsList.get(carsList.size()-1));
				}
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
			//System.out.println("X: " + carsList.get(0).curPos.x);
			//System.out.println("Y: " + carsList.get(0).curPos.y);
			switch(carsList.get(0).direction){
				case 0: if(carsList.get(0).curPos.x <= endPoint.x){
							//System.out.println("Case 0");
							return true;
						};
						break;
				case 1: if(carsList.get(0).curPos.x >= endPoint.x+carsList.get(0).size.x){
							//System.out.println("Case 1");
							return true;
						};
						break;
				case 2: if(carsList.get(0).curPos.y <= endPoint.y){
							//System.out.println("Case 2");
							return true;
						};
						break;
				case 3: if(carsList.get(0).curPos.y >= endPoint.y+carsList.get(0).size.y && carsList.get(0).curPos.y > 80){
							System.out.println("Case 3");
							return true;
						};
						break;
				default: break;
			}
		}
		return false;
	}
}
