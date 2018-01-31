package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Class LegendView
 * Zorgt ervoor dat de legenda in beeld komt.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class LegendView extends AbstractView {

    //Initialiseer de velden voor de beschrijving van de kleuren
    private JLabel wit;
    private JLabel blauw;
    private JLabel rood;
    private JLabel groen;
    private JLabel grijs;

    /**
     * Deze methode zorgt voor de gekleurde vakjes in de legenda
     * @param graphics
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 330, 130);

        graphics.setColor(Color.white);
        graphics.fillRect(20,20,20,10);

        graphics.setColor(Color.BLUE);
        graphics.fillRect(20,40,20,10);

        graphics.setColor(Color.red);
        graphics.fillRect(20,60,20,10);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(20,80,20,10);

        graphics.setColor(Color.darkGray);
        graphics.fillRect(20,100,20,10);
    }

    public LegendView(Model simulator) {
        super(simulator);

        this.wit = new JLabel("= Vrije parkeerplek ");
        this.blauw = new JLabel("= Parkeerder met abbonement");
        this.rood = new JLabel("= Parkeerder zonder abbonement");
        this.groen = new JLabel("= Gereserveerde parkeerplek");
        this.grijs = new JLabel("= Bezette gereserveerde parkeerplek");


        wit.setBounds(50, 18, 400, 15);
        blauw.setBounds(50, 38, 400, 15);
        rood.setBounds(50, 58, 400, 15);
        groen.setBounds(50, 78, 400, 15);
        grijs.setBounds(50, 98, 400, 15);


        wit.setForeground(Color.black);
        blauw.setForeground(Color.black);
        rood.setForeground(Color.black);
        groen.setForeground(Color.black);
        grijs.setForeground(Color.black);

        add(wit);
        add(groen);
        add(blauw);
        add(rood);
        add(grijs);
    }
}
