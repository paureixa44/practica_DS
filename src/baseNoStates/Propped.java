package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates the propped state of the doors.
 */
public class Propped extends State {
  private static final Logger logger = LoggerFactory.getLogger(Lock.class);
  public Propped(Door door) {
    super(door);
    this.id = DoorState.PROPPED;
  }

  @Override
  public void lock() { logger.info("Close the door first"); }

  @Override
  public void unlock() { logger.info("You can't do that now"); }
  public void open() { logger.info("It's already opened"); }
  public void close() {
    this.porta.setClosedState(true);
    this.porta.setDoorState(new Lock(this.porta));
  }

  public void unlockShortly(){ logger.info("You can't do that"); }
}
