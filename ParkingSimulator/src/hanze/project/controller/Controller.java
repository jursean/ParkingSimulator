package hanze.project.controller;

import hanze.project.logic.AbstractModel;
import hanze.project.logic.Model;
import hanze.project.main.ParkingSimulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

@SuppressWarnings("serial")

public class Controller extends AbstractController implements ActionListener {

    // FIELDS

    private JButton plusEenStep;
    private JButton startSimulator;
    private JButton pauseSimulator;
    private JSlider setSpeed;

    // CONSTRUCTORS

    public Controller(AbstractModel model) {
        super(model);

        plusEenStep = new JButton("+1 Step");
        plusEenStep.addActionListener(this);
        startSimulator = new JButton("Start");
        startSimulator.addActionListener(this);
        pauseSimulator = new JButton("Pause");
        pauseSimulator.addActionListener(this);
        setSpeed = new JSlider(JSlider.VERTICAL, 0,100,100);
        Hashtable labels = new Hashtable();
        labels.put(100, new JLabel("Slow"));
        labels.put(0, new JLabel("Fast"));

        //setSpeed.setOpaque(true);
        setSpeed.setMajorTickSpacing(20);
        setSpeed.setMinorTickSpacing(10);
        setSpeed.setPaintLabels(true);
        setSpeed.setPaintTicks(true);
        setSpeed.setInverted(true);
        setSpeed.setLabelTable(labels);

        add(plusEenStep);
        add(startSimulator);
        add(pauseSimulator);
        add(setSpeed);

        plusEenStep.setBounds(570, 370, 100, 30);
        startSimulator.setBounds(340, 370, 70, 30);
        pauseSimulator.setBounds(420, 370, 70, 30);
        setSpeed.setBounds(1110,5,70,360);

        this.setLayout(null);

        setSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                ParkingSimulator.simulationSpeed = source.getValue() * 10;
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

    private void plusEenStep() {
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

        if (e.getSource() == plusEenStep) {
            this.plusEenStep();
        }

        if (e.getSource() == startSimulator) {
            this.startPressed();
        }

        if (e.getSource() == pauseSimulator) {
            this.pausePressed();
        }

    }
}