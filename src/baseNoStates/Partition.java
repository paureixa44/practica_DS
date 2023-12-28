package baseNoStates;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Creates the Partition areas for the building and stores their rooms and id.
 */
public class Partition extends Area {
  private final String id;
  private final ArrayList<Area> rooms;

  public Partition(String id, ArrayList<Area> areas) {
    this.id = id;
    this.rooms = areas;
  }
  public String getId() { return this.id; }

  @Override
  public void acceptVisitor(Visitor v) {
    v.visitPartition(this);
  }
  public ArrayList<Area> getRooms() {
    return rooms;
  }

  /**
   * Gets all the areas a user can be in.
   */
  public ArrayList<Area> getUserAreas() {
    ArrayList<Area> test = new ArrayList<>();
    for (Area area : rooms) {
      ArrayList<Area> ex = area.getUserAreas();
      test.addAll(ex);
    }
    return test;
  }

  public JSONObject toJson(int depth) {
    // for depth=1 only the root and children,
    // for recusive = all levels use Integer.MAX_VALUE
    JSONObject json = new JSONObject();
    json.put("class", "partition");
    json.put("id", id);
    JSONArray jsonAreas = new JSONArray();
    if (depth > 0) {
      for (Area a : rooms) {
        jsonAreas.put(a.toJson(depth - 1));
      }
      json.put("areas", jsonAreas);
    }
    return json;
  }
}
