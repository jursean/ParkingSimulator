package hanze.project.view;

import hanze.project.logic.Model;
import hanze.project.logic.Car;
import hanze.project.logic.Location;

import javax.swing.*;
import java.awt.*;

/**
 * Class CarParkView
 * Zorgt ervoor dat de parkeerplaats op het beeld komt.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class CarParkView extends AbstractView {
    private JLabel titelTotalCar;
    private JLabel totalCar;
    private Image carParkImage;
    private Dimension size;

    // De constructors

    public CarParkView(Model simulator) {
        super(simulator);
        this.titelTotalCar = new JLabel("Totaal aantal auto's in garage: 0");
        this.totalCar = new JLabel(" ");
        this.size = new Dimension(920, 400); // Default = width: 680 height: 330

        titelTotalCar.setBounds(5,5,300,20); // Default = x: 0 y: 10 width: 200 height: 20
        totalCar.setBounds(150,10,20,20);   // Default = x: 150 y: 10 width: 20 height: 20

        add(titelTotalCar);
        add(totalCar);
    }

    // De methodes

    @Override

    /**
     * Plaatst de parkeergarage op het beeld.
     */

    public void paintComponent(Graphics graphics) {
        if (carParkImage == null) {
            return;
        }

        graphics.drawImage(carParkImage, 0, 0, null); // Default = x: 0 y: 0
    }

    @Override
    /**
     * zorgt ervoor dat de simulator geupdate word.
     */
    public void updateView() {

        Model simulator = (Model) super.model;

        titelTotalCar.setText(String.valueOf("Totaal aantal auto's in garage: ")+simulator.getTotalCars());

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
     * Tekent de parkeerplekken in de simulator.
     *
     * @param graphics Zorgt voor de graphics van de parkeerplekken
     * @param location Locatie waar getekend moet worden
     * @param color Zorgt voor de kleur
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

