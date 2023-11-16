package baseNoStates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Stores the group id in which a user belongs, the areas the user is able to enter,
 * the actions the user can do and the schedule in which the user can make actions.
 */
public class UserGroup {
  private final String id;
  private final ArrayList<Area> areas;
  private final ArrayList<String> actions;
  private final Schedule schedule;

  /**
   * Constructor of the class.
   *
   * @param id  the name of the user group.
   * @param areas  the areas the users in this group can enter.
   * @param actions the actions the users in this group can do.
   * @param schedule the schuedule in wich the users can do actions.
   */
  public UserGroup(String id, ArrayList<Area> areas, ArrayList<String> actions, Schedule schedule) {
    this.id = id;
    this.areas = areas;
    this.actions = actions;
    this.schedule = schedule;
  }

  public String getId() { return this.id; }

  /**
   * Searches if the given area is in the users area to know if the user can be there.
   *
   * @param id  the id of the area.
   */
  public boolean validateArea(String id) {
    for (Area area : areas) {
      if (area.findAreaById(id) != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Validates if the action given can be done by the user.
   *
   * @param action  the action the user wants to do
   */
  public boolean validateActions(String action) {
    for (String actions : actions) {
      if (actions.equals(action)) {
        return true;
      }
    }
    return false;
  }

  public boolean validateDate(LocalDateTime date) {
    return schedule.validateDate(date);
  }
}
