package comp3350.fitclub.logic;

import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.ExerciseTutorial;
import comp3350.fitclub.persistence.ExercisesPersistence;

public class ExerciseTutorialLogic {
    private ExerciseTutorialPersistence exerciseTutorialPersistence;

    public ExerciseTutorialLogic() {
        exerciseTutorialPersistence = InitializePersistence.getExerciseTutorialPersistence();
    }

    /**
     * This constructor is used for testing
     * */
    public ExerciseTutorialLogic(ExerciseTutorialPersistence exerciseTutorialPersistence) {
        this.exerciseTutorialPersistence = exerciseTutorialPersistence;
    }

    /**
     * This method returns a result Arraylist of all exerciseTutorials
     * */
    public ExerciseTutorial getExerciseTutorial(String exerciseName) {
        ExerciseTutorial exerciseTutorial = exerciseTutorialPersistence.getExerciseTutorial(exerciseName);

        //if the exercise tutorial doesn't exist, create a new one
        if (exerciseTutorial == null) {
            exerciseTutorial = exerciseTutorialPersistence.insertExerciseTutorial(new ExerciseTutorial(exerciseName, null));
        }

        return exerciseTutorial;
    }

    /**
     * Inserts a new tutorial with the given exercise name
     * */
    public void insertExerciseTutorial (String exerciseName) {
        ExerciseTutorial exerciseTutorial = new ExerciseTutorial(exerciseName, null);

        exerciseTutorialPersistence.insertExerciseTutorial(exerciseTutorial);
    }
}
