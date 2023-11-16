package baseNoStates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetDoorsGivingAccess extends Visitor {
  private final String id;
  private List<Door> doors = new ArrayList<>();

  public GetDoorsGivingAccess(String id) {
    this.id = id;
  }
  @Override
  public Area getArea() {
    return null;
  }

  @Override
  public List<Door> getDoors() {
    return doors;
  }

  @Override
  public void visitPartition(Partition p) {
    //recorrem les habitacions de la particio per aconseguir les portes de cada una
    for (Area area : p.getRooms()) {
      area.acceptVisitor(this);
    }
    doors = doors.stream().distinct().collect(Collectors.toList()); //treiem les portes repetides
  }
  @Override
  public void visitRoom(Room r) {
    ArrayList<Door> d = r.getDoors();
    doors.addAll(d);
  }
}
