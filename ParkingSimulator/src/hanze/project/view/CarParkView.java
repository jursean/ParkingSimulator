package hanze.project.view;

import hanze.project.logic.Model;
import hanze.project.logic.Car;
import hanze.project.logic.Location;

import javax.swing.*;
import java.awt.*;

// CLASS

public class CarParkView extends AbstractView {
    // title, cars and pass holder labels
    private JLabel titelTotalCar;
    private JLabel totalCar;
    // image of the car park
    private Image carParkImage;
    private Dimension size;

    // CONSTRUCTORS

    /**
     * Constructor of CarParkView that expects a model belonging to this view
     *
     * @param simulator AbstractModel that belongs to this view
     */

    public CarParkView(Model simulator) {
        super(simulator);
        this.titelTotalCar = new JLabel("Totaal aantal auto's in garage: 0");
        this.totalCar = new JLabel(" ");
        this.size = new Dimension(920, 400); // Default = width: 680 height: 330

        // set location of the labels
        titelTotalCar.setBounds(5,5,300,20); // Default = x: 0 y: 10 width: 200 height: 20
        totalCar.setBounds(150,10,20,20);   // Default = x: 150 y: 10 width: 20 height: 20

        // add the labels to the view
        add(titelTotalCar);
        add(totalCar);
    }

    // METHODS

    /**
     * The car park view component needs to be redisplayed. Copy the
     * internal image to SCREEN.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        g.drawImage(carParkImage, 0, 0, null); // Default = x: 0 y: 0
    }

    /**
     * Called by the model that belongs to this view
     * to notify that the view should by updated
     */
    @Override
    public void updateView() {

        Model simulator = (Model) super.model;

        // total statistics
        titelTotalCar.setText(String.valueOf("Totaal aantal auto's in garage: ")+simulator.getTotalCars());

        // create a new car park image if the size has changed.
        carParkImage = createImage(size.width, size.height);

        Graphics graphics = carParkImage.getGraphics();


        for (int floor = 0; floor < simulator.getNumberOfFloors(); floor++) {
            for (int row = 0; row < simulator.getNumberOfRows(); row++) {
                for (int place = 0; place < simulator.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = simulator.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }

        for(int floor = 2; floor < simulator.getNumberOfFloors(); floor++) {
            for(int row = 0; row < simulator.getNumberOfRows(); row++) {
                for(int place = 0; place < simulator.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = simulator.getCarAt(location);
                    Color color = car == null ? Color.green : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();

        setVisible(true);
        super.updateView();
    }

    /**
     * Draw a single parking spot for a car
     *
     * @param graphics Graphics object to draw up on
     * @param location Location to draw
     * @param color Color object to give to the drawn place
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (2 + // Default = 260 + 1
                        (int) Math.floor(location.getRow() * 0.5)) * 60 + // Default = 0.5 * 60
                        (location.getRow() % 2) * 20) -76, // Default = 2 * 20 -59
                location.getPlace() * 10 + 40, // Default = width: 10 + 30
                20 - 1, // Default = width: 20 - 1
                10 - 1); // Default = width: 10 - 1 TODO use dynamic size or constants
    }

}

