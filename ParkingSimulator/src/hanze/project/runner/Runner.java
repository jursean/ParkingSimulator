package hanze.project.runner;

import hanze.project.main.ParkingSimulator;

/**
 * Class Runner
 * Deze klasse zorgt ervoor dat de simulator word uitgevoerd.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class Runner {

    // De methodes

    /**
     * Zorgt ervoor dat de simulator uitgevoerd word.
     * @param args De argumenten
     */

    public static void main(String[] args){
        new ParkingSimulator();
    }

}
