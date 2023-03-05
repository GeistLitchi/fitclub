package comp3350.fitclub.logic;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.WorkoutDataStub;
import comp3350.fitclub.persistence.WorkoutPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WorkoutLogic {
    private WorkoutPersistence workoutDB;
    private List<Workout> workouts;

    public WorkoutLogic(){
        workoutDB = InitializePersistence.getWorkoutPersistence();
        workouts = workoutDB.getAllWorkouts();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    //search workout DB for workouts of a given type (UPPER, LOWER etc)
    public List<Workout> searchWorkoutType(String workoutType) {
        List<Workout> list = new ArrayList<Workout>();

        if(workoutType != null) {
            //search list of workouts for matching types
            for(int i=0; i<workouts.size(); i++) {
                String type = ((workouts.get(i)).getType());

                if(type.equals(workoutType.toUpperCase())) {
                    list.add(workouts.get(i));
                }
            }
        }

        return list;
    }

    //search the workout db for workouts of a given difficulty
    public List<Workout> searchByDifficulty(int workoutDifficulty) {
        List<Workout> list = new ArrayList<Workout>();

        //search list of workouts for matching types
        for(int i=0; i<workouts.size(); i++) {
            int diff = ((workouts.get(i)).getDifficulty());

            if(diff == workoutDifficulty) {
                list.add(workouts.get(i));
            }
        }

        return list;
    }

    //sort the workout by difficulty in descending order
    public List<Workout> sortByDifficulty() {
        Collections.sort(workouts);

        return workouts;
    }
}
