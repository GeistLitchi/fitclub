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

    public ExerciseTutorialLogic(ExerciseTutorialPersistence exerciseTutorialPersistence) {
        this.exerciseTutorialPersistence = exerciseTutorialPersistence;
    }

    public ExerciseTutorial getExerciseTutorial(String exerciseName) {

        return exerciseTutorialPersistence.getExerciseTutorial(exerciseName);
    }

    //inserts an exerciseTutorial with default body
    public void insertExerciseTutorial (String exerciseName) {
        ExerciseTutorial exerciseTutorial = new ExerciseTutorial(exerciseName,
                "This exercise is new and doesn't have a tutorial yet");

        exerciseTutorialPersistence.insertExerciseTutorial(exerciseTutorial);
    }
}
