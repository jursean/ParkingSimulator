package hanze.project.logic;

import java.awt.*;

// CLASS

public abstract class Car {

    // FIELDS

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean hasReservation;

    // CONSTRUCTORS

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    // METHODS

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public boolean getHasReservation(){
        return hasReservation;
    }

    public void setHasReservation(boolean hasReservation){
        this.hasReservation = hasReservation;
    }

    public void tick() {
        minutesLeft--;
    }
    
    public abstract Color getColor();
}