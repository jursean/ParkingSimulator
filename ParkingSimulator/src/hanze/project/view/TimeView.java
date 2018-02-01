package hanze.project.view;

import hanze.project.logic.Time;

import javax.swing.*;
import java.awt.*;


/**
 * Class TimeView
 * Zorgt ervoor dat de tijd in beeld komt.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class TimeView extends AbstractView{

    // De velden

    private JLabel dateLabel;
    private JLabel minutes;
    private JLabel hours;
    private JLabel days;
    private JLabel simulationTime;
    private JLabel startTimeString;

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 395, 315);
    }
    private JLabel weekend;
    private JLabel voorstelling;
    private JLabel koopavond;
    private JLabel avond;
    private JLabel nacht;

    // De constructors

    public TimeView(Time timeController){

        super(timeController);

        //Label maken
        this.dateLabel = new JLabel("Tijd");
        this.minutes = new JLabel("Minuten: ");
        this.hours = new JLabel("Uren: ");
        this.days = new JLabel("Dagen: ");
        this.simulationTime = new JLabel("Tijd (Simulator): ");
        this.startTimeString = new JLabel("Tijd (Actueel): ");

        //Instellen van de grenswaarde
        dateLabel.setBounds(20,10,100,30); // Default = x: 3 y: -1 width: 100 height: 30
        minutes.setBounds(20,40,100,30);
        hours.setBounds(20,60,100,30);
        days.setBounds(20,80,100,30);
        simulationTime.setBounds(20,120,230,30);
        startTimeString.setBounds(20,140,230,30);

        //Label maken
        this.dateLabel = new JLabel("Tijd");
        this.startTimeString = new JLabel("Tijd (Simulatie): ");
        this.simulationTime = new JLabel("Huidige datum/tijd: ");
        this.minutes = new JLabel("Minuten: 0");
        this.hours = new JLabel("Uren: 0");
        this.days = new JLabel("Dagen: 0");
        this.weekend = new JLabel("Weekend");
        this.voorstelling = new JLabel("Voorstelling");
        this.koopavond = new JLabel("Koopavond");
        this.avond = new JLabel("Avond");
        this.nacht = new JLabel("Nacht");

        //Instellen van de grenswaarde
        dateLabel.setBounds(20,5,100,30); // Default = x: 3 y: -1 width: 100 height: 30
        minutes.setBounds(20,55,100,30);
        hours.setBounds(20,75,100,30);
        days.setBounds(20,95,100,30);
        simulationTime.setBounds(20,135,230,30);
        startTimeString.setBounds(20,155,230,30);
        weekend.setBounds(20,195,100,30);
        voorstelling.setBounds(20,215,100,30);
        koopavond.setBounds(20,235,100,30);
        avond.setBounds(20,255,100,30);
        nacht.setBounds(20,275,100,30);

        //De labels toevoegen
        add(dateLabel);
        add(startTimeString);
        add(simulationTime);
        add(minutes);
        add(hours);
        add(days);
        add(simulationTime);
        add(startTimeString);

        add(weekend);
        add(voorstelling);
        add(koopavond);
        add(avond);
        add(nacht);

        weekend.setForeground(Color.red);
        voorstelling.setForeground(Color.red);
        koopavond.setForeground(Color.red);
        avond.setForeground(Color.red);
        nacht.setForeground(Color.red);

        this.setLayout(null);
    }

    // De methodes

    /**
     * Zorgt ervoor dat de tijd geupdate word
     */

    @Override
    public void updateView(){
        Time time = (Time) super.model;

        startTimeString.setText(time.getStartTime());

        simulationTime.setText(time.getCurrentTime());
        minutes.setText(("Minuten: ")+time.getRunningMinutes());
        hours.setText(("Uren: ")+time.getRunningHours());
        days.setText(("Dagen: ")+time.getRunningDays());
        simulationTime.setText(("Tijd (Simulator): ")+time.getCurrentTime());
        startTimeString.setText(("Tijd (Huidig): ")+time.getStartTime());

        if (time.isWeekend()) {
            weekend.setForeground(Color.green);
        }
        else {
            weekend.setForeground(Color.red);
        }

        if (time.isVoorstelling()){
            voorstelling.setForeground(Color.green);
        }else{
            voorstelling.setForeground(Color.red);
        }

        if (time.isKoopAvond()){
            koopavond.setForeground(Color.green);
        }else{
            koopavond.setForeground(Color.red);
        }

        if (time.isAvond()){
            avond.setForeground(Color.green);
        }else{
            avond.setForeground(Color.red);
        }

        if (time.isNacht()){
            nacht.setForeground(Color.green);
        }else{
            nacht.setForeground(Color.red);
        }

        setVisible(true);
        super.updateView();
    }

}
