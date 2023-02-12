package comp3350.fitclub.logic;

import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.ExerciseTutorial;

public class ExerciseTutorialLogic {
    private ExerciseTutorialPersistence exerciseTutorialPersistence;

    public ExerciseTutorialLogic() {
        exerciseTutorialPersistence = InitializePersistence.getExerciseTutorialPersistence();
    }

    public ExerciseTutorial getExerciseTutorial(String exerciseName) {

        return exerciseTutorialPersistence.getExerciseTutorial(exerciseName);
    }
}
