package comp3350.fitclub.persistence;

import comp3350.fitclub.objects.ExerciseTutorial;

public interface ExerciseTutorialPersistence {

    /**
     * Fetches all of the exerciseTutorials
     * */
    ExerciseTutorial getExerciseTutorial(String exerciseName);

    /**
     * Adds a new exerciseTutorial to the database
     * */
    ExerciseTutorial insertExerciseTutorial(ExerciseTutorial exerciseTutorial);
}
