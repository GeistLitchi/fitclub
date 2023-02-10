package comp3350.fitclub.objects;

import java.util.ArrayList;

public class Workout {
    /*--------- instance variables ------------*/
    private String name;    //name of the workout
//    private ArrayList<Exercise> exerciseList; //list of exercises in the workout

    /*--------- constructors ------------*/
    //default constructor
    public Workout() {
//        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
    }

    public Workout(String name) {
//        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        this.name = name;
    }

    /*---------- instance methods ------------*/
    public String getName() {
        return name;
    }

    public int getSize() {
//        return exerciseList.size();
        return 0;
    }

    //set name for creation of a custom workout
    public void setName(String newName) {
        name = newName;
    }
}
