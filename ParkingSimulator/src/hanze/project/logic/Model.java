package hanze.project.logic;

import java.util.Random;

// CLASS

public class Model extends AbstractModel {

    // FIELDS

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

    // CONSTRUCTORS

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

    // METHODS

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    public void stop() {

    }

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

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entranceCarQueue);
        carsEntering(entrancePassResvQueue);
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void updateViews(){
    	tick(turnoverTotal);
        // Update the car park view.
        notifyViews();
    }

    private void maakReservering(){

    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals, voorstellingArrivals, koopAvondArrivals);
        addArrivingCars(numberOfCars, AD_HOC);

    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals, voorstellingPassArrivals, koopAvondPassArrivals);
        addArrivingCars(numberOfCars, PASS);

        numberOfCars=getNumberOfCars(weekDayResvArrivals, weekendResvArrivals, voorstellingResvArrivals, koopAvondResvArrivals);
        addArrivingCars(numberOfCars, RESV);
    }

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
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
    	    totalCars--;
            i++;
    	}	
    }
    
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
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public int getNumberOfOpenResvSpots(){
        return numberOfOpenResvSpots;
    }

    public int getTotalNumberOfOpenSpots(){
        int totalNumberOfOpenSpots = (getNumberOfOpenSpots() + getNumberOfOpenResvSpots());
        return totalNumberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

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

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return floor >= 0 && floor < numberOfFloors && row >= 0 && row <= numberOfRows && place >= 0 && place <= numberOfPlaces;
    }

    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    public CarQueue getEntrancePassResvQueue(){
        return entrancePassResvQueue;
    }

    public CarQueue getExitCarQueue(){
        return exitCarQueue;
    }

    public int getTotalNoPassholder(){
        return noPassholder;
    }

    public int getTotalPassHolder(){
        return passHolder;
    }

    public int getReservationHolder(){
        return reservationHolder;
    }

    public int getTotalCars(){
        return (noPassholder + passHolder + reservationHolder) + totalCars;
    }

    public int getRijTeLang(){
        return rijTeLang;
    }

}
