package hanze.project.view;

import hanze.project.logic.Model;

import java.awt.*;


@SuppressWarnings("serial")
public class PieView extends AbstractView {

	public PieView(Model simulator) {

		super(simulator);

	}

	public void paintComponent(Graphics graphics) {
		Model simulator = (Model) super.model;
		int aantal = simulator.getTotalCars();

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 300, 300);

		graphics.setColor(Color.BLUE);
		graphics.fillArc(25, 25, 250, 250, 0, aantal);

	}

	public void updateView(){

		setVisible(true);
		super.updateView();

	}
}