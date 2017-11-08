package absentee;
import java.util.ArrayList;

public class Road {
	//private Queue<Car> cars = new LinkedList<Car>();
	private ArrayList<Car> carsList = new ArrayList<Car>();
	Car lastCar=null;
	private int streetLength;
	public Road(int len){
		streetLength = len;
	}

	public void Enter(Car car){
		if(lastCar != null){
			lastCar.addObserver(car);
		}
		else{
			// not sure what to do here
		}
		carsList.add(car);
		lastCar = car;

	}
	public boolean canEnter(double carSize){
		int totalSize = 0;
		for(int i=0; i<carsList.size(); i++){
			totalSize += carsList.get(i).size;
		}
		if(streetLength - totalSize >= carSize){
			return true;
		}
		else{
			return false;
		}
	}

	public void Exit(){
		carsList.remove(0);
		if(carsList.get(0) == null){
			lastCar = null;
		}
	}
}
