package baseNoStates;

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
  /*
  @Override
  public List<Door> getDoorsGivingAccess() {
    ArrayList<Door> all_area_doors = new ArrayList<>(0); //array on guardem les portes
    //recorrem les habitacions de la particio per aconseguir les portes de cada una
    for (Area area : rooms) {
      all_area_doors.addAll(area.getDoorsGivingAccess()); //afegim les portes a l'array
    }
    return all_area_doors.stream().distinct().collect(Collectors.toList()); //treiem les portes repetides
  }

  */
  /*
  @Override
  public Area findAreaById(String id) {
    //comprovem si ja la tenim
    Area busca = null;
    if (this.id.equals(id)) {
      return this;
    } else { // si no la tenim la busquem recursivament per totes les areas
      for (Area area : rooms) {
        Area aux = area.findAreaById(id);
        if (aux != null) {
          busca = aux;
        }
      }
    }
    return busca;
  }
  */

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
}
