package hanze.project.logic;

import java.util.LinkedList;
import java.util.Queue;

// CLASS

public class CarQueue {

    // FIELDS

    private Queue<Car> queue = new LinkedList<>();

    // METHODS

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return this.queue.size();
    }

    public Car frontCar(){
        return queue.peek();
    }

}