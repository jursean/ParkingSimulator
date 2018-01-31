package hanze.project.logic;

import java.util.Random;

/**
 * Class Model
 * in deze klasse worden veel van de berekeningen gemaakt.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class Model extends AbstractModel {

    // De velden

    private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;
    private int numberOfOpenSpots;
    private int numberOfOpenResvSpots;

    private Car[][][] cars;

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
    private static final String RESV = "3";

	private CarQueue entranceCarQueue;
    private CarQueue entrancePassResvQueue;

    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private Time time;

    private int tickPause = 100;

    // Gemiddeld aantal auto's per uur
    private int weekDayArrivals= 100;
    private int weekendArrivals = 200;
    private int voorstellingArrivals = 250;
    private int koopAvondArrivals = 220;

    private int weekDayPassArrivals= 50;
    private int weekendPassArrivals = 60;
    private int voorstellingPassArrivals = 110;
    private int koopAvondPassArrivals = 50;

    private int weekDayResvArrivals = 30;
    private int weekendResvArrivals = 60;
    private int voorstellingResvArrivals = 150;
    private int koopAvondResvArrivals = 50;

    private int enterSpeed = 3; // number of cars that can enter per minute
    private int paymentSpeed = 7; // number of cars that can pay per minute
    private int exitSpeed = 5; // number of cars that can leave per minute

    private double turnoverTotal;

    private int noPassholder = 0;
    private int passHolder = 0;
    private int reservationHolder = 0;

    private int totalCars;

    private int rijTeLang = 0;

    // De constructors

    public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces, Time time) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = (numberOfFloors - 1) * numberOfRows * numberOfPlaces;
        this.numberOfOpenResvSpots = numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        this.time = time;

        entranceCarQueue = new CarQueue();
        entrancePassResvQueue = new CarQueue();

        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        turnoverTotal = 0.0;

    }

    // De methodes

    /**
     * Voert de simulator uit.
     */

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * Stopt de simulator
     */

    public void stop() {

    }

    /**
     * Zorgt ervoor dat de tijd vooruit gaat.
     */

    public void tick() {
        time.tick();
    	//advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    /**
     * Zorgt ervoor dat er een wachtrij voor de ingang is.
     */

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entranceCarQueue);
        carsEntering(entrancePassResvQueue);
    }

    /**
     * Zorgt dat de auto's weer uit de garage gaan en betalen.
     */

    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    /**
     *  Update de view van de simulatie.
     */

    private void updateViews(){
    	tick(turnoverTotal);
        // Update the car park view.
        notifyViews();
    }

    /**
     * Bepaalt hoeveel auto's er binnenkomen in de garage.
     */

    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals, voorstellingArrivals, koopAvondArrivals);
        addArrivingCars(numberOfCars, AD_HOC);

    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals, voorstellingPassArrivals, koopAvondPassArrivals);
        addArrivingCars(numberOfCars, PASS);

        numberOfCars=getNumberOfCars(weekDayResvArrivals, weekendResvArrivals, voorstellingResvArrivals, koopAvondResvArrivals);
        addArrivingCars(numberOfCars, RESV);
    }

    /**
     * Bepaalt hoeveel auto's er in de garage willen.
     * @param queue Lengte van de wachtrij
     */

    private void carsEntering(CarQueue queue){
        Random rand = new Random();
        int random = rand.nextInt(50);
        int i=0;

        // Verwijderen van auto wanneer de rij te lang is
        if (entranceCarQueue.carsInQueue() > 5 && entranceCarQueue.carsInQueue() < 8 && random > 25 || entranceCarQueue.carsInQueue() >= 8 && entranceCarQueue.carsInQueue() >= 8 && entranceCarQueue.carsInQueue() <= 10 && random > 14 || entranceCarQueue.carsInQueue() > 10){
            rijTeLang++;
            queue.removeCar();
        }

        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && (!queue.frontCar().getHasReservation() && getNumberOfOpenSpots()>0) && i<enterSpeed || queue.carsInQueue()>0 && (queue.frontCar().getHasReservation() &&getNumberOfOpenResvSpots()>0) && i<enterSpeed) {
            Car car = queue.removeCar();
            if (!car.getHasReservation()) {
                Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
                i++;
            }else{
                Location freeLocation = getFirstResvLocation();
                setCarAt(freeLocation, car);
                i++;
            }
        }
    }

    /**
     * Bepaalt hoeveel autos klaar zijn om weg te gaan.
     */

    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    /**
     * Zorgt ervoor dat de auto's betalen
     */

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }

    /**
     * Bepaalt hoeveel auto's er weg willen
     */

    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
    	    totalCars--;
            i++;
    	}	
    }

    /**
     * Zorgt ervoor dat er een verschil is tussen dag, weekend, voorstellingen en koopavonden.
     * @param weekDay true of false
     * @param weekend true of false
     * @param voorstelling true of false
     * @param koopavond true of false
     * @return De waardes voor hoeveel autos er binnenkomen
     */

    private int getNumberOfCars(int weekDay, int weekend, int voorstelling, int koopavond){
        int averageNumberOfCarsPerHour;
        Random random = new Random();
        // Get the average number of cars that arrive per hour.
        if (time.isWeekend()){
            averageNumberOfCarsPerHour = weekend;
            double standardDeviation = averageNumberOfCarsPerHour * 0.3;
            double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
            return (int)Math.round(numberOfCarsPerHour / 60);
        } else if (time.isKoopAvond()){
            averageNumberOfCarsPerHour = koopavond;
            double standardDeviation = averageNumberOfCarsPerHour * 0.3;
            double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
            return (int)Math.round(numberOfCarsPerHour / 60);
        } else if (time.isVoorstelling()){
            averageNumberOfCarsPerHour = voorstelling;
            double standardDeviation = averageNumberOfCarsPerHour * 0.3;
            double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
            return (int)Math.round(numberOfCarsPerHour / 60);
        }else{
            averageNumberOfCarsPerHour = weekDay;
            double standardDeviation = averageNumberOfCarsPerHour * 0.3;
            double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
            return (int)Math.round(numberOfCarsPerHour / 60);
        }

    }

    /**
     * Voegt auto's toe aan de achterkant van de wachtrij.
     * @param numberOfCars Hoeveel auto's er wachten
     * @param type Het type auto
     */

    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            	noPassholder++;
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassResvQueue.addCar(new ParkingPassCar());
            	passHolder++;
            }
            break;
            case RESV:
                for (int i = 0; i < numberOfCars; i++){
                    entrancePassResvQueue.addCar(new ReservationCar());
                    reservationHolder++;
                }

    	}
    }

    /**
     * Zorgt ervoor dat de auto zijn plaats verlaat.
     * @param car Welke auto weg gaat
     */

    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    /**
     * Geeft terug hoeveel verdiepingen de garage heeft.
     * @return numberOfFloors hoeveel verdiepingen de parkeergarage heeft
     */

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Geeft terug hoeveel rijen de garage heeft.
     * @return numberOfRows hoeveel rijen de parkeergarage heeft
     */

    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Geeft terug hoeveel plekken de garage heeft.
     * @return numberOfPlaces hoeveel plekken de parkeergarage heeft
     */

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Geeft terug hoeveel vrije plekken de garage heeft.
     * @return numberOfOpenSpots Hoeveel vrije plekken er zijn
     */

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    /**
     * Geeft terug hoeveel vrije plekken de garage heeft met betrekking tot de gereserveerde plekken.
     * @return numberOfOpenResvSpots Hoeveel open gereserveerde plekker er zijn
     */

    public int getNumberOfOpenResvSpots(){
        return numberOfOpenResvSpots;
    }

    /**
     * Geeft terug hoeveel vrije plekken de garage totaal heeft.
     * @return totalNumberOfOpenSpots Het totaal van vrije plekken
     */

    public int getTotalNumberOfOpenSpots(){
        int totalNumberOfOpenSpots = (getNumberOfOpenSpots() + getNumberOfOpenResvSpots());
        return totalNumberOfOpenSpots;
    }

    /**
     * Checked wat voor auto op een bepaalde plek staat.
     * @param location Locatie van de auto
     * @return cars Geeft het soort auto terug.
     */

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Plaatst een auto op een locatie
     * @param location Locatie van de parkeerplek
     * @param car Soort auto
     * @return boolean true of false
     */

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            if (car.getHasReservation()){
                numberOfOpenResvSpots--;
            }
            if (!car.getHasReservation()) {
                numberOfOpenSpots--;
            }
            return true;
        }
        return false;
    }

    /**
     * Verwijderd een auto van een locatie.
     * @param location Plek van de auto
     * @return car
     */

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        if (car.getHasReservation()){
            numberOfOpenResvSpots++;
        }
        if (!car.getHasReservation()){
            numberOfOpenSpots++;
        }
        return car;
    }

    /**
     * Geeft de eerste vrije parkeerplek terug.
     * @return location De eerste vrije parkeerplek
     */

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Geeft de eerste vrije gereserveerde parkeerplek terug.
     * @return location De eerste vrije gereserveerde parkeerplek
     */

    public Location getFirstResvLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Geeft de eerste auto terug die vertrekt.
     * @return car De auto die vertrekt
     */

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * ?????????????????
     * @param turnoverTotal
     */

    public void tick(double turnoverTotal) {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }

        String text = String.format("%.2f", (double)turnoverTotal);
        //this.setTitle(text);
    }

    /**
     * Controleerd of een locatie valide is.
     * @param location Plaats van de parkeerplek
     * @return int Verdieping
     */

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return floor >= 0 && floor < numberOfFloors && row >= 0 && row <= numberOfRows && place >= 0 && place <= numberOfPlaces;
    }

    /**
     * Geeft de lengte van de rij voor de parkeergarage terug.
     * @return int Lengte van de rij
     */

    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    /**
     * Geeft de lengte van de rij voor de parkeergarage terug van auto's met een abbonement.
     * @return int Lengte van de rij
     */

    public CarQueue getEntrancePassResvQueue(){
        return entrancePassResvQueue;
    }

    /**
     * Geeft de lengte van de rij voor de uitgang terug.
     * @return int Lengte van de rij
     */

    public CarQueue getExitCarQueue(){
        return exitCarQueue;
    }

    /**
     * Geeft het totaal van de auto's zonder abbonement terug.
     * @return int Totaal aantal auto's zonder abbonement
     */

    public int getTotalNoPassholder(){
        return noPassholder;
    }

    /**
     * Geeft het totaal van de auto's met abbonement terug.
     * @return int Totaal aantal auto's met abbonement
     */

    public int getTotalPassHolder(){
        return passHolder;
    }

    /**
     * Geeft het totaal van auto's die gereserveerd hebben.
     * @return int Totaal van auto's die gereserveerd hebben
     */

    public int getReservationHolder(){
        return reservationHolder;
    }

    /**
     * Geeft totaal van alle auto's terug.
     * @return int Totaal aantal auto's
     */

    public int getTotalCars(){
        return (noPassholder + passHolder + reservationHolder) + totalCars;
    }

    /**
     * Geeft terug hoeveel normale auto's weg zijn gegaan omdat de rij te lang was.
     * @return int Hoeveel auto's weg zijn gegaan
     */

    public int getRijTeLang(){
        return rijTeLang;
    }

}
