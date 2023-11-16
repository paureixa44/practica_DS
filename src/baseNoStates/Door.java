package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores all doors in the building and their state.
 */
public class Door {
  private final String id;
  private boolean closed; // physically
  private State doorState; //per detectar l'estat de la porta (locked o unlocked)
  private final Room from; //from
  private final Room to; //to
  private static final Logger logger = LoggerFactory.getLogger(Lock.class);

  /**
   * Constructor of the class.
   *
   * @param id  the door id.
   * @param from  from wich area you open the door.
   * @param to  to wich area you go when opened the door.
   */
  public Door(String id, Room from, Room to) {
    this.id = id;
    closed = true;
    //locked = true;
    doorState = new Lock(this); //iniciem la porta a l'estat locked
    this.from = from;
    this.to = to;
    this.to.addDoorsGivingAccess(this);
    //this.from.addDoorsGivingAccess(this);
  }

  /**
   * Processes the request sent by the user.
   *
   * @param request  the request.
   */
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      logger.warn("not authorized");
    }
    request.setDoorStateName(getStateName());
  }
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        doorState.open();
        break;
      case Actions.CLOSE:
        doorState.close();
        break;
      case Actions.LOCK:
        //si està unlocked la bloquejem
        doorState.lock();
        break;
        // TODO
        // fall through
      case Actions.UNLOCK:
        //si està locked la desbloquejem
        doorState.unlock();
        break;
        // TODO
        // fall through
      case Actions.UNLOCK_SHORTLY:
        // TODO
        doorState.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }
  public void setDoorState(State state) { this.doorState = state; } //mètode amb el qual canviarem l'estat de la porta a lock o unlock
  public void setClosedState(Boolean closed) { this.closed = closed; } //mètode amb el qual canviarem l'estat de la porta a lock o unlock
  public Area getFrom(){return this.from;}
  public Area getTo(){return this.to;}
  public String getStateName() {
    return doorState.getId();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  /**
   * Creates a json object.
   */
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
