package hanze.project.logic;

import hanze.project.view.AbstractView;

import java.util.ArrayList;
import java.util.List;

// CLASS

public class AbstractModel implements Runnable{

    // FIELDS

    // List of views that are linked to the logic
    private int aantal;
    private List<AbstractView> views;
    private boolean run;

    // CONSTRUCTORS

    //  Constructor for AbstractModel that creates a list with views that belong to the logic that is created
    public AbstractModel() {
        this.views = new ArrayList<AbstractView>();
    }

    // METHODS

    //  Method to add a view to the logic
    //  @param view AbstractView that belongs to the logic.
    public void addView(AbstractView view) {
        this.views.add(view);
    }

    //  Method to notify the views that belong to this logic that an update is required because a change has been done
    public void notifyViews(){
        for (AbstractView view : this.views) view.updateView();
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        if (aantal>=0 && aantal <=360) {
            this.aantal=aantal;
            notifyViews();
        }
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        run=false;
    }

    @Override
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
