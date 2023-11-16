package baseNoStates;

import java.util.List;

public abstract class Visitor {
  public abstract void visitPartition(Partition p);
  public abstract void visitRoom(Room r);
  public abstract Area getArea();
}
