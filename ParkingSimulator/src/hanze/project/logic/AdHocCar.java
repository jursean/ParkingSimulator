package hanze.project.logic;

import java.util.Random;
import java.awt.*;

// CLASS

public class AdHocCar extends Car {

    // FIELDS

	private static final Color COLOR=Color.red;

    // CONSTRUCTORS

    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    	double teBetalenBedrag = stayMinutes * 0.02;
    	this.setTotaalPrijs(teBetalenBedrag);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setHasReservation(false);
    }

    // METHODS

    public Color getColor(){
    	return COLOR;
    }
}
