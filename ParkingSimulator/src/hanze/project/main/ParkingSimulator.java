package hanze.project.main;

import hanze.project.controller.AbstractController;
import hanze.project.controller.Controller;
import hanze.project.logic.Model;
import hanze.project.logic.Time;
import hanze.project.logic.AbstractModel;
import hanze.project.view.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class ParkingSimulator
 * Deze klasse zorgt ervoor dat alle vensters en views in beeld komen te staan.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class ParkingSimulator {

    // De velden

    private static JFrame screen;
    private static Model simulator;
    private static Time time;
    private int width;
    private int height;
    private AbstractModel model;
    private AbstractView carParkView;
    private AbstractView statView;
	private AbstractView legendView;
    private AbstractView timeView;
    private AbstractView tabView;
    private AbstractView tableView;
    private AbstractController controller;

    public static int simulationSpeed = 1000;
    public static boolean running;

    //  De constructors

    public ParkingSimulator(){ // <---- WORDT AANGEROEPEN IN DE RUNNER

        screen = new JFrame("Parkeer Simulator");

        time = new Time();
        simulator = new Model(3, 6, 30, time);
        model = new AbstractModel();

        this.width = 1200;
        this.height = 750;

        this.statView = new StatView(simulator);
		this.legendView = new LegendView(simulator);
        this.carParkView = new CarParkView(simulator);
        this.timeView = new TimeView(time);
        this.tabView = new TabView(simulator);
        this.tableView = new TableView(simulator);
        this.controller = new Controller(simulator);

        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(statView);
		screen.getContentPane().add(legendView);
        screen.getContentPane().add(timeView);
        screen.getContentPane().add(tabView);
        screen.getContentPane().add(tableView);
        screen.getContentPane().add(controller);

        carParkView.setBounds(342,26,760,359);
        statView.setBounds(342, 390, 360, 315);
        timeView.setBounds(7,525,330,180);
        legendView.setBounds(7,390,330,130);
        tabView.setBounds(7,5,330,380);
        tableView.setBounds(707,390,395,315);
        controller.setBounds(2, 0, 1200, 750);

        carParkView.setBorder(BorderFactory.createLineBorder(Color.black));
        statView.setBorder(BorderFactory.createLineBorder(Color.black));
		legendView.setBorder(BorderFactory.createLineBorder(Color.black));
        timeView.setBorder(BorderFactory.createLineBorder(Color.black));
        tableView.setBorder(BorderFactory.createLineBorder(Color.black));

        screen.setSize(width, height);
        screen.setResizable(false);
        screen.setLayout(null);
        screen.setVisible(true);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            if (running) {
                simulator.tick();
            }
            try {
                Thread.sleep(simulationSpeed);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
