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

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 360, 320);
    }

    public StatView(Model simulator) {
        super(simulator);

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

        statsLabel.setBounds(20, 0, 100, 30); // Default = x: 3 y: 0 width: 100 height: 30
        aantalNietResvVrij.setBounds(20, 55, 300, 30);
        aantalResvVrij.setBounds(20, 75, 300, 30);
        aantalRijIn.setBounds(20, 95, 500, 30);
        aantalRijInPassResv.setBounds(20, 115, 500, 30);
        aantalRijUit.setBounds(20, 135, 250, 30);
        aantalRijTeLang.setBounds(20, 155, 250, 30);
        aantalNormaal.setBounds(20, 175, 250, 30);
        aantalPassHouder.setBounds(20, 195, 250, 30);
        aantalResvHouder.setBounds(20, 215, 250, 30);
        verwachteInkomen.setBounds(20,235,300,30);
        inkomsten.setBounds(20,255,260,30);
        totaalVerdient.setBounds(20,275,260,30);

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

