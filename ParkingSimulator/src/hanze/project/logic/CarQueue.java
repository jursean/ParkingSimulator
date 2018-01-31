package hanze.project.logic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class Car
 * Deze klasse zorgt ervoor dat de auto's in de garage komen.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class CarQueue {

    // De velden

    private Queue<Car> queue = new LinkedList<>();

    // De methodes

    /**
     * Voegt een auto toe aan de wachtrij.
     * @param car Auto die toegevoegd moet worden.
     * @return boolean Of de auto in de rij staat
     */

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Haalt auto weg uit de wachtrij.
     * @return Car uit de wachtrij
     */

    public Car removeCar() {
        return queue.poll();
    }

    /**
     * Geeft de lengte van de rij terug.
     * @return int Lengte van de rij
     */

    public int carsInQueue(){
    	return this.queue.size();
    }

    /**
     * Geeft de voorste auto in de wachtrij terug.
     * @return Car voorste auto uit rij
     */

    public Car frontCar(){
        return queue.peek();
    }

}
