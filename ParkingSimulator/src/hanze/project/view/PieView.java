package hanze.project.view;

import hanze.project.logic.AbstractModel;
import hanze.project.logic.Model;

import java.awt.*;


@SuppressWarnings("serial")
public class PieView extends AbstractView {

	public PieView(AbstractModel model) {

		super(model);

	}

	public void paintComponent(Graphics g) {
		int aantal=getModel().getAantal();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);
		
		g.fillArc(10, 10, 180, 180, 0, aantal);

	}

	public void updateView(){
		Model simulator = (Model) this.model;

		setVisible(true);
		//super.updateView;

	}
}