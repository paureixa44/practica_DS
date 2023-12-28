package baseNoStates.requests;

import baseNoStates.Area;
import baseNoStates.DirectoryDoors;
import org.json.JSONObject;

public class RequestChildren implements Request {
  private final String areaId;
  private JSONObject jsonTree; // 1 level tree, root and children

  public RequestChildren(String areaId) {
    this.areaId = areaId;
  }

  public String getAreaId() {
    return areaId;
  }

  @Override
  public JSONObject answerToJson() {
    return jsonTree;
  }

  @Override
  public String toString() {
    return "RequestChildren{areaId=" + areaId + "}";
  }

  public void process() {
    //Area area = DirectoryDoors.getInstance().findAreaById(areaId);
    Area area = DirectoryDoors.findAreaById(areaId);
    jsonTree = area.toJson(1);
  }
}