
import java.util.ArrayList;

public class User implements Comparable<User> {
    private int userId;
    private String name;
    private int age;
    private ArrayList<String> symptoms;

    public User(int userId, String name, int age, ArrayList<String> symptoms) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
    }

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.symptoms =  new ArrayList<String>();

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public void logSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }



    @Override
    public int compareTo(User otherUser)
    {
        int out = 0;
        if(this.userId>otherUser.getUserId()) out = 1;
        else if (this.userId<otherUser.getUserId())out = -1;
        else out = 0;
        return out;
    }

}
