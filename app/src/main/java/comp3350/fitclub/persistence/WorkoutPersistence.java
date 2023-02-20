package comp3350.fitclub.persistence;

import comp3350.fitclub.objects.Workout;
import java.util.List;

public interface WorkoutPersistence {
    List<Workout> getAllWorkouts();

    Workout insertWorkout(Workout current);

    Workout updateWorkout(Workout current);
}
