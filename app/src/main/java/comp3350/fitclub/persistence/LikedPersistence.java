package comp3350.fitclub.persistence;

import java.util.List;
import comp3350.fitclub.objects.Exercise;

public interface LikedPersistence {
    List<Exercise> getLikedExercises();

    Exercise insertLikedExercise(Exercise currentExercise);

    void deleteLikedExercise(Exercise currentExercise);

    boolean isContainsExercise(Exercise currentExercise);
}
