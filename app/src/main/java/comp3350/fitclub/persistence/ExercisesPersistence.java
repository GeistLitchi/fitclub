package comp3350.fitclub.persistence;

import java.util.List;
import comp3350.fitclub.objects.Exercise;

public interface ExercisesPersistence
{
    /**
     * Fetches all of the exercises
     * */
    List<Exercise> getExercises();

    /**
     * Fetches all of the exercises in a workout
     * */
    List<Exercise> getExercisesInWorkout(String workoutName);

    /**
     * Inserts a new exercise into the database
     * */
    Exercise insertExercise(Exercise currentExercise);

    /**
     * Deletes an exercise from the database
     * */
    void deleteExercise(Exercise currentExercise);

}
