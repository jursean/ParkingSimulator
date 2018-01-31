package hanze.project.view;

import hanze.project.logic.Model;

import javax.swing.*;
import java.awt.*;

public class TabView extends AbstractView {

    public TabView(Model simulator) {
        super(simulator);

        JTabbedPane tabbedPane = new JTabbedPane();

        //JPanel dashboardPanel = new JPanel();
        BarView barView = new BarView(simulator);
        barView.add(new JLabel("Bar Chart"));
        tabbedPane.addTab("Bar Chart", barView);

        //JPanel transactionPanel = new JPanel();
        PieView pieView = new PieView(simulator);
        pieView.add(new JLabel("Pie Chart"));
        //
        // Add Transactions Tab
        //
        tabbedPane.addTab("Pie Chart", pieView);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(tabbedPane, BorderLayout.CENTER);
        this.setOpaque(true);

    }

}