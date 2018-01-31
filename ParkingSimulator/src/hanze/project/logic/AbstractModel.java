package hanze.project.logic;

import hanze.project.view.AbstractView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AbstractModel
 * Deze klasse maakt de knoppen in de GUI.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class AbstractModel implements Runnable{

    // De velden

    // Een lijst van views die gelinkt zijn aan de logic.

    private int aantal;
    private List<AbstractView> views;
    private boolean run;

    // De constructors

    //  Constructor van AbstractModel

    public AbstractModel() {
        this.views = new ArrayList<AbstractView>();
    }

    // De methodes

    /**
     * Deze methode voegd views toe aan de logic.
     * @param view De abstactview die behoort tot de logic.
     */
    public void addView(AbstractView view) {
        this.views.add(view);
    }

    /**
     * Deze methode word gebruikt om de views te updaten.
     */

    public void notifyViews(){
        for (AbstractView view : this.views) view.updateView();
    }

    /**
     * Deze methode geeft het aantal auto's terug.
     * @return aantal Het aantal autos in de garage.
     */

    public int getAantal() {
        return aantal;
    }

    /**
     * Deze methode veranderd het aantal views.
     * @param aantal Aantal views
     */

    public void setAantal(int aantal) {
        if (aantal>=0 && aantal <=360) {
            this.aantal=aantal;
            notifyViews();
        }
    }

    /**
     * Deze methode zorgt ervoor dat de thread draait.
     */

    public void run() {
        run=true;
        while(run) {
            setAantal(getAantal()+1);
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

}
