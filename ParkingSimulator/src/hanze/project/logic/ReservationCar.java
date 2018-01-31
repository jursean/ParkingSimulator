package hanze.project.logic;

import java.awt.*;
import java.util.Random;

/**
 * Class Reservationcar
 * Deze klasse breid de "Car" klasse uit en creëert de "Reservationcar" auto's (auto's die gereserveerd hebben).
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class ReservationCar extends Car {

        // De velden

        private static final Color COLOR= Color.DARK_GRAY;

        // De constructors

        public ReservationCar() {
            Random random = new Random();
            int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
            this.setMinutesLeft(stayMinutes);
            this.setHasToPay(false);
            this.setHasReservation(true);
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
