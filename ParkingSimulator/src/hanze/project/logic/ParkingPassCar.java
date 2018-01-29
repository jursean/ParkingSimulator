package hanze.project.logic;

import java.util.Random;
import java.awt.*;

// CLASS

public class ParkingPassCar extends Car {

    // FIELDS

	private static final Color COLOR=Color.blue;

    // CONSTRUCTORS

    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasReservation(false);
    }

    // METHODS

    public Color getColor(){
        return COLOR;
    }
}
