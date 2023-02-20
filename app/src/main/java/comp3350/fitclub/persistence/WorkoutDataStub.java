package comp3350.fitclub.persistence;

import java.util.List;
import java.util.ArrayList;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.logic.ExerciseLogic;
import comp3350.fitclub.logic.WorkoutLogic;

public class WorkoutDataStub implements WorkoutPersistence {

    private List<Workout> workouts;
    private ExerciseLogic exerciseLogic;
    private WorkoutLogic workoutLogic;

    //-------- constructor --------//
    public WorkoutDataStub() {
        this.workouts = new ArrayList<>();
        exerciseLogic = new ExerciseLogic();
        workoutLogic = new WorkoutLogic();
        /*
            create and add workouts to the workout db, call helper method to fill with exercises
         */
        workouts.add(new Workout("UPPER BODY 1", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("UPPER BODY 2", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("UPPER BODY 3", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("UPPER BODY 4", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("UPPER BODY 5", "UPPER"));
        fillWorkout(workouts.get(workouts.size()-1));

        workouts.add(new Workout("LOWER BODY 1", "LOWER"));
        fillWorkout(workouts.get(workouts.size()-1));
    }

    @Override //'query the DB' of workouts by type
    public List<Workout> getAllWorkouts() {
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

    //Fill each workout based on subtype muscle group, calling Exercise DB via logic layer
    private void fillWorkout(Workout toFill) {
        ArrayList<Exercise> temp = new ArrayList<>();

        if(workouts.size()-1 == 0) {
            temp = exerciseLogic.searchExerciseByMuscleGroup("arms");
        } else if(workouts.size()-1 == 1) {
            temp = exerciseLogic.searchExerciseByMuscleGroup("back");
        } else if(workouts.size()-1 == 2) {
            temp = exerciseLogic.searchExerciseByMuscleGroup("shoulders");
        } else if(workouts.size()-1 == 3) {
            temp = exerciseLogic.searchExerciseByMuscleGroup("chest");
        } else if(workouts.size()-1 == 4) {
            temp = exerciseLogic.searchExerciseByMuscleGroup("core");
        } else {
            temp = exerciseLogic.searchExerciseByMuscleGroup("legs");
        }

        for(int i=0; i<temp.size(); i++) {
            toFill.addExercise(temp.get(i));
        }
        workoutLogic.calcDifficulty(toFill);
    }
}
