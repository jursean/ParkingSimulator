package hanze.project.logic;

/**
 * Class Location
 * Deze klasse stelt de grootte van de parkeergarage in.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class Location {

    // De velden

    private int floor;
    private int row;
    private int place;

    // De constructors

    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    // De methodes

    /**
     * Checked of een auto op een bepaalde locatie staat.
     * @param obj objecten die vergeleken moeten worden
     * @return boolean
     */

    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }

    /**
     * Zet de locatie om naar een string met verdieping, rij en plaats.
     * @return string Met de locatie van de auto
     */

    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Geeft een 10 bit hashcode met daarin de locatie.
     * @return hashcode Met de locatie
     */

    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * Geeft de verdieping terug.
     * @return floor Verdieping
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Geeft de rij terug.
     * @return row Rij
     */
    public int getRow() {
        return row;
    }

    /**
     * Geeft de de plek terug.
     * @return place Plek
     */
    public int getPlace() {
        return place;
    }

}
