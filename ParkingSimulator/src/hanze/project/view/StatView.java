package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

// CLASS

public class StatView extends AbstractView {

    // Fields
    private JLabel statsLabel;
    private JLabel aantalTotaalVrij;
    private JLabel aantalNietResvVrij;
    private JLabel aantalResvVrij;
    private JLabel aantalRijIn;
    private JLabel aantalRijInPassResv;
    private JLabel aantalRijUit;
    private JLabel test;

    // CONSTRUCTORS
    public StatView(Model simulator) {
        super(simulator);
        Dimension size = new Dimension(250, 400);

        this.statsLabel = new JLabel("Statistieken");
        this.aantalTotaalVrij = new JLabel("Totaal aantal plekken vrij: ");
        this.aantalNietResvVrij = new JLabel("Totaal aantal niet-gereserveerde plekken vrij: ");
        this.aantalResvVrij = new JLabel("Totaal aantal gereserveerde plekken vrij: ");

        this.aantalRijIn = new JLabel("Aantal auto's in de rij (normaal-ingang): ");
        this.aantalRijInPassResv = new JLabel("Aantal auto's in de rij (pass/resv-ingang): ");

        this.aantalRijUit = new JLabel("Aantal auto's in de rij (uitgang): ");
        this.test = new JLabel();

        statsLabel.setBounds(5, 0, 100, 30); // Default = x: 3 y: 0 width: 100 height: 30
        aantalTotaalVrij.setBounds(5, 40, 200, 30);
        aantalNietResvVrij.setBounds(5, 60, 300, 30);
        aantalResvVrij.setBounds(5, 80, 300, 30);
        aantalRijIn.setBounds(5, 100, 500, 30);
        aantalRijUit.setBounds(5, 120, 250, 30);
        //test.setBounds(5, 120, 250, 30);
        statsLabel.setBounds(90, 0, 100, 30); // Default = x: 3 y: 0 width: 100 height: 30
        aantalTotaalVrij.setBounds(5, 20, 200, 30);
        aantalNietResvVrij.setBounds(5, 40, 300, 30);
        aantalResvVrij.setBounds(5, 60, 300, 30);
        aantalRijIn.setBounds(5, 80, 500, 30);
        aantalRijInPassResv.setBounds(5, 100, 500, 30);
        aantalRijUit.setBounds(5, 120, 250, 30);
        test.setBounds(5, 140, 250, 30);

        add(statsLabel);
        add(aantalTotaalVrij);
        add(aantalNietResvVrij);
        add(aantalResvVrij);
        add(aantalRijIn);
        add(aantalRijInPassResv);
        add(aantalRijUit);
        //add(test);
    }

        public void updateView(){

            Model simulator = (Model) super.model;

            aantalTotaalVrij.setText(("Totaal aantal plekken vrij: ")+ simulator.getTotalNumberOfOpenSpots());
            aantalNietResvVrij.setText(("Totaal aantal niet-gereserveerde plekken vrij: ")+ simulator.getNumberOfOpenSpots());
            aantalResvVrij.setText(("Totaal aantal gereserveerde plekken vrij: ")+ simulator.getNumberOfOpenResvSpots());
            aantalRijIn.setText(("Aantal auto's in de rij (normaal-ingang): ")+ simulator.getEntranceCarQueue().carsInQueue());
            aantalRijInPassResv.setText(("Aantal auto's in de rij (pass/resv-ingang): ")+ simulator.getEntrancePassResvQueue().carsInQueue());
            aantalRijUit.setText(("Aantal auto's in de rij (uitgang): ")+ simulator.getExitCarQueue().carsInQueue());
            test.setText(("")+simulator.getNumberOfOpenResvSpots());

            setVisible(true);
            super.updateView();
        }
   }

