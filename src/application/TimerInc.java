package application;

import java.util.Timer;

import java.util.TimerTask;
import javafx.scene.control.TextField;



/**
 * @author KECHADI Farid
 * 
 * The TimerInc class manages timer functionality.
 * It uses the java.util.Timer class to schedule a task that increments seconds, minutes, and hours.
 * The timer updates TextField components to display the current time.
 */
public class TimerInc {

    public Timer timer; // Timer object for scheduling tasks
    public int seconds; // Seconds counter
    public int minutes; // Minutes counter
    public int hours;   // Hours counter

    /**
     * Constructor for the TimerInc class.
     * Initializes the Timer object.
     */
    public TimerInc() {
        timer = new Timer();
    }

    /**
     * Starts the timer and updates the provided TextField components with the current time.
     *
     * @param ss The TextField for displaying seconds.
     * @param mm The TextField for displaying minutes.
     * @param hh The TextField for displaying hours.
     */
    public void startTimer(TextField ss, TextField mm, TextField hh) {
        seconds = 0;
        minutes = 0;
        hours = 0;

        ss.setText("0");
        mm.setText("0");
        hh.setText("0");

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;

                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }

                if (minutes == 60) {
                    hours++;
                    minutes = 0;
                    seconds = 0;
                }

                ss.setText(String.valueOf(seconds));
                mm.setText(String.valueOf(minutes));
                hh.setText(String.valueOf(hours));
            }
        }, 0, 1000); // Run the task every 1000 milliseconds (1 second)
    }

    /**
     * Stops the timer and prints a message indicating that the timer has been stopped.
     */
    public void stopTimer() {
        timer.cancel();
        System.out.println("Timer stopped.");
    }
}