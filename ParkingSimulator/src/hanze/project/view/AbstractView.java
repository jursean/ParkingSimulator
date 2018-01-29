package hanze.project.view;

import hanze.project.logic.AbstractModel;
import javax.swing.*;

// CLASS

public class AbstractView extends JPanel {

    // FIELDS

    public AbstractModel model;

    // CONSTRUCTORS

    //  Constructor of AbstractView that expects a logic belonging to this view.
    //  @param model AbstractModel that belongs to this view.
    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);
        setLayout(null);
        setSize(200, 200);
        setVisible(true);
    }

    // METHODS

    public void setModel(AbstractModel model) {
        this.model=model;
    }

    public AbstractModel getModel() {
        return model;
    }

    public void updateView() {
        repaint();
    }
}
