package hanze.project.view;

import hanze.project.logic.Time;

import javax.swing.*;
import java.awt.*;

// CLASS

public class TimeView extends AbstractView{

    // FIELDS

    private JLabel dateLabel;
    private JLabel minutes;
    private JLabel hours;
    private JLabel days;
    private JLabel simulationTime;
    private JLabel startTimeString;
    private JLabel voorstelling;
    private JLabel geenVoorstelling;
    private JLabel koopAvond;
    private JLabel geenKoopAvond;

    // CONSTRUCTORS

    public TimeView(Time timeController){
        super(timeController);
        Time time = (Time) super.model;

        //Dimension size = new Dimension(250, 400);

        //Label initialiseren
        this.dateLabel = new JLabel("Tijd");
        this.minutes = new JLabel("Minuten: ");
        this.hours = new JLabel("Uren: ");
        this.days = new JLabel("Dagen: ");
        this.simulationTime = new JLabel();
        this.startTimeString = new JLabel();
        this.voorstelling = new JLabel("Er is een Voorstelling");
        this.geenVoorstelling = new JLabel("Geen Voorstelling");
        this.koopAvond = new JLabel("Er is Koopavond");
        this.geenKoopAvond = new JLabel("Geen Koopavond");

        //Zetten van de bounds
        dateLabel.setBounds(150,0,100,30); // Default = x: 3 y: -1 width: 100 height: 30
        minutes.setBounds(5,20,100,30);
        hours.setBounds(5,40,100,30);
        days.setBounds(5,60,100,30);
        simulationTime.setBounds(5,80,100,30);
        startTimeString.setBounds(5,100,100,30);
        voorstelling.setBounds(5,120,100,30);
        geenVoorstelling.setBounds(5,120,100,30);
        koopAvond.setBounds(5,140,100,30);
        geenKoopAvond.setBounds(5,140,100,30);

        //Zetten van label kleuren
        voorstelling.setForeground(Color.green);
        geenVoorstelling.setForeground(Color.red);
        koopAvond.setForeground(Color.green);
        geenKoopAvond.setForeground(Color.red);


        //Label toevoegen
        add(dateLabel);
        add(minutes);
        add(hours);
        add(days);
        add(simulationTime);
        add(startTimeString);
        add(voorstelling);
        add(geenVoorstelling);
        add(koopAvond);
        add(geenKoopAvond);
    }

    public void updateView(){
        Time time = (Time) super.model;

        simulationTime.setText(time.getCurrentTime());
        startTimeString.setText(time.getStartTime());

        minutes.setText(("Minuten: ")+time.getRunningMinutes());
        hours.setText(("Uren: ")+time.getRunningHours());
        days.setText(("Dagen: ")+time.getRunningDays());

        if (time.isVoorstelling()) {
            voorstelling.setVisible(true);
            geenVoorstelling.setVisible(false);
        }
        else {
            voorstelling.setVisible(false);
            geenVoorstelling.setVisible(true);
        }

        if (time.isKoopAvond()){
            koopAvond.setVisible(true);
            geenKoopAvond.setVisible(false);
        }
        else {
            koopAvond.setVisible(false);
            geenKoopAvond.setVisible(true);
        }


        setVisible(true);
        super.updateView();
    }

}
