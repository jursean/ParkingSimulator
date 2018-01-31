package hanze.project.controller;

        import hanze.project.logic.AbstractModel;
        import javax.swing.*;

/**
 * Class AbstractController
 * Alle controllers moeten een instantie hebben van deze klasse.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public abstract class AbstractController extends JPanel {

    // De velden

    // Een controller zou een instantie van de "AbstractModel" moeten hebben.
    protected AbstractModel model;

    // De constructors

    /**
     * Constructor of AbstractController with a logic belong to this controller.
     * @return Het model "AbstractModel" die behoord tot deze controller.
     */

    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
