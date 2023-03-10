package comp3350.fitclub.logic;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WorkoutLogic {
    private WorkoutPersistence workoutDB;

    public WorkoutLogic(){

        workoutDB = InitializePersistence.getWorkoutPersistence();
    }

    public WorkoutLogic(WorkoutPersistence workoutDB){
        this.workoutDB = workoutDB;
    }

    public List<Workout> getWorkouts() {
        return workoutDB.getAllWorkouts();
    }

    //search workout DB for workouts of a given type (UPPER, LOWER etc)
    public List<Workout> searchWorkoutType(String workoutType) {
        List<Workout> workouts = workoutDB.getAllWorkouts();
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
        List<Workout> workouts = this.workoutDB.getAllWorkouts();
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
        List<Workout> workouts = workoutDB.getAllWorkouts();
        Collections.sort(workouts);

        return workouts;
    }
}
