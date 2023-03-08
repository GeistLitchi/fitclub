package comp3350.fitclub.application;

import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.sql.ExerciseTutorialSQL;
import comp3350.fitclub.persistence.sql.ExercisesSQL;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;
import comp3350.fitclub.persistence.WorkoutDataStub;

public class InitializePersistence {

    private static ExercisesPersistence exercisesPersistence = null;
    private static ExerciseTutorialPersistence exerciseTutorialPersistence = null;
    private static WorkoutPersistence workoutPersistence = null;

    public static synchronized ExerciseTutorialPersistence getExerciseTutorialPersistence() {
        if (exerciseTutorialPersistence == null) {
            exerciseTutorialPersistence = new ExerciseTutorialSQL(Main.getDbName());
        }

        return exerciseTutorialPersistence;
    }

    public static synchronized ExercisesPersistence getExercisesPersistence() {
        if (exercisesPersistence == null) {
            exercisesPersistence = new ExercisesSQL(Main.getDbName());
        }

        return exercisesPersistence;
    }

    public static synchronized WorkoutPersistence getWorkoutPersistence() {
        if (workoutPersistence == null) {
            workoutPersistence = new WorkoutDataStub();
        }

        return workoutPersistence;
    }
}
