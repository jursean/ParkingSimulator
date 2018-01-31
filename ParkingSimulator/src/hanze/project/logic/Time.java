package hanze.project.logic;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MINUTE;

/**
 * Class Time
 * Deze klasse zorgt ervoor dat de tijd goed staat.
 *
 * @author Jurian de Vries, Sebastiaan ter Veen, Deni Grabic, Tim Gorter, Sander Steenbergen
 * @version 31-01-2018
 */

public class Time extends AbstractModel{

    // De velden

    private Calendar startCalendar;
    private Calendar runningCalendar;
    private String startTime;

    // De constructors

    public Time() {
        this.startCalendar = Calendar.getInstance();
        this.runningCalendar = Calendar.getInstance();
        this.startTime = this.getStringFromCalendar(this.startCalendar);
    }

    // De methodes

    /**
     * Deze klasse zorgt ervoor dat er een minuut tijd verloopt.
     */

    public void tick() {
        this.runningCalendar.add(MINUTE,+1);
        super.notifyViews();
    }

    /**
     * Deze methode zorgt ervoor dat de tijd in het goede formaat staat.
     * @param cal Calender object
     * @return String Calenderdatum en tijd die omgezet is in een String
     */

    private String getStringFromCalendar(Calendar cal){
        String timeString = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))+"h";
        String dateString = cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR);

        return timeString+" "+dateString;
    }

    /**
     * Methode die de tijd reset.
     */

    public void reset(){
        this.startCalendar = Calendar.getInstance();
        this.runningCalendar = Calendar.getInstance();
        this.startTime = this.getStringFromCalendar(this.startCalendar);
        super.notifyViews();
    }

    /**
     * Geeft de tijd in de simulatie terug.
     * @return String met daarin de tijd van de simulatie
     */

    public String getCurrentTime(){
        return this.getStringFromCalendar(this.runningCalendar);
    }

    /**
     * Het verschil tussen de starttijd en de tijd die het nu is in de simulator.
     * @return long Met de tijd hoelang de simulator al draait
     */

    public long runningDifference() {
        return Math.abs(this.runningCalendar.getTimeInMillis() - this.startCalendar.getTimeInMillis());
    }

    /**
     * Hierin word de tijd dat de simulator draait omgezet in dagen.
     * @return String Met daarin de dagen dat de simulator draait
     */

    public String getRunningDays(){
        return Objects.toString(TimeUnit.MILLISECONDS.toDays(runningDifference()),null);
    }

    /**
     * Hierin word de tijd dat de simulator draait omgezet in uren.
     * @return String Met daarin de uren dat de simulator draait
     */

    public String getRunningHours(){
        return Objects.toString(TimeUnit.MILLISECONDS.toHours(runningDifference()),null);
    }

    /**
     * Hierin word de tijd dat de simulator draait omgezet in minuten.
     * @return String Met daarin de minuten dat de simulator draait
     */

    public String getRunningMinutes(){
        return Objects.toString(TimeUnit.MILLISECONDS.toMinutes(runningDifference()),null);
    }

    /**
     * Hierin word de tijd dat de simulator draait omgezet in weken.
     * @return String Met daarin de weken dat de simulator draait
     */

    public String getRunningWeeks(){
        long days = TimeUnit.MILLISECONDS.toDays(runningDifference());
        return Objects.toString((int) Math.floor(days/7),null);
    }

    /**
     * Geeft de starttijd terug als String.
     * @return String met daarin de starttijd
     */

    public String getStartTime() {
        return startTime;
    }

    /**
     * Geeft terug of het weekend is.
     *
     * @return boolean Of het weekend is
     */

    public boolean isWeekend(){
        return this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SATURDAY || this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * Methode om de tijd te resettten.
     */

    public void resetTime(){
        this.startCalendar = Calendar.getInstance();
        this.runningCalendar = Calendar.getInstance();
        this.startTime = this.getStringFromCalendar(this.startCalendar);
    }

    /**
     * Methode die bepaalt of er een voorstelling is.
     * @return boolean Of er een voorstelling is
     */

    public boolean isVoorstelling(){
        return this.runningCalendar.get(DAY_OF_WEEK) == Calendar.FRIDAY && this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 20 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) < 23 || this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SATURDAY && this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 20 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) < 23 || this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SUNDAY && this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 13 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) < 16;
    }

    /**
     * Methode die bepaalt of er een koopavondis.
     * @return boolean Of er een koopavond is
     */

    public boolean isKoopAvond(){
        return this.runningCalendar.get(DAY_OF_WEEK) == Calendar.THURSDAY && this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 17 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) < 21;
    }

    /**
     * @return boolean wanneer het de volgende dag is.
     **/

    public boolean volgendeDag(){
        return this.runningCalendar.get(Calendar.HOUR_OF_DAY) == 23 && this.runningCalendar.get(Calendar.MINUTE) == 59;
    }

    /**
     *
     * @return boolean of het avond is.
     */

    public boolean isAvond(){
        return this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 18 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) <= 24;
    }

    /**
     *
     * @return boolean Of het nacht is of niet
     */

    public boolean isNacht(){
        return this.runningCalendar.get(Calendar.HOUR_OF_DAY) >= 18 && this.runningCalendar.get(Calendar.HOUR_OF_DAY) < 6;
    }
}


