package comp3350.fitclub.persistence;

import java.util.List;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.WorkoutExercise;

public interface WorkoutExercisePersistence {

    /**
     * Inserts all new workoutExercises from list into database
     * */
    void insertWorkoutExercises(List<WorkoutExercise> workoutExerciseList);

    /**
     * Deletes all workout exercises associated with a workout
     * */
    void deleteWorkoutExercisesByWorkoutName(String workoutName);
}
