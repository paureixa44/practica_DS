package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class GetDoorsGivingAccess extends Visitor {
  private final String id;
  private List<Door> doors = new ArrayList<>();

  public GetDoorsGivingAccess(String id) {
    this.id = id;
  }
  @Override
  public void visitPartition(Partition p) {

  }
  @Override
  public void visitRoom(Room r) {
    return null;
  }
}
