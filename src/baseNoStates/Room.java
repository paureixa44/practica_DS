package baseNoStates;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Creates the rooms of the building and stores their doors and id
 */
public class Room extends Area {
  private final ArrayList<Door> doors;
  private final String id;

  public Room(String id) {
    this.id = id;
    this.doors = new ArrayList<>();
  }
  public String getId() { return this.id; }
  @Override
  public void acceptVisitor(Visitor v) {
    v.visitRoom(this);
  }
  /*
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }
  public void addDoorsGivingAccess(Door door){
    this.doors.add(door);
  }
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    } else {
      return null;
    }
  }
  */

  public ArrayList<Area> getUserAreas() { return new ArrayList<>(List.of(this));  }
}
