import javax.print.Doc;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int choice = -1;
    private static int loggedInUser = -1;
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

    public static void initDB(){
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

    public static int parseInput() {
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

    public static void printSeparator() {
        System.out.println("\n#################################################");
    }

    public static void resetChoice() {
        choice = -1;
    }

    public static void shortMenuEntry(String text, int option) {
        System.out.println(String.format("%-25s- %s" , text, Integer.toString(option) ));
    }

    public static void longMenuEntry(String text, int option) {
        System.out.println(String.format("%-80s- %s" , text, Integer.toString(option) ));
    }

    public static void mainMenu(){
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

    public static void adminMenu(){
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

    public static void pacientMenu(){
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

    public static void hospitalsMenu() {
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

    public static void doctorsMenu() {
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

    public static void register() {
        resetChoice();
        printSeparator();
        System.out.println("Register\n");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: save to file
        User u = new User(nrUsers++);
        u.setName(name);
        u.setUsername(username);
        u.setPassword(password);
        u.setSpecialty(specialty);

        hn.addUser(u);

        pacientMenu();
    }

    public static void login() {
        resetChoice();
        printSeparator();
        System.out.println("Login\n");

        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            Iterator it = hn.getUsers().iterator();
            while (it.hasNext()) {
                User tmp = (User) it.next();

                if (username.equals(tmp.getUsername())) {
                    tmp.login(username, password);
                    if (tmp.getLoggedIn()){
                        loggedInUser = (int) tmp.getId();
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

    public static void logout() {
        //TODO: logout
    }

    public static void createHospital() {
        resetChoice();
        printSeparator();
        System.out.println("Hospital added. ID := " + nrHospitals + "\n");
        //TODO: save to file
        Hospital h = new Hospital(nrHospitals++);
        hn.addHospital(h);

        addDoctor(h, "hospitalsMenu");
    }

    public static void editHospitals() {
        resetChoice();
        printSeparator();
        System.out.println("Hospitals\n");

        do {
            selectHospitals();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            hospitalsMenu();
            return;
        }
        else {
            Iterator it = hn.getHospitals().iterator();
            while (it.hasNext()) {
                Hospital h = (Hospital) it.next();

                if ((int) h.getId() == choice) {
                    editHospital(h);
                }
            }
        }
    }

    public static void exploreHospitals() {
        resetChoice();
        printSeparator();
        System.out.println("Hospitals\n");

        do {
            displayHospitals();
        }
        while (parseInput() != 0);

        hospitalsMenu();
    }

    public static void createDoctor() {
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

    public static void editDoctors() {
        resetChoice();
        printSeparator();
        System.out.println("Doctors\n");

        do {
            selectDoctors();
        }
        while (parseInput() != 0);

        if (choice == 0) {
            doctorsMenu();
            return;
        }
        else {
            Iterator it = hn.getDoctors().iterator();
            while (it.hasNext()) {
                Doctor d = (Doctor) it.next();

                if ((int) d.getId() == choice) {
                    editDoctor(d);
                }
            }
        }
    }

    public static void exploreDoctors() {
        resetChoice();
        printSeparator();
        System.out.println("Doctors\n");

        do {
            displayDoctors();
        }
        while (parseInput() != 0);

        doctorsMenu();
    }

    public static void loggedInMenu() {
        //TODO: choose doctor -> by name, hospital or specialty; list of my doctors, list of my hospitals (?)
    }

    public static void selectDoctors(Hospital h, String context) {
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

    public static void selectDoctors() {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getDoctors().iterator();

        while (it.hasNext()) {
            Doctor tmp = (Doctor) it.next();
            //TODO: maybe display name instead of id
            shortMenuEntry("Doctor" + tmp.getId(), (int) tmp.getId());
        }

        shortMenuEntry("Finish", 0);
    }

    public static void displayDoctors(Hospital h, String context) {
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

    public static void displayDoctors() {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getDoctors().iterator();

        while (it.hasNext()) {
            Doctor tmp = (Doctor) it.next();
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

    public static void selectHospitals() {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getHospitals().iterator();

        while (it.hasNext()) {
            Hospital tmp = (Hospital) it.next();
            shortMenuEntry("Hospital " + tmp.getId(), (int) tmp.getId());
        }

        shortMenuEntry("Back", 0);
    }

    public static void displayHospitals() {
        resetChoice();
        //TODO: order is reversed
        Iterator it = hn.getHospitals().iterator();

        while (it.hasNext()) {
            Hospital tmp = (Hospital) it.next();
            longMenuEntry(tmp.toString(), (int) tmp.getId());
        }

        longMenuEntry("Click any number to exit", 0);
    }

    public static void editHospital(Hospital h) {
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

    public static void editDoctor(Doctor d) {
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

    public static void addDoctor(Hospital h, String context) {
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
                Iterator it = hn.getDoctors().iterator();
                while (it.hasNext()) {
                    Doctor d = (Doctor) it.next();

                    if ((int) d.getId() == choice) {
                        h.addDoctor(d);
                        h.addSpecialty(d.getSpecialty());
                    }
                }
            }
        }
    }

    public static void changeDocName(Doctor d) {
        resetChoice();
        printSeparator();

        System.out.print("New Name: ");
        String name = scanner.nextLine();

        //TODO: save to file
        d.setName(name);

        editDoctors();
    }

    public static void changeDocSpecialty(Doctor d) {
        resetChoice();
        printSeparator();

        System.out.print("New Specialty: ");
        String specialty = scanner.nextLine();

        //TODO: save to file
        d.setSpecialty(specialty);

        editDoctors();
    }
}
