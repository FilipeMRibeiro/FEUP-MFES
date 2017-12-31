
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  private Number id;
  private VDMSet doctors = SetUtil.set();
  private VDMSet specialties = SetUtil.set();
  private VDMSet subsystems = SetUtil.set();

  public void cg_init_Hospital_1(final Number i) {

    id = i;
    return;
  }

  public Hospital(final Number i) {

    cg_init_Hospital_1(i);
  }

  public Number getId() {

    return id;
  }

  public VDMSet getDoctors() {

    return Utils.copy(doctors);
  }

  public void addDoctor(final Doctor d) {

    doctors = SetUtil.union(SetUtil.set(d), Utils.copy(doctors));
  }

  public void addSpecialty(final String s) {

    specialties = SetUtil.union(SetUtil.set(s), Utils.copy(specialties));
  }

  public VDMSet getSpecialties() {

    return Utils.copy(specialties);
  }

  public void addSubsystem(final String s) {

    subsystems = SetUtil.union(SetUtil.set(s), Utils.copy(subsystems));
  }

  public VDMSet getSubsystems() {

    return Utils.copy(subsystems);
  }

  public Hospital() {}

  public String toString() {

    return "Hospital{"
        + "id := "
        + Utils.toString(id)
        + ", doctors := "
        + Utils.toString(doctors)
        + ", specialties := "
        + Utils.toString(specialties)
        + ", subsystems := "
        + Utils.toString(subsystems)
        + "}";
  }
}
