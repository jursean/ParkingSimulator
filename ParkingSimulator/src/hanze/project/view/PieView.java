package hanze.project.view;

import hanze.project.logic.Model;

import java.awt.*;

/**
 * Class PieView
 * Zorgt ervoor dat de taart diagram in beeld komt.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

@SuppressWarnings("serial")
public class PieView extends AbstractView {

	// De velden

	int numberOfScores = 5;
	int Xleft = 100;
	int Xright = 300;
	int Ytop = 100;
	int Ybottom = 250;

	int totalX , totalY;
	int[ ] scores;
	char graphChoice;

	// De constructors

	public PieView(Model simulator){

		super(simulator);

		int i;
		scores = new int[numberOfScores];
		for (i=0; i<scores.length; i++)
			scores[i] = 0;
		totalX = Xright - Xleft + 1;
		totalY = Ybottom - Ytop + 1;
		graphChoice = 'P';
	}

	// De methodes

	@Override

	/**
	 * Zorgt ervoor dat de graphics van de diagram geladen worden.
	 */

	public void paintComponent (Graphics graphics)
	{
		getInputData();
		drawPieGraph(graphics);
	}

	/**
	 * Haalt de informatie van het scherm.
	 */

	public void getInputData()
	{
		Model simulator = (Model) super.model;

		scores[0] = simulator.getNumberOfOpenSpots();
		scores[1] = simulator.getNumberOfOpenResvSpots();
		scores[2] = simulator.getTotalNoPassholder();
		scores[3] = simulator.getTotalPassHolder();
		scores[4] = simulator.getTotalReservationHolder();
	}

	/**
	 * Zorgt ervoor dat de taart diagram getekend word.
	 */

	public void drawPieGraph(Graphics graphics)
	{

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 330, 360);

		int i, totalUnits, centerX, centerY, radius, startAngle;
		double unitAngleSize;

		totalUnits = sum(scores);
		centerX = getSize().width / 2;
		centerY = getSize().height / 2;
		radius = centerX - centerX/3;
		centerX = radius;
		centerY = centerY - centerY/3;

		if (totalUnits == 0)
			unitAngleSize = 0;
		else
			unitAngleSize = 360.0 / totalUnits;

		startAngle = 0;

		for(i=0; i < numberOfScores; i++)
		{
			int centralAngle = (int)(unitAngleSize * scores[i]);
			graphics.setColor(intToColor(i));
			graphics.fillArc(centerX, centerY, radius, radius, startAngle, centralAngle);
			startAngle = startAngle + centralAngle;
		}

		setVisible(true);
	}

	/**
	 * vind de som van autos in de garage.
	 * @param a Haalt de gegevens op
	 * @return Totaal aantal auto's
	 */

	public int sum(int [ ] a)
	{
		int i;
		int total = 0;
		for (i=0; i<a.length; i++)
			total = total + a[i];
		return total;
	}

	/**
	 * Stelt de kleur van de taart diagram in.
	 * @param i De kleur die de diagram gaat krijgen
	 * @return color De kleur
	 */

	public Color intToColor (int i)
	{
		Color color = Color.black;
		switch (i)
		{
			case 0: {
				color = Color.WHITE;
				break;
			}
			case 1: {
				color = Color.GREEN;
				break;
			}
			case 2: {
				color = Color.RED;
				break;
			}
			case 3: {
				color = Color.BLUE;
				break;
			}
			case 4: {
				color = Color.DARK_GRAY;
				break;
			}
		}
		return color;
	}
}
