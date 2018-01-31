package hanze.project.logic;

import java.awt.*;

/**
 * Class Car
 * Deze klasse zorgt ervoor dat de auto's in de garage komen.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public abstract class Car {

    // De velden

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean hasReservation;
    private double totaalPrijs;

    // De constructors


    public Car() {

    }

    // De methodes

    /**
     * Deze methode geeft de locatie terug.
     * @return locatie
     */

    public Location getLocation() {
        return location;
    }

    /**
     * Met deze methode wordt de locatie ingesteld waar de auto moet gaan staan.
     * @param location locatie van de auto
     */

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Met deze methode wordt terug gegeven hoe lang de auto blijft staan op de parkeerplaats.
     * @return int minuten resterend
     */

    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Met deze methode wordt ingesteld hoe lang de auto blijft staan op de parkeerplaats.
     * @param int Hoelang de auto blijft staan
     */

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * Controleert of de auto aan het betalen is.
     * @return boolean Of de auto aan het betalen is
     */

    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Stelt in of de auto aan het betalen is.
     * @param boolean Of de auto aan het betalen is
     */

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Geeft terug of de auto moet betalen.
     * @return boolean Of de auto moet betalen
     */

    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * Steld in of de auto moet betalen .
     * @param boolean Of de auto moet betalen
     */

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }
    /**
    * Geeft de totale prijs
    */
    public void setTotaalPrijs(double totaalPrijs) {
        this.totaalPrijs = totaalPrijs;
    }
/**
* geeft de totale prijs terug
*/
    public double getTotaalPrijs() {
        return totaalPrijs;
    }
    /**
     * Geeft terug of de auto heeft gereserveerd.
     * @return boolean Of de auto heeft gereserveerd
     */

    public boolean getHasReservation(){
        return hasReservation;
    }

    /**
     * Stelt in of de auto heeft gereserveerd.
     * @param hasReservation
     */

    public void setHasReservation(boolean hasReservation){
        this.hasReservation = hasReservation;
    }

    /**
     * Haalt een minute af van de tijd die de auto's nog blijven staan.
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     * Geeft de kleur van de auto terug.
     * @return Color De kleur die de auto heeft
     */
    public abstract Color getColor();
}
