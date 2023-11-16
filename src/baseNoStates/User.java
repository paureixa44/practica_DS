package baseNoStates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the users name, group and credential.
 */
public class User {
  private final String name;
  private final String credential;
  private final UserGroup group;

  /**
   * Constructor of the class.
   *
   * @param name  the name of the user.
   * @param credential  the credential of the user.
   * @param group  the group in which the user belongs.
   */
  public User(String name, String credential, UserGroup group) {
    this.name = name;
    this.credential = credential;
    this.group = group;
  }
  public String getCredential() {
    return credential;
  }
  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
  public boolean validateArea(String id) { return group.validateArea(id); }
  public boolean validateActions(String action) { return group.validateActions(action); }
  public boolean validateDate(LocalDateTime date) {  return group.validateDate(date);  }

}
