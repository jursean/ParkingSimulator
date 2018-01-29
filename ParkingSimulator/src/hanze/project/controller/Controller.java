package hanze.project.controller;

import hanze.project.logic.AbstractModel;
import hanze.project.logic.Model;
import hanze.project.main.ParkingSimulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")

public class Controller extends AbstractController implements ActionListener {

    // FIELDS

    private JButton minEen;
    private JButton plusEen;
    private JButton plusEenStep;
    private JButton startCounter;
    private JButton stopCounter;
    private JButton startSimulator;
    private JButton pauseSimulator;
    private JSlider setSpeed;

    // CONSTRUCTORS

    public Controller(AbstractModel model) {
        super(model);

        minEen = new JButton("-1");
        minEen.addActionListener(this);
        plusEen = new JButton("+1");
        plusEen.addActionListener(this);
        plusEenStep = new JButton("+1 Step");
        plusEenStep.addActionListener(this);
        startCounter = new JButton("Start");
        startCounter.addActionListener(this);
        stopCounter = new JButton("Stop");
        stopCounter.addActionListener(this);
        startSimulator = new JButton("Start");
        startSimulator.addActionListener(this);
        pauseSimulator = new JButton("Pause");
        pauseSimulator.addActionListener(this);
        setSpeed = new JSlider(JSlider.VERTICAL, 0,100,1);

        //setSpeed.setOpaque(true);
        setSpeed.setMajorTickSpacing(10);
        setSpeed.setMinorTickSpacing(2);
        setSpeed.setPaintLabels(true);
        setSpeed.setPaintTicks(true);
        //setSpeed.setInverted(true);

        add(plusEenStep);
        add(startSimulator);
        add(pauseSimulator);
        add(minEen);
        add(plusEen);
        add(startCounter);
        add(stopCounter);
        add(setSpeed);

        plusEenStep.setBounds(570, 370, 100, 30);
        startSimulator.setBounds(340, 370, 70, 30);
        pauseSimulator.setBounds(420, 370, 70, 30);
        minEen.setBounds(410, 678, 70, 30);
        plusEen.setBounds(490, 678, 70, 30);
        startCounter.setBounds(610, 678, 70, 30);
        stopCounter.setBounds(690, 678, 70, 30);
        setSpeed.setBounds(1110,5,70,360);

        this.setLayout(null);

        setSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                ParkingSimulator.simulationSpeed = 1000 - (source.getValue() * 10);
            }
        });
    }

    // METHODS
    private void startPressed() {
        ParkingSimulator.running = true;
    }

    private void pausePressed() {
        ParkingSimulator.running = false;
    }

    private void plusEen() {
        Model model = (Model) super.model;
        int teller = 0;
        for (teller = 0; teller < 1; teller++) {
            model.tick();
        }
    }

    private void setSpeed() {

    }

    // HIER KAN MEN ACTIES TOEWIJZEN AAN DE KNOPPEN IN DE GUI
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plusEen) {
            model.setAantal(model.getAantal()+1);
        }

        if (e.getSource() == minEen) {
            model.setAantal(model.getAantal()-1);
        }

        if (e.getSource() == plusEenStep) {
            this.plusEen();
        }

        if (e.getSource() == startCounter) {
            //model.start();
        }

        if (e.getSource() == stopCounter) {
            //model.stop();
        }

        if (e.getSource() == startSimulator) {
            this.startPressed();
        }

        if (e.getSource() == pauseSimulator) {
            this.pausePressed();
        }

    }
}
