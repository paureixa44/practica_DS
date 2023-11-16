package baseNoStates;

import java.sql.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Stores all users.
 */
public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  /**
   * Creates all users, usergroups and schedules and assign them.
   */
  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    Schedule temp = new Schedule(null, null, null, null, null);
    UserGroup temporally = new UserGroup("temporally", new ArrayList<>(), new ArrayList<>(), temp);
    User Bernat = new User("Bernat", "12345", temporally);
    User Blai = new User("Blai", "77532", temporally);

    users.add(Bernat);
    users.add(Blai);

    // employees :
    // Sep. 1 2023 to Mar. 1 2024
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    Area ground_floor = DirectoryDoors.findAreaById("ground_floor");
    Area floor1 = DirectoryDoors.findAreaById("floor1");
    Area stairs = DirectoryDoors.findAreaById("stairs");
    Area exterior = DirectoryDoors.findAreaById("exterior");
    //llista amb les areas en les que poden estar els employees
    ArrayList<Area> employee_areas = new ArrayList<>(Arrays.asList(ground_floor, floor1, stairs, exterior));
    //llista amb les accions que poden realitzar els employees
    ArrayList<String> employee_actions = new ArrayList<>(List.of(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));
    //data d'inici i final en les que poden fer accions
    LocalDate date_start = LocalDate.of(2023, 9, 1);
    LocalDate date_finish = LocalDate.of(2024, 3, 1);
    //hores del dia en les que poden fer accions
    LocalTime time_start = LocalTime.of(9, 0);
    LocalTime time_finish = LocalTime.of(17, 0);
    //dies en els que poden fer accins
    ArrayList<DayOfWeek> emp_days = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

    //creem un schedulel per a aquest grup d'usuaris amb la info anterior
    Schedule emp = new Schedule(date_start, date_finish, time_start, time_finish, emp_days);
    //creem el grup d'usuaris i li passem les areas, accions i l'horari
    UserGroup employees = new UserGroup("employees", employee_areas, employee_actions, emp);
    User Ernest = new User("Ernest", "74984", employees);
    User Eulalia = new User("Eulalia", "43295", employees);

    users.add(Ernest);
    users.add(Eulalia);

    // managers :
    // Sep. 1 2023 to Mar. 1 2024
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    Area admin_areas = DirectoryDoors.findAreaById("building");
    //accions que poden fer accions
    ArrayList<String> admin_actions = new ArrayList<>(Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.CLOSE, Actions.OPEN,
        Actions.UNLOCK_SHORTLY));
    //hores en les que poden fer accions
    time_start = LocalTime.of(8, 0);
    time_finish = LocalTime.of(20, 0);
    ArrayList<DayOfWeek> man_days = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    Schedule man = new Schedule(date_start, date_finish, time_start, time_finish, man_days);
    UserGroup managers = new UserGroup("managers", new ArrayList<>(List.of(admin_areas)), admin_actions, man);
    User Manel = new User("Manel", "95783", managers);
    User Marta = new User("Marta", "05827", managers);

    users.add(Manel);
    users.add(Marta);

    // admin :
    // always=2023 to 2100
    // all days of the week
    // all actions
    // all spaces
    LocalDate admin_date_start = LocalDate.of(2023, 1, 1);
    LocalDate admin_date_finish = LocalDate.of(2100, 12, 31);
    time_start = LocalTime.of(0, 0);
    time_finish = LocalTime.of(23, 59);
    ArrayList<DayOfWeek> admin_days = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

    Schedule ad = new Schedule(admin_date_start, admin_date_finish, time_start, time_finish, admin_days);
    UserGroup admin = new UserGroup("admin", new ArrayList<>(List.of(admin_areas)), admin_actions, ad);
    User Ana = new User("Ana", "11343", admin);

    users.add(Ana);
  }

  /**
   * Finds an user given a credential
   *
   * @param credential  the user credential.
   */
  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }
}
