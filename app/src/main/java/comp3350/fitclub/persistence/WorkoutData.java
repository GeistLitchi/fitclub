package comp3350.fitclub.persistence;

import comp3350.fitclub.objects.Workout;
import java.util.List;

public interface WorkoutData {
    List<Workout> getWorkoutType(String workoutType);

    Workout insertWorkout(Workout current);

    Workout updateWorkout(Workout current);

    void deleteWorkout(Workout current);
}
