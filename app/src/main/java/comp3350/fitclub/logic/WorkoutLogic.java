package comp3350.fitclub.logic;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.stubs.WorkoutDataStub;
import java.util.ArrayList;

public class WorkoutLogic {
    private WorkoutDataStub workoutDB;

    public WorkoutLogic(){
        workoutDB = new WorkoutDataStub();
    }

    //calculate the difficulty of the workout based on average difficulty of exercises
    public int calcDifficulty(Workout current) {
        int difficulty = 0;

        if(current != null && current.getSize() > 0) {
            ArrayList<Exercise> currentExercises = current.getWorkoutExercises();

            //sum difficulties of each exercise in workout
            for(int i=0; i<currentExercises.size(); i++) {
                difficulty += currentExercises.get(i).getDifficulty();
            }
            difficulty /= current.getSize();
        }

        return difficulty;
    }

    //search workout DB for workouts of a given type (UPPER, LOWER etc)
    public ArrayList<Workout> searchWorkoutType(String workoutType) {
        ArrayList<Workout> workouts = workoutDB.getAllWorkouts();

        ArrayList<Workout> list = new ArrayList<Workout>();

        if(workoutType != null) {
            //search list of workouts for matching types
            for(int i=0; i<workouts.size(); i++) {
                String type = ((Workout)(workouts.get(i))).getType();

                if(type.equals(workoutType.toUpperCase())) {
                    list.add(workouts.get(i));
                }
            }
        }

        return list;
    }
}
