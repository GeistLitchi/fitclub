package comp3350.fitclub.logic;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.WorkoutExercise;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.WorkoutExercisePersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WorkoutLogic {
    private WorkoutPersistence workoutPersistence;
    private WorkoutExercisePersistence workoutExercisePersistence;

    public WorkoutLogic(){

        workoutPersistence = InitializePersistence.getWorkoutPersistence();
        workoutExercisePersistence = InitializePersistence.getWorkoutExercisePersistence();

    }

    /**
     * These constructors are used for testing
     * */
    public WorkoutLogic(WorkoutPersistence workoutDB){
        this.workoutPersistence = workoutDB;
    }

    public WorkoutLogic(WorkoutPersistence workoutDB, WorkoutExercisePersistence workoutExercisePersistence) {
        this.workoutPersistence = workoutDB;
        this.workoutExercisePersistence = workoutExercisePersistence;
    }

    /**
     * Creates a new workout
     * */
    public Workout insertWorkout (Workout workout) {

        //if name is not set, cannot insert
        if (workout.getName().equals("")) {
            return null;
        }

        List<Workout> workoutList = workoutPersistence.getAllWorkouts();

        //if workout already exists in the database, cannot insert
        if (workoutList.contains(workout)) {
            return null;
        } else {
            return workoutPersistence.insertWorkout(workout);
        }
    }

    /**
     * Calculates the difficulty of the current workout. If it is different than what was stored
     * previously, update it.
     * */
    public int calcDifficulty(Workout current) {
        int difficulty = 0;

        if (current != null && current.getSize() > 0) {
            List<Exercise> currentExercises = current.getWorkoutExercises();

            //sum difficulties of each exercise in workout
            for (int i = 0; i < currentExercises.size(); i++) {
                difficulty += currentExercises.get(i).getDifficulty();
            }
            difficulty /= current.getSize();
        }

        return difficulty;
    }

    /**
     * Fetches all workouts
     * */
    public List<Workout> getWorkouts() {
        return workoutPersistence.getAllWorkouts();
    }

    /**
     * Searches for workouts based on a given type (Arms, Legs, Chest, etc)
     * */
    public List<Workout> searchWorkoutType(String workoutType) {
        List<Workout> workouts = workoutPersistence.getAllWorkouts();
        List<Workout> list = new ArrayList<Workout>();

        if(workoutType != null) {
            //search list of workouts for matching types
            for(int i=0; i<workouts.size(); i++) {
                String type = ((workouts.get(i)).getType());

                if(type.equalsIgnoreCase(workoutType)) {
                    list.add(workouts.get(i));
                }
            }
        }

        return list;
    }

    /**
     * Fetches all workouts of a certain difficulty
     * */
    public List<Workout> searchByDifficulty(int workoutDifficulty) {
        List<Workout> workouts = this.workoutPersistence.getAllWorkouts();
        List<Workout> list = new ArrayList<>();

        //search list of workouts for matching types
        for(int i=0; i<workouts.size(); i++) {
            int diff = ((workouts.get(i)).getDifficulty());

            if(diff == workoutDifficulty) {
                list.add(workouts.get(i));
            }
        }

        return list;
    }


    /**
     * Sorts a workout list by difficulty
     * */
    public List<Workout> sortByDifficulty() {
        List<Workout> workouts = workoutPersistence.getAllWorkouts();
        Collections.sort(workouts);

        return workouts;
    }

    /**
     * Adds a list of exercises to the given workout
     * */
    public void addExercises(Workout workout, List<Exercise> exerciseList) {
        List<WorkoutExercise> workoutExerciseList = new ArrayList<>();

        for (Exercise exercise : exerciseList) {
            workoutExerciseList.add(new WorkoutExercise(workout.getName(), exercise.getExerciseName()));
        }

        workoutExercisePersistence.insertWorkoutExercises(workoutExerciseList);
    }

    /**
     * Deletes a workout with a given name and all associated WorkoutExercise entries
     * */
    public void deleteWorkout(String workoutName) {
        workoutPersistence.deleteWorkout(workoutName);
        workoutExercisePersistence.deleteWorkoutExercisesByWorkoutName(workoutName);
    }

}
