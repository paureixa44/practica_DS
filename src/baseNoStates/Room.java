package baseNoStates;

import org.json.JSONArray;
import org.json.JSONObject;

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
  public String getId() {
    return this.id;
  }
  @Override
  public void acceptVisitor(Visitor v) {
    v.visitRoom(this);
  }
  public ArrayList<Door> getDoors() {
    return doors;
  }

  public void addDoorsGivingAccess(Door door) {
    this.doors.add(door);
  }

  public ArrayList<Area> getUserAreas() { return new ArrayList<>(List.of(this));  }

  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", id);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : doors) {
      jsonDoors.put(d.toJson());
    }
    json.put("access_doors", jsonDoors);
    return json;
  }
}
