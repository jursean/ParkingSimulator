package hanze.project.view;

import hanze.project.logic.Model;

import java.awt.*;

public class TableView extends AbstractView {

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 400, 320);

    }

    public TableView(Model simulator) {

        super(simulator);

    }
}

