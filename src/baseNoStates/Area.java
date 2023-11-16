package baseNoStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements building areas (Partitions and Rooms).
 */
public abstract class Area {
  public abstract List<Door> getDoorsGivingAccess();
  public abstract String getId();
  public abstract Area findAreaById(String id);
  public abstract ArrayList<Area> getUserAreas();

}
