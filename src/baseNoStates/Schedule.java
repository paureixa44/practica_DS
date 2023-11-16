package baseNoStates;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Stores the dates, hours and days of the week a usergroup can do actions.
 */
public class Schedule {
  private final LocalDate date_start;
  private final LocalDate date_finish;
  private final LocalTime time_start;
  private final LocalTime time_finish;
  private ArrayList<DayOfWeek> available_days = new ArrayList<>();

  /**
   * Constructor of the class.
   *
   * @param date_start  date users can start making actions.
   * @param date_finish  last date users can make actions.
   * @param time_start  first hour users can make actions.
   * @param time_finish  last hour users can make actions
   * @param days  days users can make actions.
   */
  public Schedule(LocalDate date_start, LocalDate date_finish, LocalTime time_start, LocalTime time_finish, ArrayList<DayOfWeek> days) {
    this.date_start = date_start;
    this.date_finish = date_finish;
    this.time_finish = time_finish;
    this.time_start = time_start;
    this.available_days = days;
  }

  /**
   * Validates the date, hour and day of the week and tells if the user can make the action.
   *
   * @param date  the date to see if the user can do the action.
   */
  public boolean validateDate(LocalDateTime date) {
    LocalDate date1 = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
    LocalTime time1 = LocalTime.of(date.getHour(), date.getMinute());
    DayOfWeek day = date.getDayOfWeek();
    //fem totes les comprovacions per validar que pugui realitzar accions en la data, hora i dia de la setmana que i toqui
    if ((date1.isAfter(this.date_start) || date1.isEqual(this.date_start)) && (date1.isBefore(this.date_finish) || date1.isEqual(this.date_finish))) {
      if ((time1.isAfter(this.time_start) || time1.equals(this.time_start)) && (time1.isBefore(this.time_finish) || time1.equals(this.time_finish))) {
        for (DayOfWeek days : available_days) {
          if (day.equals(days)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
