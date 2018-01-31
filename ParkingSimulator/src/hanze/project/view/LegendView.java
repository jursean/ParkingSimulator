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
    private JLabel legendaKopje;
    private JLabel wit;
    private JLabel blauw;
    private JLabel rood;
    private JLabel groen;
    private JLabel grijs;

    /**
     * Deze methode zorgt voor de gekleurde vakjes in de legenda
     * @param a
     */
    public void paintComponent(Graphics a) {
        super.paintComponent(a);

        a.setColor(Color.white);
        a.fillRect(20,52,20,8);


        a.setColor(Color.BLUE);
        a.fillRect(20,72,20,8);


        a.setColor(Color.red);
        a.fillRect(20,92,20,8);


        a.setColor(Color.GREEN);
        a.fillRect(20,112,20,8);


        a.setColor(Color.darkGray);
        a.fillRect(20,132,20,8);
    }

    public LegendView(Model simulator) {
        super(simulator);
        Dimension size = new Dimension(250, 400);

        this.legendaKopje = new JLabel("Legenda");
        this.wit = new JLabel("= Vrije parkeerplek ");
        this.blauw = new JLabel("= Parkeerder met abbonement");
        this.rood = new JLabel("= Parkeerder zonder abbonement");
        this.groen = new JLabel("= Gereserveerde parkeerplek");
        this.grijs = new JLabel("= Bezette gereserveerde parkeerplek");


        legendaKopje.setBounds(120, 0, 100, 30);
        wit.setBounds(50, 40, 800, 30);
        blauw.setBounds(50, 60, 400, 30);
        rood.setBounds(50, 80, 400, 30);
        groen.setBounds(50, 100, 400, 30);
        grijs.setBounds(50, 120, 400, 30);


        wit.setForeground(Color.black);
        blauw.setForeground(Color.black);
        rood.setForeground(Color.black);
        groen.setForeground(Color.black);

        add(legendaKopje);
        add(wit);
        add(groen);
        add(blauw);
        add(rood);
        add(grijs);
    }
}
