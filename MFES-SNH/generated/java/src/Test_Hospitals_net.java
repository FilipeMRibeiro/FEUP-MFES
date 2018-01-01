
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Test_Hospitals_net {
  private Hospitals_net n = new Hospitals_net();
  private User u = new User(1L);
  private User uu = new User(7L);
  private Hospital h = new Hospital(2L);
  private Hospital hh = new Hospital(5L);
  private Hospital hhh = new Hospital(6L);
  private Doctor d = new Doctor(3L);
  private Doctor dd = new Doctor(4L);

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void testHospitals_net() {

    assertTrue(!(SetUtil.inSet(h, n.getHospitals())));
    n.addHospital(h);
    assertTrue(SetUtil.inSet(h, n.getHospitals()));
    assertTrue(!(SetUtil.inSet(d, n.getDoctors())));
    n.addDoctor(d);
    assertTrue(SetUtil.inSet(d, n.getDoctors()));
    assertTrue(Utils.equals(n.docInHospital(d, h), false));
    h.addDoctor(d);
    assertTrue(n.docInHospital(d, h));
    assertTrue(!(SetUtil.inSet(u, n.getUsers())));
    n.addUser(u);
    assertTrue(SetUtil.inSet(u, n.getUsers()));
    assertTrue(!(SetUtil.inSet(d, n.getSpecialtyDoctors("spec"))));
    d.setSpecialty("spec");
    assertTrue(SetUtil.inSet(d, n.getSpecialtyDoctors("spec")));
    assertTrue(!(SetUtil.inSet("spec", h.getSpecialties())));
    h.addSpecialty("spec");
    assertTrue(SetUtil.inSet("spec", h.getSpecialties()));
    assertTrue(SetUtil.inSet(h, n.getHospitalsSpecialty("spec")));
    assertTrue(!(SetUtil.inSet("subs", h.getSubsystems())));
    h.addSubsystem("subs");
    assertTrue(SetUtil.inSet("subs", h.getSubsystems()));
    assertTrue(SetUtil.inSet(h, n.getHospitalsSubsystems("subs")));
    n.addUser(uu);
    d.addUser(uu);
    dd.setSpecialty("spec");
    assertTrue(Utils.equals(d, n.getMinWaitDoc("spec")));
    assertTrue(!(Utils.equals(dd, n.getMinWaitDoc("spec"))));
    assertTrue(SetUtil.inSet(h, n.docHospitals(d)));
    assertTrue(Utils.equals(h.getId(), 2L));
    d.setName("doc");
    assertTrue(Utils.equals(d.getName(), "doc"));
    assertTrue(!(SetUtil.inSet(u, d.getUsers())));
    d.addUser(u);
    assertTrue(SetUtil.inSet(u, d.getUsers()));
    assertTrue(Utils.equals(d.getId(), 3L));
    assertTrue(Utils.equals(u.getId(), 1L));
    u.setName("user1");
    assertTrue(Utils.equals(u.getName(), "user1"));
    u.setSpecialty("spec");
    assertTrue(Utils.equals(u.getSpecialty(), "spec"));
    u.setPassword("pass");
    assertTrue(Utils.equals(u.getPassword(), "pass"));
    u.setUsername("user");
    assertTrue(Utils.equals(u.getUsername(), "user"));
    u.login("user", "pass");
    assertTrue(u.getLoggedIn());
  }

  public static void main() {

    new Test_Hospitals_net().testHospitals_net();
  }

  public Test_Hospitals_net() {}

  public String toString() {

    return "Test_Hospitals_net{"
        + "n := "
        + Utils.toString(n)
        + ", u := "
        + Utils.toString(u)
        + ", uu := "
        + Utils.toString(uu)
        + ", h := "
        + Utils.toString(h)
        + ", hh := "
        + Utils.toString(hh)
        + ", hhh := "
        + Utils.toString(hhh)
        + ", d := "
        + Utils.toString(d)
        + ", dd := "
        + Utils.toString(dd)
        + "}";
  }
}
