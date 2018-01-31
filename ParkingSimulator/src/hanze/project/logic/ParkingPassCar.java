package hanze.project.logic;

import java.util.Random;
import java.awt.*;

/**
 * Class ParkingPassCar
 * Deze klasse breid de "Car" klasse uit en creëert de "ParkingPassCar" auto's (abbonement houders).
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class ParkingPassCar extends Car {

    // De velden

	private static final Color COLOR=Color.blue;

    // De constructors

    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasReservation(false);
    }

    // De methodes

    /**
     * Geeft de kleur van de auto terug.
     * @return COLOR Kleur van de auto
     */

    public Color getColor(){
        return COLOR;
    }
}
