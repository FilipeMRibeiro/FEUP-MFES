import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int choice = -1;
    private static int loggedInUser = -1;

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Hospital> hospitals = new ArrayList<>();
    private static Hospitals_net hn = new Hospitals_net();

    public static void main(String[] args){
        mainMenu();
    }

    public static void initDB(){
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

    public static void menuEntry(String text, int option) {
        System.out.println(String.format("%-25s- %s" , text, Integer.toString(option) ));
    }

    public static void mainMenu(){
        resetChoice();
        printSeparator();
        System.out.println("Main Menu\n");

        do {
            if (choice > 0 && choice < 2) {
                break;
            }

            menuEntry("Administator Menu", 1);
            menuEntry("Pacient Menu", 2);
            System.out.println();
            menuEntry("Exit", 0);
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

            menuEntry("Hospitals Menu", 1);
            menuEntry("Doctors Menu", 2);
            System.out.println();
            menuEntry("Exit", 0);
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

            menuEntry("Register", 1);
            menuEntry("Login", 2);
            System.out.println();
            menuEntry("Exit", 0);
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

            menuEntry("Add Hospital", 1);
            menuEntry("Edit Hospital", 2);
            menuEntry("Explore", 3);
            System.out.println();
            menuEntry("Exit", 0);
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
                editHospital();
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

            menuEntry("Add Doctor", 1);
            menuEntry("Edit Doctor", 2);
            menuEntry("Explore", 3);
            System.out.println();
            menuEntry("Exit", 0);
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
                editDoctor();
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
        User u = new User(users.size());
        u.setName(name);
        u.setUsername(username);
        u.setPassword(password);
        u.setSpecialty(specialty);

        users.add(u);

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

            for (int i = 0; i < users.size(); i++) {
                if (username.equals(users.get(i).getUsername())) {
                    users.get(i).login(username, password);
                    if (users.get(i).getLoggedIn()){
                        loggedInUser = (int) users.get(i).getId();
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

    public static void createHospital() {
        //TODO: form and add to arraylist
    }

    public static void editHospital() {
        //TODO: form to choose hospital by id and change element(s) in arraylist
    }

    public static void exploreHospitals() {
        //TODO: display hospitals by id, select hospital and display doctors or specialties
    }

    public static void createDoctor() {
        //TODO: form and add to arraylist
    }

    public static void editDoctor() {
        //TODO: form to choose doctor by id and change element(s) in arraylist
    }

    public static void exploreDoctors() {
        //TODO: display doctors by id, select doctor and display users or specialty
    }

    public static void loggedInMenu() {
        //TODO: choose doctor -> by name, hospital or specialty; list of my doctors
    }
}
