package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Class StatView
 * Zorgt ervoor dat de statistieken in beeld komen.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class StatView extends AbstractView {

    // De velden

    private JLabel statsLabel;
    private JLabel aantalNietResvVrij;
    private JLabel aantalResvVrij;
    private JLabel aantalRijIn;
    private JLabel aantalRijInPassResv;
    private JLabel aantalRijUit;
    private JLabel aantalRijTeLang;
    private JLabel inkomsten;
    private JLabel verwachteInkomen;
    private JLabel aantalNormaal;
    private JLabel aantalPassHouder;
    private JLabel aantalResvHouder;
    private JLabel totaalVerdient;

    // De constructors

    public StatView(Model simulator) {
        super(simulator);
        Dimension size = new Dimension(250, 400);

        this.statsLabel = new JLabel("Statistieken");
        this.aantalNietResvVrij = new JLabel("Totaal aantal niet-gereserveerde plekken vrij: ");
        this.aantalResvVrij = new JLabel("Totaal aantal gereserveerde plekken vrij: ");

        this.aantalRijIn = new JLabel("Aantal auto's in de rij (normaal-ingang): ");
        this.aantalRijInPassResv = new JLabel("Aantal auto's in de rij (pass/resv-ingang): ");
        this.aantalRijUit = new JLabel("Aantal auto's in de rij (uitgang): ");
        this.aantalRijTeLang = new JLabel("Aantal auto's doorgereden: ");
        this.inkomsten = new JLabel("Inkomsten van vandaag: €0");
        this.verwachteInkomen = new JLabel("Verwachte inkomsten: €0");
        this.aantalNormaal = new JLabel("Aantal normale parkeerhouders: ");
        this.aantalPassHouder = new JLabel("Aantal abonnement parkeerhouders: ");
        this.aantalResvHouder = new JLabel("Aantal reservering parkeerhouders: ");
        this.totaalVerdient = new JLabel("Totale inkomsten: €0");

        //this.aantalTotaalVrijLabel = new JLabel("");

        statsLabel.setBounds(90, 0, 100, 30); // Default = x: 3 y: 0 width: 100 height: 30
        aantalNietResvVrij.setBounds(5, 40, 300, 30);
        aantalResvVrij.setBounds(5, 60, 300, 30);
        aantalRijIn.setBounds(5, 80, 500, 30);
        aantalRijInPassResv.setBounds(5, 100, 500, 30);
        aantalRijUit.setBounds(5, 120, 250, 30);
        aantalRijTeLang.setBounds(5, 140, 250, 30);
        aantalNormaal.setBounds(5, 160, 250, 30);
        aantalPassHouder.setBounds(5, 180, 250, 30);
        aantalResvHouder.setBounds(5, 200, 250, 30);
        verwachteInkomen.setBounds(5,220,300,30);
        inkomsten.setBounds(5,240,240,30);
        totaalVerdient.setBounds(5,260,260,30);


        add(statsLabel);
        add(aantalNietResvVrij);
        add(aantalResvVrij);
        add(aantalRijIn);
        add(aantalRijInPassResv);
        add(aantalRijUit);
        add(aantalRijTeLang);
        add(inkomsten);
        add(verwachteInkomen);
        add(aantalNormaal);
        add(aantalPassHouder);
        add(aantalResvHouder);
        add(totaalVerdient);
    }

    // De methodes

    /**
     * Update de gegevens op het scherm
     */

    public void updateView(){

        Model simulator = (Model) super.model;

        aantalNietResvVrij.setText(("Totaal aantal niet-gereserveerde plekken vrij: ")+ simulator.getNumberOfOpenSpots());
        aantalResvVrij.setText(("Totaal aantal gereserveerde plekken vrij: ")+ simulator.getNumberOfOpenResvSpots());
        aantalRijIn.setText(("Aantal auto's in de rij (normaal-ingang): ")+ simulator.getEntranceCarQueue().carsInQueue());
        aantalRijInPassResv.setText(("Aantal auto's in de rij (pass/resv-ingang): ")+ simulator.getEntrancePassResvQueue().carsInQueue());
        aantalRijUit.setText(("Aantal auto's in de rij (uitgang): ")+ simulator.getExitCarQueue().carsInQueue());
        aantalRijTeLang.setText(("Aantal auto's doorgereden: ")+ simulator.getRijTeLang());
        inkomsten.setText(("Inkomsten van vandaag: €") + Math.round(simulator.getInkomen()));
        verwachteInkomen.setText(("Verwachte inkomsten: €") + Math.round(simulator.getVerwachteinkomen()));
        totaalVerdient.setText(("Totale inkomsten: €") + Math.round(simulator.getTotaalInkomen()));
        aantalNormaal.setText(("Aantal normale parkeerhouders: ")+simulator.getTotalNoPassholder());
        aantalPassHouder.setText(("Aantal abonnement parkeerhouders: ")+simulator.getTotalPassHolder());
        aantalResvHouder.setText(("Aantal reservering parkeerhouders: ")+simulator.getTotalReservationHolder());

        setVisible(true);
        super.updateView();
    }
}

