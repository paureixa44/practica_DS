package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

/**
 * Defines the Unlock Shortly state for the doors.
 */
public class UnlockShortly extends State implements Observer {
  private static Clock clock;
  private final LocalDateTime time;
  private static final Logger logger = LoggerFactory.getLogger(Lock.class);

  /**
   * Constructor of the class.
   *
   * @param door  the door we want to put this state on.
   */
  public UnlockShortly(Door door) {
    super(door);
    this.id = DoorState.UNLOCKED_SHORTLY;
    clock = Clock.getInstance();
    clock.addObserver(this);
    this.time = LocalDateTime.now();
  }

  public void update(Observable o, Object arg) {
    LocalDateTime now = LocalDateTime.now();

    if (now.isAfter(this.time.plusSeconds(10))) { //si han passat els 10s
      o.deleteObserver(this);
      if (!this.porta.isClosed()) {
        //si està oberta, la posem en propped
        this.porta.setDoorState(new Propped(this.porta));
      } else {
        //si està tancada, la posem en lock
        this.porta.setDoorState(new Lock(this.porta));
      }
    }
  }
  public void unlockShortly() { }

  @Override
  public void unlock() { logger.info("you can't do this action"); }
  @Override
  public void lock() {
    if (this.porta.isClosed()) {
      this.porta.setDoorState(new Lock(this.porta));
    } else {
      logger.info("Can't lock the door " + this.porta.getId() + " because it's opened");
    }
  }
  public void open() { this.porta.setClosedState(false); }
  public void close() {
    this.porta.setClosedState(true);
  }
}
