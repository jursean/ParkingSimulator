package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

public class TabView extends AbstractView {

    public TabView(Model simulator) {
        super(simulator);

        JTabbedPane tabbedPane = new JTabbedPane();

        PieView pieView = new PieView(simulator);
        pieView.add(new JLabel("Pie Chart"));
        tabbedPane.addTab("Pie Chart", pieView);

        BarView barView = new BarView(simulator);
        barView.add(new JLabel("Bar Chart"));
        tabbedPane.addTab("Bar Chart", barView);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(tabbedPane, BorderLayout.CENTER);
        this.setOpaque(true);

    }

}