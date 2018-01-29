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
	private AbstractView legendaView;
    private AbstractView timeView;
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

        // PAS HIER DE RESOLUTIE AAN VAN DE GUI
        this.width = 1200;
        this.height = 750;

        // HET INITIALISEREN VAN DE VIEWS
        this.statView = new StatView(simulator);
		this.legendaView = new LegendaView(simulator);
        this.carParkView = new CarParkView(simulator);
        this.timeView = new TimeView(time);
        this.pieView = new PieView(simulator);
        this.controller = new Controller(simulator);

        // VOED HIER DE VIEWS TOE AAN DE GUI
        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(statView);
		screen.getContentPane().add(legendaView);
        screen.getContentPane().add(timeView);
        screen.getContentPane().add(pieView);
        screen.getContentPane().add(controller);

        // PAS HIER DE MARGES EN AFMETINGEN AAN VAN DE GUI
        carParkView.setBounds(340,5,770,360);
        statView.setBounds(5,5,330,360);
		legendaView.setBounds(370,470,300,200);
        timeView.setBounds(5,370,330,300);
        pieView.setBounds(810, 370, 300, 300);
        controller.setBounds(0, 0, 1200, 750);

        // PAS HIER DE BUITENRAND AAN VAN DE VIEWS IN DE GUI
        carParkView.setBorder(BorderFactory.createLineBorder(Color.black));
        statView.setBorder(BorderFactory.createLineBorder(Color.black));
		legendaView.setBorder(BorderFactory.createLineBorder(Color.black));
        timeView.setBorder(BorderFactory.createLineBorder(Color.black));
        pieView.setBorder(BorderFactory.createLineBorder(Color.black));

        // GUI INSTELLINGEN
        screen.setSize(width, height);
        screen.setResizable(false);
        screen.setLayout(null);
        screen.setVisible(true);

        // AFSLUIT INSTELLINGEN
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

        // HET ZICHTBAAR OF ONZICHTBAAR MAKEN VAN DE GUI
    }

}