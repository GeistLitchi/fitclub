package comp3350.fitclub.objects;

import java.util.ArrayList;
import java.util.List;

public class Workout implements Comparable{
    /*--------- instance variables ------------*/
    private String name;    //name of the workout
    private String type;    //the type of workout
    private int difficulty; //value between 1-3 meaning simple, medium, difficult to match exercise difficulty
    private List<Exercise> workoutExercises; //list of exercises in the workout

    /*--------- constructors ------------*/
    public Workout(String name) {
        workoutExercises = new ArrayList<Exercise>();
        this.name = name; //in future check if name is in db already?
        type = null; //can change this to custom in later implementations
        difficulty = 0;
    }

    public Workout(String name, String type) {
        workoutExercises = new ArrayList<Exercise>();
        this.name = name;
        this.type = type;
        difficulty = 0;
    }

    public Workout(String name, String type, int difficulty) {
        workoutExercises = new ArrayList<Exercise>();
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
    }

    /*---------- instance methods ------------*/
    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return workoutExercises.size();
    }

    public List<Exercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public void addExercise(Exercise toAdd) {
        if(null != toAdd) {
            workoutExercises.add(toAdd);
            calcDifficulty();
        }
    }

    public String toString() {
        return String.format("Workout: %s (%s)", name, type);
    }

    public boolean equals(Object other) {
        boolean isEqual = false;

        if(other instanceof Workout) {
            final Workout otherWorkout = (Workout) other;

            isEqual = this.name.equals(otherWorkout.name);
        }

        return isEqual;
    }

    public boolean equals(Workout otherWorkout) {
        return otherWorkout.getName().equalsIgnoreCase(name);
    }

    public int compareTo(Object other) {
        int compare = 0;

        if(other instanceof Workout) {
            final Workout otherWorkout = (Workout) other;

            compare = otherWorkout.getDifficulty() - this.difficulty;
        }

        return compare;
    }

    private void calcDifficulty() {
        int diff = 0;

        // sum workout difficulty
        for(int i=0; i<getSize(); i++) {
            diff += workoutExercises.get(i).getDifficulty();
        }
        this.difficulty = diff/getSize();
    }
}
