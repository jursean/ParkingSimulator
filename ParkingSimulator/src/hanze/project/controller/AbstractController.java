package hanze.project.controller;

import hanze.project.logic.AbstractModel;
import javax.swing.*;

// CLASS

public abstract class AbstractController extends JPanel {

    // FIELDS

    //  A controller should have a certain instance of the AbstractModel
    protected AbstractModel model;

    // CONSTRUCTORS

    //  Constructor of AbstractController with a logic belong to this controller
    //  @param model AbstractModel that belongs to this controller
    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
