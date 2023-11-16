package baseNoStates;

import java.util.List;

public class FindAreaById extends Visitor{
  private final String id;
  private Area area = null;

  public FindAreaById(String id) {
    this.id = id;
  }

  public Area getArea() {
    return this.area;
  }
  public void visitPartition(Partition p) {
    Area busca = null;
    if (p.getId().equals(id)) {
      area = p;
    } else { // si no la tenim la busquem recursivament per totes les areas
      for (Area area : p.getUserAreas()) {
        area.acceptVisitor(this);
      }
    }
  }
  @Override
  public void visitRoom(Room r) {
    if (r.getId().equals(id)) {
      area = r;
    }
  }
}
