package hanze.project.logic;

import java.awt.*;
import java.util.Random;

public class ReservationCar extends Car {

        // FIELDS

        private static final Color COLOR= Color.DARK_GRAY;

        // CONSTRUCTORS

        public ReservationCar() {
            Random random = new Random();
            int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
            this.setMinutesLeft(stayMinutes);
            this.setHasToPay(false);
            this.setHasReservation(true);
        }

        // METHODS

        public Color getColor(){
            return COLOR;
        }
    }
