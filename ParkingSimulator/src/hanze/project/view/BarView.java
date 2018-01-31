package hanze.project.view;

import hanze.project.logic.Model;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class BarView extends AbstractView {

    private Map<Color, Integer> bars =
            new LinkedHashMap<Color, Integer>();

    public BarView(Model simulator) {
        super(simulator);

    }

    /**
     * Add new bar to chart
     * @param color color to display bar
     * @param value size of bar
     */
    public void addBar(Color color, int value)
    {
        bars.put(color, value);
        repaint();

    }

    @Override
    public void paintComponent(Graphics graphics)
    {

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 330, 360);

        Model simulator = (Model) super.model;
        int value1 = simulator.getNumberOfOpenSpots();
        int value2 = simulator.getNumberOfOpenResvSpots();
        int value3 = simulator.getTotalNoPassholder();
        int value4 = simulator.getTotalPassHolder();
        int value5 = simulator.getTotalReservationHolder();

        addBar(Color.WHITE, value1);
        addBar(Color.GREEN, value2);
        addBar(Color.RED, value3);
        addBar(Color.BLUE, value4);
        addBar(Color.DARK_GRAY, value5);

        // determine longest bar

        int max = Integer.MIN_VALUE;
        for (Integer value : bars.values())
        {
            max = Math.max(max, value);
        }

        // paint bars

        int width = (getWidth() / bars.size()) - 2;
        int x = 1;
        for (Color color : bars.keySet())
        {
            int value = bars.get(color);
            int height = (int)
                    ((getHeight()-5) * ((double)value / max));
            graphics.setColor(color);
            graphics.fillRect(x, getHeight() - height, width, height);
            graphics.setColor(Color.black);
            graphics.drawRect(x, getHeight() - height, width, height);
            x += (width + 2);
        }

        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 10 + 2, 50);
    }
}
