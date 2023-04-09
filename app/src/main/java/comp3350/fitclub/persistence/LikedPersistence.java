package comp3350.fitclub.persistence;

import java.util.List;
import comp3350.fitclub.objects.Exercise;

public interface LikedPersistence {
    /**
     * Gets all liked exercises
     * */
    List<Exercise> getLikedExercises();

    /**
     * Adds a new exercise to the liked list
     * */
    Exercise insertLikedExercise(Exercise currentExercise);

    /**
     * Removes an exercise from the liked list
     * */
    void deleteLikedExercise(Exercise currentExercise);

    /**
     * Checks if the exercise is in the liked list
     * */
    boolean isContainsExercise(Exercise currentExercise);
}
