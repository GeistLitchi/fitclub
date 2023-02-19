package comp3350.fitclub.persistence;

import java.util.ArrayList;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.WorkoutPersistence;

public class WorkoutDataStub implements WorkoutPersistence {

    private ArrayList<Workout> workouts;

    //-------- constructor --------//
    public WorkoutDataStub() {
        this.workouts = new ArrayList<Workout>();

        workouts.add(new Workout("UPPER BODY 1", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("UPPER BODY 2", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("LOWER BODY 1", "LOWER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("LOWER BODY 2", "LOWER"));
        fillWorkout(workouts.get(workouts.size()-1));

        //for custom workout test
        workouts.add(new Workout("CUSTOM WORKOUT", "CUSTOM"));
        fillWorkout(workouts.get(workouts.size()-1));
    }

    @Override //'query the DB' of workouts by type
    public ArrayList<Workout> getAllWorkouts() {
        return workouts;
    }

    @Override
    public Workout insertWorkout(Workout current) {
        workouts.add(current);
        return current;
    }

    @Override
    public Workout updateWorkout(Workout current) {
        int index;
        index = workouts.indexOf(current);
        if (index >= 0)
        {
            workouts.set(index, current);
        }

        return current;
    }

    @Override
    public void deleteWorkout(Workout current) {
        //future implementations
    }

    //To fill out made workouts for now
    private void fillWorkout(Workout toFill) {
        if(workouts.size()-1 == 0) {
            toFill.addExercise(new Exercise("e1", "arms", 2));
            toFill.addExercise(new Exercise("e2", "arms", 2));
            toFill.addExercise(new Exercise("e3", "arms", 2));
            toFill.setDifficulty(2);
        } else if(workouts.size()-1 == 1) {
            toFill.addExercise(new Exercise("e4", "back", 1));
            toFill.addExercise(new Exercise("e5", "back", 2));
            toFill.addExercise(new Exercise("e6", "back", 3));
            toFill.setDifficulty(2);
        } else if(workouts.size()-1 == 2) {
            toFill.addExercise(new Exercise("e7", "legs", 3));
            toFill.addExercise(new Exercise("e8", "legs", 3));
            toFill.addExercise(new Exercise("e9", "legs", 3));
            toFill.setDifficulty(3);
        } else {
            toFill.addExercise(new Exercise("e10", "legs", 3));
            toFill.addExercise(new Exercise("e11", "legs", 1));
            toFill.addExercise(new Exercise("e12", "legs", 1));
            toFill.setDifficulty(1);
        }
    }
}
