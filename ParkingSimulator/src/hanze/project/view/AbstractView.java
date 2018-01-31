package hanze.project.view;

import hanze.project.logic.AbstractModel;
import javax.swing.*;

/**
 * Class AbstractView
 * ??????????????????????????????????????????????????????????????????????
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class AbstractView extends JPanel {

    // De velden

    public AbstractModel model;

    // De constructors

    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);
        setLayout(null);
        setSize(200, 200);
        setVisible(true);
    }

    // METHODS

//    public void setModel(AbstractModel model) {
//        this.model=model;
//    }
//
//    public AbstractModel getModel() {
//        return model;
//    }

    /**
     * Update de view
     */

    public void updateView() {
        repaint();
    }
}
