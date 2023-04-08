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

    //Next 2 constructors are used for testing
    public WorkoutLogic(WorkoutPersistence workoutDB){
        this.workoutPersistence = workoutDB;
    }
    public WorkoutLogic(WorkoutPersistence workoutDB, WorkoutExercisePersistence workoutExercisePersistence) {
        this.workoutPersistence = workoutDB;
        this.workoutExercisePersistence = workoutExercisePersistence;
    }

    public Workout insertWorkout (Workout workout) {
        return workoutPersistence.insertWorkout(workout);
    }

    //calculate the difficulty of the workout based on average difficulty of exercises
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

    public List<Workout> getWorkouts() {
        return workoutPersistence.getAllWorkouts();
    }

    //search workout DB for workouts of a given type (UPPER, LOWER etc)
    public List<Workout> searchWorkoutType(String workoutType) {
        List<Workout> workouts = workoutPersistence.getAllWorkouts();
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


    //sort the workout by difficulty in descending order
    public List<Workout> sortByDifficulty() {
        List<Workout> workouts = workoutPersistence.getAllWorkouts();
        Collections.sort(workouts);

        return workouts;
    }

    //adds all of the exercises in the list provided to the workout
    public void addExercises(Workout workout, List<Exercise> exerciseList) {
        List<WorkoutExercise> workoutExerciseList = new ArrayList<>();

        for (Exercise exercise : exerciseList) {
            workoutExerciseList.add(new WorkoutExercise(workout.getName(), exercise.getExerciseName()));
        }

        workoutExercisePersistence.insertWorkoutExercises(workoutExerciseList);
    }

}
