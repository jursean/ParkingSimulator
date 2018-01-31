package hanze.project.logic;

import java.util.Random;
import java.awt.*;

/**
 * Class AdHocCar
 * Deze klasse breid de "Car" klasse uit en creëert de "Ad Hoc" auto's (normale auto's).
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class AdHocCar extends Car {

    // De velden

	private static final Color COLOR=Color.red;

	// De constructors

    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setHasReservation(false);
    }

    /**
     * Deze methode wordt gebruikt om de kleur van de auto terug te geven.
     * @return COLOR De kleur van de auto
     */

    public Color getColor(){
    	return COLOR;
    }

}
