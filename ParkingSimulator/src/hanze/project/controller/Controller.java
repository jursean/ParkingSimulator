package hanze.project.controller;

import hanze.project.logic.AbstractModel;
import hanze.project.logic.Model;
import hanze.project.main.ParkingSimulator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.applet.*;
import java.net.*;

/**
 * Class Controller
 * Deze klasse maakt de knoppen in de GUI.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class Controller extends AbstractController implements ActionListener {

    // De velden

    private JButton plusEenStep;
    private JButton startSimulator;
    private JButton pauseSimulator;
    private JSlider setSpeed;

    // De constructors

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

    // De methodes

    /**
     * Deze methode checked of de start knop is ingedrukt.
     */

    private void startPressed() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/hanze/project/sound/startstop.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Er is iets fout gegaan bij het afspelen van het geluid.");
            ex.printStackTrace();
        }
        ParkingSimulator.running = true;
    }

    /**
     * Deze methode checked of de pauze knop is ingedrukt.
     */


    private void pausePressed() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("parkingsimulator/src/hanze/project/sound/startstop.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Er is iets fout gegaan bij het afspelen van het geluid.");
            ex.printStackTrace();
        }
        ParkingSimulator.running = false;
    }

    /**
     * Deze methode is voor de knop die de simulator een stap verder laat gaan.
     */

    private void plusEenStep() {
        Model model = (Model) super.model;
        int teller = 0;
        for (teller = 0; teller < 1; teller++) {
            model.tick();
        }
    }

    /**
     * Hier kan je acties toewijzen aan de knoppen in de GUI.
     * @param e De "ActionEvent"
     */

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
