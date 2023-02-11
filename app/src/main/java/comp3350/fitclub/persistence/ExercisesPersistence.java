package comp3350.fitclub.persistence;

import java.util.List;
import comp3350.fitclub.objects.Exercise;

public interface ExercisesPersistence
{
    List<Exercise> getExercises();

    Exercise insertExercise(Exercise currentExercise);

    void deleteExercise(Exercise currentExercise);


}
