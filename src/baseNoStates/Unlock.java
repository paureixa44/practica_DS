package baseNoStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Defines the Unlock state for the doors.
 */
public class Unlock extends State {
  private static final Logger logger = LoggerFactory.getLogger(Unlock.class);
  public Unlock(Door door) {
    super(door);
    this.id = DoorState.UNLOCKED;
  }
  @Override
  public void lock() {
    if (this.porta.isClosed()) { //mirem si la porta està tancada (si no ho està, no la podem bloquejar)
      this.porta.setDoorState(new Lock(this.porta));
      logger.info("Door " + this.porta.getId() + " has been locked");
    } else {
      logger.info("Can't lock the door " + this.porta.getId() + " because it's opened");
    }
  }
  @Override
  public void unlock() { //no la podem desbloquejar si ja ho està
    logger.info("Can't unlock door " + this.porta.getId() + " because it's already unlocked");
  }
  public void unlockShortly() {  logger.info("You can't do this action because door is unlocked"); }

  public void open() { this.porta.setClosedState(false); }
  public void close() { this.porta.setClosedState(true); }
}
