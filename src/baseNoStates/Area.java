package baseNoStates;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements building areas (Partitions and Rooms).
 */
public abstract class Area {
  //public abstract List<Door> getDoorsGivingAccess();
  //public abstract Area findAreaById(String id);
  public abstract void acceptVisitor(Visitor v);
  public abstract String getId();
  public abstract ArrayList<Area> getUserAreas();
  public abstract JSONObject toJson(int depth);
}
