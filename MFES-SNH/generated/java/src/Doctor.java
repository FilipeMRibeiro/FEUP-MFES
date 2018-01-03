
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Doctor {
  private Number id;
  private String name;
  private String specialty;
  private VDMSet users = SetUtil.set();

  public void cg_init_Doctor_1(final Number i) {

    id = i;
    specialty = "generalist";
    return;
  }

  public Doctor(final Number i) {

    cg_init_Doctor_1(i);
  }

  public String getName() {

    return name;
  }

  public VDMSet getUsers() {

    return Utils.copy(users);
  }

  public void addUser(final User p) {

    users = SetUtil.union(SetUtil.set(p), Utils.copy(users));
  }

  public void setName(final String n) {

    name = n;
  }

  public Number getId() {

    return id;
  }

  public String getSpecialty() {

    return specialty;
  }

  public void setSpecialty(final String s) {

    specialty = s;
  }

  public Number waitingTime() {

    Number count = 0L;
    for (Iterator iterator_1 = users.iterator(); iterator_1.hasNext(); ) {
      User us = (User) iterator_1.next();
      count = count.longValue() + 1L;
    }
    return count;
  }

  public Doctor() {}

  public String toString() {

    return "Doctor{"
        + "id := "
        + Utils.toString(id)
        + ", name := "
        + Utils.toString(name)
        + ", specialty := "
        + Utils.toString(specialty)
        //+ ", users := "
        //+ Utils.toString(users)
        + "}";
  }
}
