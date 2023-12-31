package comp3350.fitclub.persistence;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import java.util.List;

public interface WorkoutPersistence {
    /**
     * Fetches all of the workouts
     * */
    List<Workout> getAllWorkouts();

    /**
     * Inserts a new workout into the database
     * */
    Workout insertWorkout(Workout current);

    /**
     * Deletes a workout with a given name and all associated WorkoutExercise entries
     * */
    void deleteWorkout(String workoutName);

}
