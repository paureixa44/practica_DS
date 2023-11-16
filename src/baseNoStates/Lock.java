package baseNoStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates the Lock state for the doors.
 */
public class Lock extends State { // classe heredada de state que posa la porta en lock
  private static final Logger logger = LoggerFactory.getLogger(Lock.class);
  public Lock(Door door) {
    super(door);
    this.id = DoorState.LOCKED;
  }
  @Override
  public void lock() { //si està bloquejada no la podrem bloquejar, ja que ja ho està
    logger.info("Can't lock door " + porta.getId() + " because it's already locked");
  }
  @Override
  public void unlock() { //desbloquejem la porta
    this.porta.setDoorState(new Unlock(this.porta));
    logger.info("Door " + this.porta.getId() + " has been unlocked");
  }
  public void open(){ logger.info("Can't open door " + porta.getId() + " because it's locked"); }
  public void close(){  logger.info("Can't close door " + porta.getId() + " because it's locked"); }
  public void unlockShortly() { this.porta.setDoorState(new UnlockShortly(this.porta)); }


}
