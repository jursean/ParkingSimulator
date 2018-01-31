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
        graphics.fillRect(0, 0, 330, 180);
    }

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

        //De labels toevoegen
        add(dateLabel);
        add(minutes);
        add(hours);
        add(days);
        add(simulationTime);
        add(startTimeString);

    }

    // De methodes

    /**
     * Zorgt ervoor dat de tijd geupdate word
     */

    public void updateView(){
        Time time = (Time) super.model;

        simulationTime.setText(time.getCurrentTime());
        startTimeString.setText(time.getStartTime());

        minutes.setText(("Minuten: ")+time.getRunningMinutes());
        hours.setText(("Uren: ")+time.getRunningHours());
        days.setText(("Dagen: ")+time.getRunningDays());
        simulationTime.setText(("Tijd (Simulator): ")+time.getCurrentTime());
        startTimeString.setText(("Tijd (Huidig): ")+time.getStartTime());
        setVisible(true);
        super.updateView();
    }

}
