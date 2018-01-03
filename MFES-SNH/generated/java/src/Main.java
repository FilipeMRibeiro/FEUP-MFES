import org.overture.codegen.runtime.VDMSet;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int choice = -1;
    private static int loggedInUser = -1;
    private static User user;
    private static int nrHospitals = 1;
    private static int nrDocs = 1;
    private static int nrUsers = 1;

    private static Hospitals_net hn = new Hospitals_net();

    public static void main(String[] args){
        initDB();


        //test code


        Hospital h = new Hospital(nrHospitals++);
        Hospital hh = new Hospital(nrHospitals++);
        Doctor d = new Doctor(nrDocs++);
        Doctor dd = new Doctor(nrDocs++);
        Doctor ddd = new Doctor(nrDocs++);
        Doctor dddd = new Doctor(nrDocs++);

        d.setSpecialty("cardiology");
        dd.setSpecialty("cardiology");

        hn.addHospital(h);
        hn.addHospital(hh);
        hn.addDoctor(d);
        hn.addDoctor(dd);
        hn.addDoctor(ddd);
        hn.addDoctor(dddd);

/*        hn.addHospital(h);
        h.addDoctor(d);
        h.addDoctor(dd);
        hn.addDoctor(d);
        hn.addDoctor(dd);

        Iterator hni = hn.getDoctors().iterator();
        while (hni.hasNext()) {
            Doctor tmp = (Doctor) hni.next();
            System.out.println(tmp.getId());
        }
*/


        //real code
        mainMenu();
    }

    private static void initDB(){
        Iterator hi = hn.getHospitals().iterator();
        Iterator di = hn.getDoctors().iterator();
        Iterator ui = hn.getUsers().iterator();

        while (hi.hasNext()) {
            nrHospitals++;
        }

        while (di.hasNext()) {
            nrDocs++;
        }

        while (ui.hasNext()) {
            nrUsers++;
        }

        //TODO: read hospitals, doctors and users from file
    }

    private static int parseInput() {
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e){
            System.err.println("Input must be an integer");
            choice = -1;
            scanner.nextLine();
            return -1;
        }

        return 0;
    }

    private static void printSeparator() {
        System.out.println("\n#################################################");
    }

    private static void resetChoice() {
        choice = -1;
    }

    private static void shortMenuEntry(String text, int option) {
        System.out.println(String.format("%-25s- %s" , text, Integer.toString(option) ));
    }

    private static void longMenuEntry(String text, int option) {
        System.out.println(String.format("%-80s- %s" , text, Integer.toString(option) ));
    }

    private static void mainMenu(){
        resetChoice();
        printSeparator();
        System.out.println("Main Menu\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Administator Menu", 1);
            shortMenuEntry("Pacient Menu", 2);
            System.out.println();
            shortMenuEntry("Exit", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                return;
            case 1:
                adminMenu();
                break;
            case 2:
                pacientMenu();
                break;
            default:
                break;
        }

    }

    private static void adminMenu(){
        resetChoice();
        printSeparator();
        System.out.println("Administrator Menu\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Hospitals Menu", 1);
            shortMenuEntry("Doctors Menu", 2);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                mainMenu();
                break;
            case 1:
                hospitalsMenu();
                break;
            case 2:
                doctorsMenu();
                break;
            default:
                break;
        }
    }

    private static void pacientMenu(){
        resetChoice();
        printSeparator();
        System.out.println("Pacient Menu\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Register", 1);
            shortMenuEntry("Login", 2);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                mainMenu();
                break;
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            default:
                break;
        }
    }

    private static void hospitalsMenu() {
        resetChoice();
        printSeparator();
        System.out.println("Hospitals Menu\n");

        do {
            if (choice > 0 && choice < 3) {
                break;
            }

            shortMenuEntry("Add Hospital", 1);
            shortMenuEntry("Edit Hospital", 2);
            shortMenuEntry("Explore", 3);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                adminMenu();
                break;
            case 1:
                createHospital();
                break;
            case 2:
                editHospitals();
                break;
            case 3:
                exploreHospitals();
                break;
            default:
                break;
        }
    }

    private static void doctorsMenu() {
        resetChoice();
        printSeparator();
        System.out.println("Doctors Menu\n");

        do {
            if (choice > 0 && choice < 3) {
                break;
            }

            shortMenuEntry("Add Doctor", 1);
            shortMenuEntry("Edit Doctor", 2);
            shortMenuEntry("Explore", 3);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                adminMenu();
                break;
            case 1:
                createDoctor();
                break;
            case 2:
                editDoctors();
                break;
            case 3:
                exploreDoctors();
                break;
            default:
                break;
        }
    }

    private static void register() {
        resetChoice();
        printSeparator();
        System.out.println("Register\n");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        //TODO: show as *******
        String password = scanner.nextLine();

        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: check unique username

        //TODO: save to file
        User u = new User(nrUsers++);
        u.setName(name);
        u.setUsername(username);
        u.setPassword(password);
        u.setSpecialty(specialty);

        hn.addUser(u);

        pacientMenu();
    }

    private static void login() {
        resetChoice();
        printSeparator();
        System.out.println("Login\n");

        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            //TODO: show as *******
            String password = scanner.nextLine();

            for (Object o : hn.getUsers()) {
                User tmp = (User) o;

                if (username.equals(tmp.getUsername())) {
                    tmp.login(username, password);
                    if (tmp.getLoggedIn()){
                        loggedInUser = (int) tmp.getId();
                        user = tmp;
                        break;
                    }
                }
            }

            if (loggedInUser == -1) {
                System.out.println("Wrong Credentials\n");
            }
        }
        while (loggedInUser == -1);

        loggedInMenu();

    }

    private static void createHospital() {
        resetChoice();
        printSeparator();
        System.out.println("Hospital added. ID := " + nrHospitals + "\n");
        //TODO: save to file
        Hospital h = new Hospital(nrHospitals++);
        hn.addHospital(h);

        addDoctor(h, "hospitalsMenu");
    }

    private static void editHospitals() {
        resetChoice();
        printSeparator();
        System.out.println("Hospitals\n");

        do {
            selectHospitals();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            hospitalsMenu();
        }
        else {
            for (Object o : hn.getHospitals()) {
                Hospital h = (Hospital) o;

                if ((int) h.getId() == choice) {
                    editHospital(h);
                }
            }
        }
    }

    private static void exploreHospitals() {
        resetChoice();
        printSeparator();
        System.out.println("Hospitals\n");

        do {
            displayHospitals();
        }
        while (parseInput() != 0);

        hospitalsMenu();
    }

    private static void createDoctor() {
        resetChoice();
        printSeparator();
        System.out.println("New Doctor\n");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: save to file
        Doctor d = new Doctor(nrDocs++);
        d.setName(name);
        d.setSpecialty(specialty);

        hn.addDoctor(d);

        doctorsMenu();
    }

    private static void editDoctors() {
        resetChoice();
        printSeparator();
        System.out.println("Doctors\n");

        do {
            selectDoctors();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            doctorsMenu();
        }
        else {
            for (Object o : hn.getDoctors()) {
                Doctor d = (Doctor) o;

                if ((int) d.getId() == choice) {
                    editDoctor(d);
                }
            }
        }
    }

    private static void exploreDoctors() {
        resetChoice();
        printSeparator();
        System.out.println("Doctors\n");

        do {
            displayDoctors();
        }
        while (parseInput() != 0);

        doctorsMenu();
    }

    private static void loggedInMenu() {
        resetChoice();
        printSeparator();
        System.out.println("Pacient Menu\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Choose Doctor", 1);
            shortMenuEntry("Change Specialty", 2);
            shortMenuEntry("Explore", 3);
            System.out.println();
            shortMenuEntry("Logout", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                loggedInUser = -1;
                mainMenu();
                break;
            case 1:
                chooseDoctor();
                break;
            case 2:
                editSpecialty();
                break;
            case 3:
                displayDoctors(user);
                break;
            default:
                break;
        }
    }

    private static void selectDoctors(Hospital h, String context) {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getDoctors().iterator();
        int counter = 0;

        while (it.hasNext()) {
            Doctor tmp = (Doctor) it.next();

            if (!hn.docInHospital(tmp, h)){
                //TODO: maybe display name instead of id
                shortMenuEntry("Doctor" + tmp.getId(), (int) tmp.getId());
                counter++;
            }
        }

        if (choice == 0) {
            switch(context) {
                case "editHospitals":
                    editHospitals();
                    break;
                case "hospitalsMenu":
                    hospitalsMenu();
                    break;
                case "chooseDoctor":
                    chooseDoctor();
                    break;
            }
            return;
        }

        shortMenuEntry("Finish", 0);
    }

    private static void selectDoctors() {
        resetChoice();
        //TODO: order is reversed
        for (Object o : hn.getDoctors()) {
            Doctor tmp = (Doctor) o;
            //TODO: maybe display name instead of id
            shortMenuEntry("Doctor" + tmp.getId(), (int) tmp.getId());
        }

        shortMenuEntry("Finish", 0);
    }

    private static void displayDoctors(Hospital h, String context) {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getDoctors().iterator();
        int counter = 0;

        while (it.hasNext()) {
            Doctor tmp = (Doctor) it.next();

            if (!hn.docInHospital(tmp, h)){
                longMenuEntry(tmp.toString(), (int) tmp.getId());
                counter++;
            }
        }

        if (choice == 0) {
            if (context.equals("editHospitals")){
                editHospitals();
            }
            else if (context.equals("hospitalsMenu")){
                hospitalsMenu();
            }
            return;
        }

        longMenuEntry("Finish", 0);
    }

    private static void displayDoctors(User u) {
        //TODO: order is reversed
        for (Object o : hn.getDoctors()) {
            Doctor d = (Doctor) o;

            for (Object ob : hn.getUsers()) {
                User tmp = (User) ob;

                if (tmp.getId().equals(u.getId())) {
                    System.out.println("Doctor " + d.getId());
                    break;
                }
            }
        }

        parseInput();

        System.out.println("Click any number to exit");
    }

    private static void displayDoctors() {
        resetChoice();
        //TODO: order is reversed
        for (Object o : hn.getDoctors()) {
            Doctor tmp = (Doctor) o;
            longMenuEntry(tmp.toString(), (int) tmp.getId());
        }

        longMenuEntry("Click any number to exit", 0);
    }

    public static void displaySpecialties(Hospital h) {
        resetChoice();
        //TODO: order is reversed
        Iterator it = h.getSpecialties().iterator();
        int counter = 0;

        while (it.hasNext()) {
            String tmp = (String) it.next();

            System.out.println(tmp);
            counter++;
        }

        if (choice == 0) {
            hospitalsMenu();
            return;
        }

        longMenuEntry("Back", 0);
    }

    private static void selectHospitals() {
        resetChoice();
        //TODO: order is reversed
        for (Object o : hn.getHospitals()) {
            Hospital tmp = (Hospital) o;
            shortMenuEntry("Hospital " + tmp.getId(), (int) tmp.getId());
        }

        shortMenuEntry("Back", 0);
    }

    private static void displayHospitals() {
        resetChoice();
        //TODO: order is reversed
        for (Object o : hn.getHospitals()) {
            Hospital tmp = (Hospital) o;
            longMenuEntry(tmp.toString(), (int) tmp.getId());
        }

        longMenuEntry("Click any number to exit", 0);
    }

    private static void editHospital(Hospital h) {
        resetChoice();
        printSeparator();
        System.out.println(h);

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Add Doctor", 1);
            shortMenuEntry("Add Subsystem", 2);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                editHospitals();
                break;
            case 1:
                addDoctor(h, "editHospitals");
                break;
            case 2:
                //TODO: addSubsystem();
                break;
            default:
                break;
        }
    }

    private static void editDoctor(Doctor d) {
        resetChoice();
        printSeparator();
        System.out.println(d);

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Change Name", 1);
            shortMenuEntry("Change Specialty", 2);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                editDoctors();
                break;
            case 1:
                changeDocName(d);
                break;
            case 2:
                changeDocSpecialty(d);
                break;
            default:
                break;
        }

        editDoctors();
    }

    private static void addDoctor(Hospital h, String context) {
        resetChoice();
        printSeparator();
        System.out.println("Choose Doctors:\n");

        while (true) {
            do {
                displayDoctors(h, context);
            }
            while (parseInput() != 0);

            if (choice == 0) {
                if (context.equals("editHospitals")){
                    editHospitals();
                }
                else if (context.equals("hospitalsMenu")){
                    hospitalsMenu();
                }
                return;
            }
            else {
                //TODO: add only doctors with valid ids
                for (Object o : hn.getDoctors()) {
                    Doctor d = (Doctor) o;

                    if ((int) d.getId() == choice) {
                        h.addDoctor(d);
                        h.addSpecialty(d.getSpecialty());
                    }
                }
            }
        }
    }

    private static void changeDocName(Doctor d) {
        resetChoice();
        printSeparator();

        System.out.print("New Name: ");
        String name = scanner.nextLine();

        //TODO: save to file
        d.setName(name);

        editDoctors();
    }

    private static void changeDocSpecialty(Doctor d) {
        resetChoice();
        printSeparator();

        System.out.print("New Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: save to file
        d.setSpecialty(specialty);
        //TODO: add specialty to hospitals where doctor works

        editDoctors();
    }

    private static void chooseDoctor() {
        resetChoice();
        printSeparator();
        System.out.println("Choose Doctor\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("By Name", 1);
            shortMenuEntry("By Hospital", 2);
            shortMenuEntry("By Specialty", 3);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                loggedInMenu();
                break;
            case 1:
                docByName();
                break;
            case 2:
                docByHospital();
                break;
            case 3:
                docBySpecialty();
                break;
            default:
                break;
        }
    }

    private static void docByName() {
        resetChoice();
        printSeparator();
        System.out.println("Choose Doctor\n");

        do {
            selectDoctors();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            chooseDoctor();
        }
        else {
            for (Object o : hn.getDoctors()) {
                Doctor d = (Doctor) o;

                if ((int) d.getId() == choice) {
                    if (!d.getUsers().contains(user)) {//TODO: test this
                        d.addUser(user);
                    }
                    chooseDoctor();
                    return;
                }
            }
        }
    }

    private static void docByHospital() {
        resetChoice();
        printSeparator();
        System.out.println("Choose Hospital\n");

        do {
            selectHospitals();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            chooseDoctor();
        }
        else {
            for (Object o : hn.getHospitals()) {
                Hospital h = (Hospital) o;

                if ((int) h.getId() == choice) {
                    do {
                        selectDoctors(h, "chooseDoctor");
                    }
                    while (parseInput() != 0);

                    if (choice == 0) {
                        chooseDoctor();
                        return;
                    }
                    else {
                        for (Object ob : hn.getDoctors()) {
                            Doctor tmp = (Doctor) ob;
                            if ((int) tmp.getId() == choice) {
                                if (!tmp.getUsers().contains(user)) {//TODO: test this
                                    tmp.addUser(user);
                                }
                                chooseDoctor();
                                return;
                            }
                        }
                    }

                }
            }
        }
    }

    private static void docBySpecialty() {
        resetChoice();
        printSeparator();
        String specialty = user.getSpecialty();
        System.out.println("By specialty: " + specialty + "\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            shortMenuEntry("Normal Appointment", 1);
            shortMenuEntry("Emergency Appointment", 2);
            System.out.println();
            shortMenuEntry("Back", 0);
        }
        while (parseInput() != 0);

        switch (choice) {
            case 0:
                chooseDoctor();
                break;
            case 1:
                specDoc(specialty);
                break;
            case 2:
                Doctor d = hn.getMinWaitDoc(specialty);
                d.addUser(user);
                //TODO: maybe display name instead of id
                System.out.println("Appointment scheduled with Doctor " + d.getId());
                chooseDoctor();
                break;
            default:
                break;
        }


    }

    private static void specDoc(String specialty) {
        VDMSet specDocs = hn.getSpecialtyDoctors(specialty);

        do {
            resetChoice();
            printSeparator();
            for (Object o : specDocs) {
                Doctor tmp = (Doctor) o;
                //TODO: maybe display name instead of id
                shortMenuEntry("Doctor" + tmp.getId(), (int) tmp.getId());
            }

            shortMenuEntry("Finish", 0);
        }
        while (parseInput() != 0);

        if (choice == 0) {
            chooseDoctor();
        }
        else {
            for (Object o : specDocs) {
                Doctor d = (Doctor) o;

                if ((int) d.getId() == choice) {
                    d.addUser(user);
                }
            }
        }

    }

    private static void editSpecialty() {
        resetChoice();
        printSeparator();

        System.out.print("New Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: save to file
        for (Object o: hn.getUsers()) {
            User u = (User) o;

            if (u == user) {
                u.setSpecialty(specialty);
            }
        }

        editDoctors();
    }
}
