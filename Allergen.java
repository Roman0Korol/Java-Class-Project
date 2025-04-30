
import java.util.ArrayList;

public class Allergen implements Comparable<Allergen> {
    private String name;
    private int allergenId;
    private int severity;
    private ArrayList<String> symptoms;



    public Allergen(String name, int allergenId, int severity) {
        this.name = name;
        this.allergenId = allergenId;
        this.severity = severity;
    }

    public Allergen(String name, int allergenId, int severity, ArrayList<String> symptoms) {
        this.name = name;
        this.allergenId = allergenId;
        this.severity = severity;
        this.symptoms = symptoms;
    }

    public int getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(int allergenId) {
        this.allergenId = allergenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }


    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    @Override
    public int compareTo(Allergen otherAllergen)
    {
        int out = 0;
        if(this.severity>otherAllergen.getSeverity()) out = -1;
        else if (this.severity<otherAllergen.getSeverity())out = 1;
        else out = 0;
        return out;
    }


    @Override
    public String toString() {
        return "Allergen{" +
                "name='" + name + '\'' +
                ", allergenId=" + allergenId +
                ", severity=" + severity +
                ", symptoms=" + symptoms +
                '}';
    }
}