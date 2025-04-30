
import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class mainApp {
    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        File AllergensFile = new File("Allergens.txt");
        Scanner Allergens = new Scanner(AllergensFile);

        File UsersFile = new File("Users.txt");
        Scanner Users = new Scanner(UsersFile);

        File Users2File = new File("Users2.txt");
        Scanner Users2 = new Scanner(Users2File);


        MedicalProfessional doctor = new MedicalProfessional("Dr.Smith", "LIC123");
        doctor.setAssignedUsers(new ArrayList<>());
        doctor.setKnownAllergens(new ArrayList<>());

        MedicalProfessional doctor2 = new MedicalProfessional("Dr.Baxter", "LIC321");
        doctor2.setAssignedUsers(new ArrayList<>());
        doctor2.setKnownAllergens(new ArrayList<>());


        while (Allergens.hasNext()) {
            String name = Allergens.next();
            int id = Allergens.nextInt();
            int severity = Allergens.nextInt();
            ArrayList<String> symptoms = new ArrayList<>();

            String next;
            while (true) {
                next = Allergens.next();
                if (next.equals("end")) {
                    break;
                }
                symptoms.add(next);
            }
            Allergen allergy = new Allergen(name, id, severity, symptoms);
            doctor.addKnownAllergen(allergy);
            doctor2.addKnownAllergen(allergy);

        }

        while (Users.hasNext()) {

            int id = Users.nextInt();
            String name = Users.next();
            int age = Users.nextInt();
            ArrayList<String> symptoms = new ArrayList<>();

            String next;
            while (true) {
                next = Users.next();
                if (next.equals("end")) {
                    break;
                }
                symptoms.add(next);
            }
            User user = new User(id, name, age, symptoms);
            doctor.assignUser(user);
        }

        while (Users2.hasNext()) {

            int id2 = Users2.nextInt();
            String name2 = Users2.next();
            int age2 = Users2.nextInt();
            ArrayList<String> symptoms2 = new ArrayList<>();

            String next;
            while (true) {
                next = Users2.next();
                if (next.equals("end")) {
                    break;
                }
                symptoms2.add(next);
            }
            User user2 = new User(id2, name2, age2, symptoms2);
            doctor2.assignUser(user2);
        }
while(true){
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║   WELCOME TO MEDICAL MOODLE  ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Doctor Login              ║");
        System.out.println("║ 2. Patient Portal            ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ Enter your choice (1-2):     ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Choose: ");

        int choice = keyboard.nextInt();
        while (choice <= 0 || choice >= 3) {
            System.out.print("Invalid choice. Please enter 1-3: ");
            choice = keyboard.nextInt();
        }
        if (choice == 1) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        DOCTOR LOGIN          ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║          Enter ID:           ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Enter your license ID: ");

            String doctorLicense = keyboard.next();
            while (!doctorLicense.equals("LIC123") && !doctorLicense.equals("LIC321")) {
                System.out.print("Invalid choice. Please enter valid ID: ");
                doctorLicense = keyboard.next();
            }
            MedicalProfessional medicalProfessional;
            if (doctorLicense.equals("LIC123")) medicalProfessional = doctor;
            else medicalProfessional = doctor2;

            while (true)
             {

                    medicalProfessional.displayDoctorMenu();

                    System.out.print("Choose: ");

                    choice = keyboard.nextInt();
                    while (choice <= 0 || choice >= 14) {
                        System.out.print("Invalid choice. Please enter 1-13: ");
                        choice = keyboard.nextInt();
                    }

                    if (choice == 1) {

                        System.out.print("Enter patient ID: ");
                        int id = keyboard.nextInt();
                        keyboard.nextLine();
                        while (medicalProfessional.getAssignedUsersID().contains(id)) {
                            System.out.print("Patient ID exists, please enter a new ID: ");
                            id = keyboard.nextInt();
                        }
                        System.out.print("Enter name: ");
                        String name = keyboard.nextLine();

                        System.out.print("Enter Age: ");
                        int age = keyboard.nextInt();

                        User patient = new User(id, name, age);
                        patient.setSymptoms(new ArrayList<>());
                        medicalProfessional.assignUser(patient);
                        System.out.println("Patient added");
                    } else if (choice == 2) {
                        medicalProfessional.printAllUsers();
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║           DELETE MENU        ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Delete user               ║");
                        System.out.println("║ 2. Delete user if no symptoms║");
                        System.out.println("║ 3. Delete all users          ║");
                        System.out.println("║ 4. Back to main menu         ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ Enter your choice (1-2):     ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Choose: ");

                        choice = keyboard.nextInt();
                        while (choice <= 0 || choice >= 5) {
                            System.out.print("Invalid choice. Please enter 1-5: ");
                            choice = keyboard.nextInt();
                        }
                        if(choice == 1)
                        {
                            System.out.print("Enter patient ID to remove: ");
                            int id = keyboard.nextInt();
                            ArrayList<User> users = medicalProfessional.getAssignedUsers();
                            boolean userFound = false;

                            for (int i = 0; i < users.size(); i++) {
                                if (users.get(i).getUserId() == id) {
                                    medicalProfessional.removeUser(i);
                                    userFound = true;
                                }
                            }
                            while (!userFound) {
                                System.out.println("Patient not found");
                                System.out.print("Enter patient ID to remove: ");
                                id = keyboard.nextInt();
                                userFound = medicalProfessional.removePatient(id);
                            }
                        } else if (choice == 2) {
                            medicalProfessional.deleteIfSymptomEmpty();
                        } else if (choice == 3) {
                            medicalProfessional.removeAllUsers();
                        }

                    } else if (choice == 3) {
                        medicalProfessional.printAllUsers();
                        System.out.print("Enter patient ID: ");
                        int id = keyboard.nextInt();
                        keyboard.nextLine();
                        while (!medicalProfessional.getAssignedUsersID().contains(id)) {
                            System.out.print("Patient ID doesnt exists, please enter a proper ID: ");
                            id = keyboard.nextInt();
                        }

                        ArrayList<User> users = medicalProfessional.getAssignedUsers();
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getUserId() == id) {
                                System.out.println("Enter symptoms (one per entry, enter 'done' when finished):");

                                while (true) {
                                    String symptom = keyboard.next();

                                    while (!medicalProfessional.getAllergensSymptoms().contains(symptom)) {
                                        if (symptom.equals("done")) {
                                            break;
                                        }
                                        System.out.println("Invalid symptom. Please enter an existing symtom.");
                                        symptom = keyboard.next();

                                    }
                                    if (symptom.equals("done")) {
                                        break;
                                    }
                                    users.get(i).logSymptom(symptom);
                                }


                            }
                        }
                    } else if (choice == 4) {
                        medicalProfessional.printAllUsers();
                        System.out.print("Enter patient ID: ");
                        int id = keyboard.nextInt();
                        while (!medicalProfessional.getAssignedUsersID().contains(id)) {
                            System.out.print("Patient ID doesnt exists, please enter a proper ID: ");
                            id = keyboard.nextInt();
                        }
                        ArrayList<User> users = medicalProfessional.getAssignedUsers();
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getUserId() == id) {
                                ArrayList<Allergen> results = medicalProfessional.diagnoseUser(users.get(i));
                                System.out.println("");
                                System.out.print("Symptoms:");
                                System.out.print(users.get(i).getSymptoms());

                                System.out.println("\nPossible allergies:");

                                System.out.printf("%-22s %-10s %-50s%n", "Name", "Severity", "Symptoms");
                                for (int j = 0; j < results.size(); j++) {
                                    System.out.printf("%-20s %-10s %-50s%n",
                                            results.get(j).getName(),
                                            results.get(j).getSeverity(),
                                            results.get(j).getSymptoms());
                                }
                                System.out.println("");
                                break;
                            }
                        }
                    } else if (choice == 5) {
                        System.out.print("Enter allergen name: ");
                        String name = keyboard.nextLine();

                        System.out.print("Enter allergen ID: ");
                        int id = keyboard.nextInt();
                        while (medicalProfessional.getAllergensID().contains(id)) {
                            System.out.print("Allergen ID exists, please enter a new ID: ");
                            id = keyboard.nextInt();
                        }

                        int severity;
                        System.out.print("Enter severity (1-10): ");
                        severity = keyboard.nextInt();

                        while (severity <= 1 || severity >= 10) {
                            System.out.println("Invalid severity. Please enter a number between 1 and 10.");
                            System.out.print("Enter severity (1-10): ");
                            severity = keyboard.nextInt();
                        }

                        ArrayList<String> symptoms = new ArrayList<>();
                        System.out.println("Enter symptoms (one per line, enter 'done' when finished):");
                        while (true) {
                            String symptom = keyboard.next();
                            while (!medicalProfessional.getAllergensSymptoms().contains(symptom)) {
                                if (symptom.equals("done")) {
                                    break;
                                }
                                System.out.println("Invalid symptom. Please enter an existing symtom.");
                                symptom = keyboard.next();
                            }
                            if (symptom.equals("done")) {
                                break;
                            }
                            symptoms.add(symptom);
                        }
                        Allergen newAllergen = new Allergen(name, id, severity, symptoms);
                        medicalProfessional.addKnownAllergen(newAllergen);
                        System.out.println("New allergen added successfully!");
                    } else if (choice == 6) {
                        medicalProfessional.printKnownAllergens();


                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║           DELETE MENU        ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Delete Allergen           ║");
                        System.out.println("║ 2.Delete Allergen by severity║");
                        System.out.println("║ 3. Delete all Allergens      ║");
                        System.out.println("║ 4. Back to main menu         ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ Enter your choice (1-2):     ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Choose: ");

                        choice = keyboard.nextInt();
                        while (choice <= 0 || choice >= 5) {
                            System.out.print("Invalid choice. Please enter 1-5: ");
                            choice = keyboard.nextInt();
                        }
                        if(choice == 1)
                        {
                            System.out.print("Enter allergen ID to remove: ");
                            int id = keyboard.nextInt();

                            while (!medicalProfessional.getAllergensID().contains(id)) {
                                System.out.print("Allergen ID doesnt exists, please enter a proper ID: ");
                                id = keyboard.nextInt();
                                keyboard.next();
                            }

                            medicalProfessional.removeAllergen(id);
                            System.out.println("Allergen removed successfully");
                        } else if (choice == 2) {
                            System.out.println("Enter severity: ");
                            int severity = keyboard.nextInt();
                            medicalProfessional.deleteBySeverity(severity);
                        } else if (choice == 3) {
                            medicalProfessional.removeAllAllergens();
                        }
                    } else if (choice == 7) {
                        medicalProfessional.printAllUsers();
                        int count = medicalProfessional.countTotalUsers();
                        System.out.println(medicalProfessional.getName() + " has " + count + " patients");
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║           VIEW MENU          ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Count users under an age  ║");
                        System.out.println("║ 2. Back to main menu         ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ Enter your choice (1-2):     ║");
                        System.out.println("╚══════════════════════════════╝");                        choice = keyboard.nextInt();
                        if(choice == 1)
                        {
                            System.out.print("Enter age: ");
                            int age = keyboard.nextInt();

                            System.out.println(medicalProfessional.countByTotal(age)+" pateints below "+ age);
                        }
                    } else if (choice == 8) {
                        medicalProfessional.printKnownAllergens();
                        int count = medicalProfessional.countTotalAllergens();
                        System.out.println(medicalProfessional.getName() + " manages " + count + " Allergens");
                        System.out.println("\n╔════════════════════════════════════╗");
                        System.out.println("║           VIEW MENU                ║");
                        System.out.println("╠════════════════════════════════════╣");
                        System.out.println("║ 1.Count allergen between a severity║");
                        System.out.println("║ 2.Back to main menu                ║");
                        System.out.println("╠════════════════════════════════════╣");
                        System.out.println("║ Enter your choice (1-2):           ║");
                        System.out.println("╚════════════════════════════════════╝");
                        choice = keyboard.nextInt();
                        if(choice == 1)
                        {
                            System.out.print("Enter min: ");
                            int min = keyboard.nextInt();
                            System.out.print("Enter max: ");
                            int max = keyboard.nextInt();

                            System.out.println(medicalProfessional.countByTotal(min,max)+" allergens between "+ min+", "+max);

                        }                    }
                    else if (choice == 9) {
                        System.out.print("Enter severity(1-10): ");
                        int severity = keyboard.nextInt();
                        while (severity > 11 || severity < 0) {
                            System.out.println("Invalid severity ");
                            System.out.print("Enter severity(1-10): ");
                            severity = keyboard.nextInt();
                        }
                        medicalProfessional.printKnownAllergens(severity);
                    } else if (choice == 10) {
                        System.out.print("Enter allergen name to search: ");
                        String name = keyboard.next();

                        ArrayList<Allergen> results = medicalProfessional.searchAllergenByName(name);

                        medicalProfessional.printSearchAllergenByName(results);
                    } else if (choice == 11) {
                        System.out.println("\n╔════════════════════════════════════╗");
                        System.out.println("║        STATISTICS MENU             ║");
                        System.out.println("╠════════════════════════════════════╣");
                        System.out.println("║ 1. Most common symptom             ║");
                        System.out.println("║ 2. Count users under an age        ║");
                        System.out.println("║ 3.Count allergen between a severity║");
                        System.out.println("║ 4. Back to main menu               ║");
                        System.out.println("╠════════════════════════════════════╣");
                        System.out.println("║ Enter your choice (1-4):           ║");
                        System.out.println("╚════════════════════════════════════╝");

                        System.out.print("Choose: ");

                        choice = keyboard.nextInt();
                        keyboard.nextLine();
                        while (choice <= 0 || choice >= 5) {
                            System.out.print("Invalid choice. Please enter 1-3: ");
                            choice = keyboard.nextInt();
                        }

                        if (choice == 1) {
                            System.out.println(medicalProfessional.getMostCommonSymptom());
                        } else if (choice == 2) {
                            int count = medicalProfessional.countTotalUsers();
                            System.out.println(medicalProfessional.getName() + " has " + count + " patients");
                        }
                        else if(choice ==3){
                            int count = medicalProfessional.countTotalAllergens();
                            System.out.println(medicalProfessional.getName() + " manages " + count + " Allergens");
                        }
                        else{

                        }

                    } else if (choice == 12) {
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║       SORT ALLERGENS BY      ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Severity (High to Low)    ║");
                        System.out.println("║ 2. User ID (Low to High)     ║");
                        System.out.println("║ 3. Back to main menu         ║");
                        System.out.println("╚══════════════════════════════╝");

                        System.out.print("Choose: ");

                        choice = keyboard.nextInt();
                        while (choice <= 0 || choice >= 3) {
                            System.out.print("Invalid choice. Please enter 1-2: ");
                            choice = keyboard.nextInt();
                        }

                        if (choice == 1) {
                            medicalProfessional.sortBySeverity();
                        } else if (choice == 2) {
                            medicalProfessional.sortByUserID();
                        } else if (choice == 3) {
                        }

                    }
                    else if (choice == 13) {
                        break;
                    }

            }


        } else {
            while(true) {
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║        PATIENT PORTAL        ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║ 1. Register                  ║");
                System.out.println("║ 2. Login                     ║");
                System.out.println("║ 3. Back                      ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║ Enter your choice (1-5):     ║");
                System.out.println("╚══════════════════════════════╝");

                System.out.print("Choose: ");

                choice = keyboard.nextInt();
                while (choice <= 0 || choice >= 4) {
                    System.out.print("Invalid choice. Please enter 1-4: ");
                    choice = keyboard.nextInt();
                }
                User patient = null;
                if (choice == 1) {
                    System.out.print("Enter your name: ");
                    String name = keyboard.next();

                    System.out.print("Enter Age: ");
                    int age = keyboard.nextInt();

                    Random rand = new Random();
                    int randDoctor = rand.nextInt(1, 3);
                    MedicalProfessional assignedDoctor;
                    if (randDoctor == 1) assignedDoctor = doctor;
                    else assignedDoctor = doctor2;

                    int id = assignedDoctor.getAssignedUsersID().getLast() + 1;


                    patient = new User(id, name, age);

                    ArrayList<String> symptoms = new ArrayList<>();
                    System.out.println("If you are experiencing any symptoms, enter symptoms (one per line, enter 'done' when finished):");
                    while (true) {
                        String symptom = keyboard.next();
                        while (!assignedDoctor.getAllergensSymptoms().contains(symptom)) {
                            if (symptom.equals("done")) {
                                break;
                            }
                            System.out.println("Invalid symptom. Please enter an existing symptom.");
                            symptom = keyboard.next();
                        }
                        if (symptom.equals("done")) {
                            break;
                        }
                        symptoms.add(symptom);
                    }
                    patient.setSymptoms(symptoms);

                    assignedDoctor.assignUser(patient);
                    System.out.println("You will be assigned to " + assignedDoctor.getName());

                } else if(choice == 2) {

                    System.out.println();
                    System.out.print("Enter doctors name: ");
                    String name = keyboard.next();

                    while (!name.equals("Dr.Smith") && !name.equals("Dr.Baxter")) {
                        System.out.print("Invalid choice. Please enter valid Doctor: ");
                        name = keyboard.nextLine();
                    }
                    MedicalProfessional assignedDoctor;

                    if (name.equals("Dr.Smith")) assignedDoctor = doctor;
                    else assignedDoctor = doctor2;

                    System.out.print("Enter ID: ");
                    int id = keyboard.nextInt();
                    while (!assignedDoctor.getAssignedUsersID().contains(id)) {
                        System.out.print("Patient ID doesnt exists, please enter a proper ID: ");
                        id = keyboard.nextInt();
                    }


                    for (int i = 0; i < assignedDoctor.getAssignedUsers().size(); i++) {
                        if (assignedDoctor.getAssignedUsers().get(i).getUserId() == id) {
                            patient = assignedDoctor.getAssignedUsers().get(i);
                        }
                    }


                }
                else break;
                while (true) {
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║        PATIENT MENU          ║");
                    System.out.println("╠══════════════════════════════╣");
                    System.out.println("║ 1. View my profile           ║");
                    System.out.println("║ 2. Report symptoms           ║");
                    System.out.println("║ 3. Change my information     ║");
                    System.out.println("║ 4. Logout                    ║");
                    System.out.println("╠══════════════════════════════╣");
                    System.out.println("║ Enter your choice (1-6):     ║");
                    System.out.println("╚══════════════════════════════╝");

                    System.out.print("Choose: ");
                    choice = keyboard.nextInt();

                    if (choice == 1) {
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║        MY PROFILE            ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.println("  ID: " + patient.getUserId());
                        System.out.println("  Name: " + patient.getName());
                        System.out.println("  Age: " + patient.getAge());
                        System.out.println("  Symptoms: " + patient.getSymptoms());
                        System.out.println("════════════════════════════════");
                    } else if (choice == 2) {
                        System.out.println("Enter symptoms (one per entry, enter 'done' when finished):");

                        while (true) {
                            String symptom = keyboard.next();

                            while (!doctor.getAllergensSymptoms().contains(symptom)) {
                                if (symptom.equals("done")) {
                                    break;
                                }
                                System.out.println("Invalid symptom. Please enter an existing symtom.");
                                symptom = keyboard.next();

                            }
                            if (symptom.equals("done")) {
                                break;
                            }
                            patient.logSymptom(symptom);
                        }
                    } else if (choice == 3) {
                        System.out.println("\nWhat would you like to change?");
                        System.out.println("1. Name");
                        System.out.println("2. Age");
                        System.out.println("3. Symptoms");
                        System.out.print("Enter your choice (1-3): ");

                        choice = keyboard.nextInt();
                        if (choice == 1) {
                            System.out.print("Enter new name(second and first name seperated by '_': ");
                            String name = keyboard.next();
                            patient.setName(name);
                            System.out.println("Name updated successfully!");
                        } else if (choice == 2) {
                            System.out.print("Enter new age: ");
                            int age = keyboard.nextInt();
                            patient.setAge(age);
                            System.out.println("Age updated successfully!");
                        } else if (choice == 3) {
                            System.out.print("Enter symptom number you would like to remove/update : ");
                            System.out.println();
                            for (int i = 0; i < patient.getSymptoms().size(); i++) {
                                System.out.println(i + ". " + patient.getSymptoms().get(i));

                            }
                            int edit = keyboard.nextInt();
                            while (edit < 0 || edit >= patient.getSymptoms().size()) {
                                System.out.print("Invalid choice. Please enter a proper value: ");
                                edit = keyboard.nextInt();
                            }
                            System.out.println("Enter new value or type 'delete' to remove");
                            String symptom= "";

                                symptom = keyboard.next();

                                while (!doctor.getAllergensSymptoms().contains(symptom)) {
                                    if (symptom.equals("delete")) {
                                        break;
                                    }
                                    System.out.println("Invalid symptom. Please enter an existing symtom.");
                                    symptom = keyboard.next();

                                }

                            int indexEdit = 0;
                            for (int i = 0; i < patient.getSymptoms().size(); i++) {
                                if (edit == i) indexEdit = i;
                            }
                            if (!symptom.equals("delete")) patient.getSymptoms().set(indexEdit, symptom);
                            else patient.getSymptoms().remove(indexEdit);

                            System.out.println("Symptoms updated successfully!");
                        } else {
                            System.out.println("Invalid choice. No changes made.");
                        }
                    } else if (choice == 4) {
                        break;
                    }
                }
            }
            }

        }

    }
}


