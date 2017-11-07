package absentee;
import java.util.LinkedList;
import java.util.Queue;

public class Street {
	private Queue<Car> cars = new LinkedList<Car>();
	public void addCar(Car car){
		cars.add(car);
	}
	public void removeCar(){
		cars.poll();
	}
}
