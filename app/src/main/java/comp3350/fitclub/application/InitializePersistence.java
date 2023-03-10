package comp3350.fitclub.application;

import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.LikedPersistence;
import comp3350.fitclub.persistence.sql.ExerciseTutorialSQL;
import comp3350.fitclub.persistence.sql.ExercisesSQL;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;
import comp3350.fitclub.persistence.sql.LikedExercisesSQL;
import comp3350.fitclub.persistence.sql.WorkoutSQL;

public class InitializePersistence {

    private static ExercisesPersistence exercisesPersistence = null;
    private static ExerciseTutorialPersistence exerciseTutorialPersistence = null;
    private static WorkoutPersistence workoutPersistence = null;
    private static LikedPersistence likedPersistence = null;

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
            workoutPersistence = new WorkoutSQL(Main.getDbName());
        }

        return workoutPersistence;
    }

    public static synchronized LikedPersistence getLikedPersistence() {
        if (likedPersistence == null) {
            likedPersistence = new LikedExercisesSQL(Main.getDbName());
        }

        return likedPersistence;
    }
}
