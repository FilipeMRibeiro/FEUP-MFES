
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private Number id;
  private String name;
  private String specialty;
  private String username;
  private String password;
  private Boolean loggedIn = false;

  public void cg_init_User_1(final Number i) {

    id = i;
    return;
  }

  public User(final Number i) {

    cg_init_User_1(i);
  }

  public Number getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public String getSpecialty() {

    return specialty;
  }

  public String getPassword() {

    return password;
  }

  public String getUsername() {

    return username;
  }

  public Boolean getLoggedIn() {

    return loggedIn;
  }

  public void setSpecialty(final String d) {

    specialty = d;
  }

  public void setName(final String n) {

    name = n;
  }

  public void setPassword(final String p) {

    password = p;
  }

  public void setUsername(final String n) {

    username = n;
  }

  public void setLoggedIn(final Boolean n) {

    loggedIn = n;
  }

  public void login(final String e, final String p) {

    Boolean andResult_6 = false;

    if (Utils.equals(e, username)) {
      if (Utils.equals(p, password)) {
        andResult_6 = true;
      }
    }

    if (andResult_6) {
      setLoggedIn(true);
    }
  }

  public User() {}

  public String toString() {

    return "User{"
        + "id := "
        + Utils.toString(id)
        + ", name := "
        + Utils.toString(name)
        + ", specialty := "
        + Utils.toString(specialty)
        + ", username := "
        + Utils.toString(username)
        + ", password := "
        + Utils.toString(password)
        + ", loggedIn := "
        + Utils.toString(loggedIn)
        + "}";
  }
}
