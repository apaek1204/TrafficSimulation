package absentee;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Street {
	//private Queue<Car> cars = new LinkedList<Car>();
	private ArrayList<Car> carsList = new ArrayList<Car>();
	Car lastCar=null;
	private int streetLength;
	public Street(int len){
		streetLength = len;
	}

	public void addCar(Car car){
		if(lastCar != null){
			lastCar.addObserver(car);
		}
		else{
			// not sure what to do here
		}
		carsList.add(car);
		lastCar = car;

	}
	public boolean canEnter(Car car){
		int totalSize = 0;
		for(int i=0; i<carsList.size(); i++){
			totalSize += carsList.get(i).size;
		}
		if(streetLength - totalSize >= car.size){
			return true;
		}
		else{
			return false;
		}
	}

	public void removeCar(){
		carsList.remove(0);
		if(carsList.get(0) == null){
			lastCar = null;
		}
	}
}
