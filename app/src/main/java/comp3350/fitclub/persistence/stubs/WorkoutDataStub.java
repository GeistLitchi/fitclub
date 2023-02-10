package comp3350.fitclub.persistence.stubs;

import java.util.ArrayList;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.persistence.WorkoutData;

public class WorkoutDataStub implements WorkoutData {

    @Override
    public ArrayList<Workout> getWorkoutMuscleGroup(String workoutType) {
        return null;
    }

    @Override
    public Workout insertWorkout(Workout current) {
        return null;
    }

    @Override
    public Workout updateWorkout(Workout current) {
        return null;
    }

    @Override
    public void deleteWorkout(Workout current) {

    }
}
