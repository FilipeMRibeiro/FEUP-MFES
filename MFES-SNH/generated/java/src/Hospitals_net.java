
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospitals_net {
  private VDMSet hospitals = SetUtil.set();
  private VDMSet doctors = SetUtil.set();
  private VDMSet users = SetUtil.set();

  public void cg_init_Hospitals_net_1() {

    return;
  }

  public Hospitals_net() {

    cg_init_Hospitals_net_1();
  }

  public VDMSet getHospitals() {

    return Utils.copy(hospitals);
  }

  public VDMSet getDoctors() {

    return Utils.copy(doctors);
  }

  public VDMSet getUsers() {

    return Utils.copy(users);
  }

  public void addDoctor(final Doctor d) {

    doctors = SetUtil.union(SetUtil.set(d), Utils.copy(doctors));
  }

  public void addUser(final User d) {

    users = SetUtil.union(SetUtil.set(d), Utils.copy(users));
  }

  public void addHospital(final Hospital h) {

    hospitals = SetUtil.union(SetUtil.set(h), Utils.copy(hospitals));
  }

  public VDMSet getSpecialtyDoctors(final String s) {

    VDMSet ret = SetUtil.set();
    for (Iterator iterator_2 = doctors.iterator(); iterator_2.hasNext(); ) {
      Doctor doc = (Doctor) iterator_2.next();
      if (Utils.equals(doc.getSpecialty(), s)) {
        ret = SetUtil.union(Utils.copy(ret), SetUtil.set(doc));
      }
    }
    return Utils.copy(ret);
  }

  public VDMSet getHospitalsSpecialty(final String s) {

    VDMSet ret = SetUtil.set();
    for (Iterator iterator_3 = hospitals.iterator(); iterator_3.hasNext(); ) {
      Hospital hosp = (Hospital) iterator_3.next();
      if (SetUtil.inSet(s, hosp.getSpecialties())) {
        ret = SetUtil.union(Utils.copy(ret), SetUtil.set(hosp));
      }
    }
    return Utils.copy(ret);
  }

  public VDMSet getHospitalsSubsystems(final String s) {

    VDMSet ret = SetUtil.set();
    for (Iterator iterator_4 = hospitals.iterator(); iterator_4.hasNext(); ) {
      Hospital hosp = (Hospital) iterator_4.next();
      if (SetUtil.inSet(s, hosp.getSubsystems())) {
        ret = SetUtil.union(Utils.copy(ret), SetUtil.set(hosp));
      }
    }
    return Utils.copy(ret);
  }

  public Doctor getMinWaitDoc(final String s) {

    VDMSet sset = getSpecialtyDoctors(s);
    Doctor md = null;
    Number min = 99L;
    for (Iterator iterator_5 = sset.iterator(); iterator_5.hasNext(); ) {
      Doctor doc = (Doctor) iterator_5.next();
      if (doc.waitingTime().longValue() < min.longValue()) {
        md = doc;
      }
    }
    return md;
  }

  public Boolean docInHospital(final Doctor d, final Hospital h) {

    for (Iterator iterator_6 = h.getDoctors().iterator(); iterator_6.hasNext(); ) {
      Doctor doc = (Doctor) iterator_6.next();
      if (Utils.equals(d, doc)) {
        return true;
      }
    }
    return false;
  }

  public VDMSet docHospitals(final Doctor d) {

    VDMSet ret = SetUtil.set();
    for (Iterator iterator_7 = hospitals.iterator(); iterator_7.hasNext(); ) {
      Hospital hosp = (Hospital) iterator_7.next();
      if (docInHospital(d, hosp)) {
        ret = SetUtil.union(Utils.copy(ret), SetUtil.set(hosp));
      }
    }
    return Utils.copy(ret);
  }

  public String toString() {

    return "Hospitals_net{"
        + "hospitals := "
        + Utils.toString(hospitals)
        + ", doctors := "
        + Utils.toString(doctors)
        + ", users := "
        + Utils.toString(users)
        + "}";
  }
}
