package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

public class LegendView extends AbstractView {

    //Initialiseer de velden voor de beschrijving van de kleuren
    private JLabel legendaKopje;
    private JLabel wit;
    private JLabel blauw;
    private JLabel rood;
    private JLabel groen;
    private JLabel grijs;


    public LegendView(Model simulator) {
        super(simulator);
        Dimension size = new Dimension(250, 400);

        this.legendaKopje = new JLabel("Legenda");
        this.wit = new JLabel("Wit = Vrije parkeerplek");
        this.blauw = new JLabel("Blauw = Parkeerder met abbonement");
        this.rood = new JLabel("Rood = Parkeerder zonder abbonement");
        this.groen = new JLabel("Groen = Gereserveerde parkeerplek");
        this.grijs = new JLabel("Grijs = Bezette gereserveerde parkeerplek");

        legendaKopje.setBounds(90, 0, 100, 30);
        wit.setBounds(5, 20, 400, 30);
        blauw.setBounds(5, 40, 400, 30);
        rood.setBounds(5, 60, 400, 30);
        groen.setBounds(5, 80, 400, 30);
        grijs.setBounds(5, 100, 400, 30);

        wit.setForeground(Color.black);
        blauw.setForeground(Color.blue);
        rood.setForeground(Color.red);
        groen.setForeground(Color.green);

        add(legendaKopje);
        add(wit);
        add(groen);
        add(blauw);
        add(rood);
        add(grijs);
    }
}
