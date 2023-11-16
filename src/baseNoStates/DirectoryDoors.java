package baseNoStates;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all building doors and areas.
 */
public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;
  private static Area building;

  /**
   * Creates all building doors and areas (Partitions and Rooms).
   */
  public static void makeDoors() {
    //basement
    Room parking = new Room("parking");
    Partition basement = new Partition("basement", new ArrayList<>(List.of(parking)));

    //ground floor
    Room room1 = new Room("room1");
    Room room2 = new Room("room2");
    Room hall = new Room("hall");
    Partition ground_floor = new Partition("ground_floor", new ArrayList<>(Arrays.asList(room1, room2, hall)));

    //floor1
    Room room3 = new Room("room3");
    Room it = new Room("IT");
    Room corridor = new Room("corridor");
    Partition floor1 = new Partition("floor1", new ArrayList<>(Arrays.asList(room3, it, corridor)));

    Room exterior = new Room("exterior");
    Room stairs = new Room("stairs");

    building = new Partition("building", new ArrayList<>(Arrays.asList(basement, ground_floor, floor1, stairs, exterior)));

    // basement
    Door d1 = new Door("D1", exterior, parking); // exterior, parking
    Door d2 = new Door("D2", stairs, parking); // stairs, parking

    // ground floor
    Door d3 = new Door("D3", exterior, hall); // exterior, hall
    Door d4 = new Door("D4", stairs, hall); // stairs, hall
    Door d5 = new Door("D5", hall, room1); // hall, room1
    Door d6 = new Door("D6", hall, room2); // hall, room2

    // first floor
    Door d7 = new Door("D7", stairs, corridor); // stairs, corridor
    Door d8 = new Door("D8", corridor, room3); // corridor, room3
    Door d9 = new Door("D9", corridor, it); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  /**
   * Finds a door given a door id.
   *
   * @param id  the id of the door.
   */
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  public static Area findAreaById(String id) {
    return building.findAreaById(id);
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
