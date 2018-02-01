package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

public class LogoView extends AbstractView {

    private JLabel logo;

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 400, 320);

    }

    public LogoView(Model simulator) {

        super(simulator);

        this.logo = new JLabel("City Park Groningen");

        logo.setBounds(105, 80, 400, 15);

        logo.setForeground(Color.black);

        add(logo);

        this.setLayout(null);
    }
}

