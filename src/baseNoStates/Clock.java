package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Counts the time for the unlock shortly state.
 */
public class Clock extends Observable {
  protected Timer timer;
  private static Clock instance = null;
  private final int period;

  /**
   * Constructor of Clock
   *
   * @param period  indicates the period.
   */
  public Clock(int period) {
    this.period = period;
    this.timer = new Timer();
    start();
  }
  public static Clock getInstance() {
    if(instance == null) {
      instance = new Clock(1);
    }
    return instance;
  }
  /**
   * Starts the clock.
   */
  public void start() {
    TimerTask repeat = new TimerTask() { //creem un thread
      @Override
      public void run() {
        setChanged(); //mirem si ha canviat
        notifyObservers(); //si ha canviat notfiquem a l'observer
      }
    };
    timer.scheduleAtFixedRate(repeat, 1, 1000 * period);
  }
  public void stop() { this.timer.cancel(); }

}
