package hanze.project.view;

import hanze.project.logic.Model;

import java.awt.*;

@SuppressWarnings("serial")
public class PieView extends AbstractView {

	//Establish variables for this program
	int numberOfScores = 5;
	int Xleft = 100;
	int Xright = 300;
	int Ytop = 100;
	int Ybottom = 250;

	int totalX , totalY;
	int[ ] scores;
	char graphChoice;

	//Constructor to establish the initial values in the program
	public PieView (Model simulator)
	{
		super(simulator);

		int i;
		scores = new int[numberOfScores];
		for (i=0; i<scores.length; i++)
			scores[i] = 0;
		totalX = Xright - Xleft + 1;
		totalY = Ybottom - Ytop + 1;
		graphChoice = 'P';
	}

	@Override
	public void paintComponent (Graphics graphics)
	{
		getInputData();
		drawPieGraph(graphics);
	}

	//Get input from the screen

	public void getInputData()
	{
		Model simulator = (Model) super.model;

		scores[0] = simulator.getNumberOfOpenSpots();
		scores[1] = simulator.getNumberOfOpenResvSpots();
		scores[2] = simulator.getTotalNoPassholder();
		scores[3] = simulator.getTotalPassHolder();
		scores[4] = simulator.getReservationHolder();
	}

	//Draw the pie graph
	public void drawPieGraph(Graphics graphics)
	{

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 330, 360);

		int i, totalUnits, centerX, centerY, radius, startAngle;
		double unitAngleSize;

		//Set up center point and radius of the pie, and the unit angle size
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

		//Draw the wedges of the pie
		for(i=0; i < numberOfScores; i++)
		{
			int centralAngle = (int)(unitAngleSize * scores[i]);
			graphics.setColor(intToColor(i));
			graphics.fillArc(centerX, centerY, radius, radius, startAngle, centralAngle);
			startAngle = startAngle + centralAngle;
		}

		setVisible(true);
	}

	//Find the sum of the array
	public int sum(int [ ] a)
	{
		int i;
		int total = 0;
		for (i=0; i<a.length; i++)
			total = total + a[i];
		return total;
	}

	//Set the colors for the pie slices
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

	public void updateView(){

		setVisible(true);
		super.updateView();

	}
}