package comp3350.fitclub.persistence;

import java.util.List;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.WorkoutExercise;

public interface WorkoutExercisePersistence {

    void insertWorkoutExercises(List<WorkoutExercise> workoutExerciseList);

    void deleteWorkoutExercisesByWorkoutName(String workoutName);
}
