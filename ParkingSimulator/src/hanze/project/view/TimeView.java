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
    private JLabel weekend;
    private JLabel voorstelling;
    private JLabel koopavond;
    private JLabel avond;
    private JLabel nacht;

    // De constructors

    public TimeView(Time timeController){
        super(timeController);
        Time time = (Time) super.model;

        //Label maken
        this.dateLabel = new JLabel("Tijd");
        this.startTimeString = new JLabel("Start datum van de simulatie: ");
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
        dateLabel.setBounds(150,0,100,30); // Default = x: 3 y: -1 width: 100 height: 30
        startTimeString.setBounds(5,20,100,30);
        simulationTime.setBounds(5,60,100,30);
        minutes.setBounds(5,80,100,30);
        hours.setBounds(5,100,100,30);
        days.setBounds(5,120,100,30);
        weekend.setBounds(5,160,100,30);
        voorstelling.setBounds(5,180,100,30);
        koopavond.setBounds(5,200,100,30);
        avond.setBounds(5,220,100,30);
        nacht.setBounds(5,240,100,30);

        //De labels toevoegen
        add(dateLabel);
        add(startTimeString);
        add(simulationTime);
        add(minutes);
        add(hours);
        add(days);
        add(weekend);
        add(voorstelling);
        add(koopavond);
        add(avond);
        add(nacht);
    }

    // De methodes

    /**
     * Zorgt ervoor dat de tijd geupdate word
     */

    public void updateView(){
        Time time = (Time) super.model;

        startTimeString.setText(time.getStartTime());

        simulationTime.setText(time.getCurrentTime());
        minutes.setText(("Minuten: ")+time.getRunningMinutes());
        hours.setText(("Uren: ")+time.getRunningHours());
        days.setText(("Dagen: ")+time.getRunningDays());

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
