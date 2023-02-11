package comp3350.fitclub.persistence;

import comp3350.fitclub.objects.Workout;
import java.util.ArrayList;

public interface WorkoutData {
    ArrayList<Workout> getAllWorkouts();

    Workout insertWorkout(Workout current);

    Workout updateWorkout(Workout current);

    void deleteWorkout(Workout current);
}
