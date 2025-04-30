
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a medical professional who can manage patients and allergens
 * This class provides functionality for managing assigned users ,
 * known allergens and various methods to change,view and modify
 */
public class MedicalProfessional {
    /** The name of the medical professional */
    private String name;

    /** The license ID of the medical professional */
    private String licenseId;

    /** List of users (patients) assigned to this medical professional */
    private ArrayList<User> assignedUsers;

    /** List of allergens known to this medical professional */
    private ArrayList<Allergen> knownAllergens;

    /**
     * Constructs a MedicalProfessional with all fields
     * @param name The name of the medical professional
     * @param licenseId The license ID of the medical professional
     * @param assignedUsers List of initially assigned users (patients)
     * @param knownAllergens List of initially known allergens
     */
    public MedicalProfessional(String name, String licenseId, ArrayList<User> assignedUsers, ArrayList<Allergen> knownAllergens) {
        this.name = name;
        this.licenseId = licenseId;
        this.assignedUsers = assignedUsers;
        this.knownAllergens = knownAllergens;
    }

    /**
     * Constructs a MedicalProfessional with only name and license ID
     * Initializes empty lists for assignedUsers and knownAllergens
     * @param name The name of the medical professional
     * @param licenseId The license ID of the medical professional
     */
    public MedicalProfessional(String name, String licenseId) {
        this.name = name;
        this.licenseId = licenseId;
        this.assignedUsers = new ArrayList<>();
        this.knownAllergens = new ArrayList<>();
    }

    /**
     * Gets the name of the medical professional
     * @return The name of the medical professional
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medical professional
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the license ID of the medical professional
     * @return The license ID
     */
    public String getLicenseId() {
        return licenseId;
    }

    /**
     * Sets the license ID of the medical professional
     * @param licenseId The new license ID to set
     */
    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    /**
     * Gets the list of assigned users
     * @return ArrayList of assigned users
     */
    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    /**
     * Sets the list of assigned users
     * @param assignedUsers The new list of assigned users
     */
    public void setAssignedUsers(ArrayList<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    /**
     * Gets the list of known allergens
     * @return ArrayList of known allergens
     */
    public ArrayList<Allergen> getKnownAllergens() {
        return knownAllergens;
    }

    /**
     * Sets the list of known allergens.
     * @param knownAllergens The new list of known allergens
     */
    public void setKnownAllergens(ArrayList<Allergen> knownAllergens) {
        this.knownAllergens = knownAllergens;
    }

    // User/Patient related methods

    /**
     * Assigns a new user (patient) to this medical professional.
     * @param user The user to assign
     */
    public void assignUser(User user) {
        assignedUsers.add(user);
    }

    /**
     * Removes a user by their index in the assigned users list.
     * @param userId The index of the user to remove
     * @return true if removal was successful, false otherwise
     */
    public boolean removeUser(int userId) {
        assignedUsers.remove(userId);
        return true;
    }

    /**
     * Removes a patient by their ID.
     * @param id The ID of the patient to remove
     * @return true if the patient was found and removed, false otherwise
     */
    public boolean removePatient(int id) {
        boolean userFound = false;
        ArrayList<User> users = this.getAssignedUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == id) {
                this.removeUser(i);
                userFound = true;
            }
        }
        return userFound;
    }

    /**
     * Removes all assigned users (patients).
     */
    public void removeAllUsers() {
        for (int i = assignedUsers.size() - 1; i >= 0; i--) {
            assignedUsers.remove(i);
        }
    }

    /**
     * Deletes users who have no symptoms
     */
    public void deleteIfSymptomEmpty() {
        for (int i = 0; i < assignedUsers.size(); i++) {
            if (assignedUsers.get(i).getSymptoms().isEmpty()) assignedUsers.remove(i);
        }
    }

    // Allergen related methods

    /**
     * Adds a new allergen to the arraylist of allergens
     * @param allergen The allergen to add
     */
    public void addKnownAllergen(Allergen allergen) {
        knownAllergens.add(allergen);
    }

    /**
     * Removes an allergen by its ID.
     * @param allergenId The ID of the allergen to remove
     */
    public void removeAllergen(int allergenId) {
        for (int i = 0; i < knownAllergens.size(); i++) {
            if (knownAllergens.get(i).getAllergenId() == allergenId) {
                knownAllergens.remove(i);
            }
        }
    }

    /**
     * Deletes allergens with a specific severity level
     * @param severity The severity level of allergens to delete
     */
    public void deleteBySeverity(int severity) {
        for (int i = 0; i < knownAllergens.size(); i++) {
            if (knownAllergens.get(i).getSeverity() == severity) knownAllergens.remove(i);
        }
    }

    /**
     * Removes all known allergens
     */
    public void removeAllAllergens() {
        for (int i = knownAllergens.size() - 1; i >= 0; i--) {
            knownAllergens.remove(i);
        }
    }

    // Printing/Display methods

    /**
     * Prints all known allergens in a formatted table
     */
    public void printKnownAllergens() {
        System.out.printf("%-20s %-10s %-10s %-50s%n", "Name", "ID", "Severity", "Symptoms");
        for (int i = 0; i < knownAllergens.size(); i++) {
            System.out.printf("%-20s %-10s %-10s %-50s%n",
                    knownAllergens.get(i).getName(),
                    knownAllergens.get(i).getAllergenId(),
                    knownAllergens.get(i).getSeverity(),
                    knownAllergens.get(i).getSymptoms());
        }
    }

    /**
     * Prints known allergens filtered by severity
     * @param severity The severity level to filter by
     */
    public void printKnownAllergens(int severity) {
        System.out.printf("\n%-20s %-10s %-10s %-50s%n", "Name", "ID", "Severity", "Symptoms");
        for (int i = 0; i < knownAllergens.size(); i++) {
            if (knownAllergens.get(i).getSeverity() == severity) {
                System.out.printf("%-20s %-10s %-10s %-50s%n",
                        knownAllergens.get(i).getName(),
                        knownAllergens.get(i).getAllergenId(),
                        knownAllergens.get(i).getSeverity(),
                        knownAllergens.get(i).getSymptoms());
            }
        }
    }

    /**
     * Prints all assigned users in a formatted table
     */
    public void printAllUsers() {
        System.out.printf("%n%-12s %-20s %-12s %-50s%n", "Patient ID", "Patient Name", "Patient Age", "Symptoms");
        for (int i = 0; i < assignedUsers.size(); i++) {
            System.out.printf("%-12s %-20s %-12s %-50s%n",
                    assignedUsers.get(i).getUserId(),
                    assignedUsers.get(i).getName(),
                    assignedUsers.get(i).getAge(),
                    assignedUsers.get(i).getSymptoms());
        }
    }

    /**
     * Prints allergen search results in a formatted table
     * @param results The list of allergens to display
     */
    public void printSearchAllergenByName(ArrayList<Allergen> results) {
        System.out.printf("%-20s %-10s %-10s %-50s%n", "Name", "ID", "Severity", "Symptoms");
        for (int i = 0; i < results.size(); i++) {
            Allergen allergen = results.get(i);
            System.out.printf("%-20s %-10s %-10s %-50s%n",
                    allergen.getName(),
                    allergen.getAllergenId(),
                    allergen.getSeverity(),
                    allergen.getSymptoms());
        }
    }

    /**
     * Displays the doctor's menu options
     */
    public void displayDoctorMenu() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        MEDICAL SYSTEM        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Add patient               ║");
        System.out.println("║ 2. Remove patient            ║");
        System.out.println("║ 3. Add symptom to patient    ║");
        System.out.println("║ 4. Diagnose Patient          ║");
        System.out.println("║ 5. Add new allergen          ║");
        System.out.println("║ 6  Remove allergen           ║");
        System.out.println("║ 7. View all patients         ║");
        System.out.println("║ 8. View all allergens        ║");
        System.out.println("║ 9. Search by severity        ║");
        System.out.println("║10. Search allergen by name   ║");
        System.out.println("║11. View statistics           ║");
        System.out.println("║12. Sort by menu              ║");
        System.out.println("║13. Logout                    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println(" Enter your choice " + this.getName());
        System.out.println("╚══════════════════════════════╝");
    }

    // Search and diagnostic methods

    /**
     * Searches for allergens by name
     * @param name The name or partial name to search for
     * @return ArrayList of matching allergens
     */
    public ArrayList<Allergen> searchAllergenByName(String name) {
        ArrayList<Allergen> results = new ArrayList<>();
        for (int i = 0; i < knownAllergens.size(); i++) {
            Allergen allergen = knownAllergens.get(i);
            if (allergen.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(allergen);
            }
        }
        return results;
    }

    /**
     * Diagnoses a user by matching their symptoms with known allergens
     * @param user The user to diagnose
     * @return ArrayList of possible allergens that match the user's symptom(s)
     */
    public ArrayList<Allergen> diagnoseUser(User user) {
        ArrayList<Allergen> possibleAllergens = new ArrayList<>();
        for (int i = 0; i < knownAllergens.size(); i++) {
            Allergen allergen = knownAllergens.get(i);
            ArrayList<String> userSymptoms = user.getSymptoms();
            for (int j = 0; j < userSymptoms.size(); j++) {
                String symptom = userSymptoms.get(j);
                if (allergen.getSymptoms().contains(symptom)) {
                    if (!possibleAllergens.contains(allergen)) {
                        possibleAllergens.add(allergen);
                    }
                }
            }
        }
        return possibleAllergens;
    }

    // ID collection methods

    /**
     * Gets a list of all assigned user IDs
     * @return ArrayList of user IDs
     */
    public ArrayList<Integer> getAssignedUsersID() {
        ArrayList<Integer> allIDS = new ArrayList<>();
        for (int i = 0; i < assignedUsers.size(); i++) {
            allIDS.add(assignedUsers.get(i).getUserId());
        }
        return allIDS;
    }

    /**
     * Gets a list of all known allergen IDs
     * @return ArrayList of allergen IDs
     */
    public ArrayList<Integer> getAllergensID() {
        ArrayList<Integer> allIDS = new ArrayList<>();
        for (int i = 0; i < knownAllergens.size(); i++) {
            allIDS.add(knownAllergens.get(i).getAllergenId());
        }
        return allIDS;
    }

    // Symptoms collection methods

    /**
     * Gets all symptoms from all known allergens
     * @return ArrayList of all symptoms
     */
    public ArrayList<String> getAllergensSymptoms() {
        ArrayList<String> allSymptoms = new ArrayList<>();
        for (int i = 0; i < knownAllergens.size(); i++) {
            for (int j = 0; j < knownAllergens.get(i).getSymptoms().size(); j++) {
                allSymptoms.add(knownAllergens.get(i).getSymptoms().get(j));
            }
        }
        return allSymptoms;
    }

    /**
     * Gets all symptoms from all assigned users
     * @return ArrayList of all patient symptoms
     */
    public ArrayList<String> getPatientSymptoms() {
        ArrayList<String> allSymptoms = new ArrayList<>();
        for (int i = 0; i < assignedUsers.size(); i++) {
            for (int j = 0; j < assignedUsers.get(i).getSymptoms().size(); j++) {
                allSymptoms.add(assignedUsers.get(i).getSymptoms().get(j));
            }
        }
        return allSymptoms;
    }

    /**
     * gets the most common symptom among all patients
     * @return String of the most common symptom(s if equal amount of more than 1 symptom) and their count
     */
    public String getMostCommonSymptom() {
        String mostCommonSymptoms = "";
        ArrayList<String> allSymptoms = getPatientSymptoms();
        ArrayList<Integer> allSymptomsCount = new ArrayList<>();

        for (int i = 0; i < allSymptoms.size(); i++) {
            String current = allSymptoms.get(i);
            int count = 0;
            for (int j = 0; j < allSymptoms.size(); j++) {
                if (current.equals(allSymptoms.get(j))) {
                    count++;
                }
            }
            allSymptomsCount.add(count);
        }

        int maxCount = 0;
        for (int i = 0; i < allSymptomsCount.size(); i++) {
            if (allSymptomsCount.get(i) > maxCount) {
                maxCount = allSymptomsCount.get(i);
            }
        }
        mostCommonSymptoms += "Count of Most common symptom(s): " + maxCount + "\nMost common symptom(s):";
        for (int i = 0; i < allSymptoms.size(); i++) {
            if (maxCount == allSymptomsCount.get(i)) {
                if (!mostCommonSymptoms.contains(allSymptoms.get(i))) {
                    mostCommonSymptoms += " " + allSymptoms.get(i);
                }
            }
        }
        return mostCommonSymptoms;
    }

    // Sorting methods

    /**
     * Sorts known allergens by severity and prints them
     */
    public void sortBySeverity() {
        Collections.sort(this.getKnownAllergens());
        this.printKnownAllergens();
    }

    /**
     * Sorts assigned users by ID and prints them
     */
    public void sortByUserID() {
        Collections.sort(this.getAssignedUsers());
        this.printAllUsers();
    }

    // Counting methods

    /**
     * Counts the total number of assigned users
     * @return The count of assigned users
     */
    public int countTotalUsers() {
        int count = this.assignedUsers.size();
        return count;
    }

    /**
     * Counts the total number of known allergens
     * @return The count of known allergens
     */
    public int countTotalAllergens() {
        int count = this.knownAllergens.size();
        return count;
    }

    /**
     * Counts and prints allergens between chosen range
     * @param min The minimum severity
     * @param max The maximum severity
     * @return The count of allergens between a chosen range
     */
    public int countByTotal(int min, int max) {
        int count = 0;
        System.out.printf("%-20s %-10s %-10s %-50s%n", "Name", "ID", "Severity", "Symptoms");
        for (int i = 0; i < knownAllergens.size(); i++) {
            if (knownAllergens.get(i).getSeverity() < max && knownAllergens.get(i).getSeverity() > min) {
                count++;
                System.out.printf("%-20s %-10s %-10s %-50s%n",
                        knownAllergens.get(i).getName(),
                        knownAllergens.get(i).getAllergenId(),
                        knownAllergens.get(i).getSeverity(),
                        knownAllergens.get(i).getSymptoms());
            }
        }
        return count;
    }

    /**
     * Counts and prints allergens below a specific severity threshold
     * @param max The maximum severity
     * @return The count of allergens below the severity
     */
    public int countByTotal(int max) {
        int count = 0;
        System.out.printf("%n%-12s %-20s %-12s %-50s%n", "Patient ID", "Patient Name", "Patient Age", "Symptoms");
        for (int i = 0; i < knownAllergens.size(); i++) {
            if (knownAllergens.get(i).getSeverity() < max) {
                count++;
                System.out.printf("%-12s %-20s %-12s %-50s%n",
                        assignedUsers.get(i).getUserId(),
                        assignedUsers.get(i).getName(),
                        assignedUsers.get(i).getAge(),
                        assignedUsers.get(i).getSymptoms());
            }
        }
        return count;
    }


    @Override
    public String toString() {
        return "MedicalProfessional{" +
                "name='" + name + '\'' +
                ", licenseId='" + licenseId + '\'' +
                ", assignedUsers=" + assignedUsers +
                ", knownAllergens=" + knownAllergens +
                '}';
    }
}