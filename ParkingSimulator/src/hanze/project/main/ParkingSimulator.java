package hanze.project.main;

import hanze.project.controller.AbstractController;
import hanze.project.controller.Controller;
import hanze.project.logic.Model;
import hanze.project.logic.Time;
import hanze.project.logic.AbstractModel;
import hanze.project.view.*;

import javax.swing.*;
import java.awt.*;

// CLASS

public class ParkingSimulator {

    // FIELDS

    private static JFrame screen;
    private static Model simulator;
    private static Time time;
    private int width;
    private int height;
    private AbstractModel model;
    private AbstractView carParkView;
    private AbstractView statView;
    private AbstractView timeView;
    private AbstractView countView;
    private AbstractView pieView;
    private AbstractController controller;

    public static int simulationSpeed = 1000;
    public static boolean running;

    //  CONSTRUCTORS

    public ParkingSimulator(){ // <---- WORDT AANGEROEPEN IN DE RUNNER

        // TITEL VAN DE GUI
        screen = new JFrame("Parkeer Simulator");

        // HET TOEVOEGEN VAN EEN NIEUWE INSTANCE
        time = new Time();
        simulator = new Model(3, 6, 30, time);
        model = new AbstractModel();
        countView = new CountView(model);
        pieView = new PieView(model);

        // PAS HIER DE RESOLUTIE AAN VAN DE GUI
        this.width = 1200;
        this.height = 750;

        // HET INITIALISEREN VAN DE VIEWS
        this.statView = new StatView(simulator);
        this.carParkView = new CarParkView(simulator);
        this.timeView = new TimeView(time);
        this.pieView = new PieView(simulator);
        this.countView = new CountView(simulator);
        this.controller = new Controller(simulator);

        // VOED HIER DE VIEWS TOE AAN DE GUI
        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(statView);
        screen.getContentPane().add(timeView);
        screen.getContentPane().add(countView);
        screen.getContentPane().add(pieView);
        screen.getContentPane().add(controller);

        // PAS HIER DE MARGES EN AFMETINGEN AAN VAN DE GUI
        carParkView.setBounds(410,5,770,360);
        statView.setBounds(5,5,400,360);
        timeView.setBounds(5,370,400,300);
        countView.setBounds(410, 470, 200, 200);
        pieView.setBounds(630, 470, 200, 200);
        controller.setBounds(0, 0, 1200, 750);

        // PAS HIER DE BUITENRAND AAN VAN DE VIEWS IN DE GUI
        carParkView.setBorder(BorderFactory.createLineBorder(Color.black));
        statView.setBorder(BorderFactory.createLineBorder(Color.black));
        timeView.setBorder(BorderFactory.createLineBorder(Color.black));
        pieView.setBorder(BorderFactory.createLineBorder(Color.black));
        countView.setBorder(BorderFactory.createLineBorder(Color.black));

        // GUI INSTELLINGEN
        screen.setSize(width, height);
        screen.setResizable(false);
        screen.setLayout(null);

        // AFSLUIT INSTELLINGEN
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);

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

        // HET ZICHTBAAR OF ONZICHTBAAR MAKEN VAN DE GUI
    }

}