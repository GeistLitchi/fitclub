package comp3350.fitclub.application;

import comp3350.fitclub.objects.WorkoutExercise;
import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.LikedPersistence;
import comp3350.fitclub.persistence.WorkoutExercisePersistence;
import comp3350.fitclub.persistence.sql.ExerciseTutorialSQL;
import comp3350.fitclub.persistence.sql.ExercisesSQL;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;
import comp3350.fitclub.persistence.sql.LikedExercisesSQL;
import comp3350.fitclub.persistence.sql.WorkoutExerciseSQL;
import comp3350.fitclub.persistence.sql.WorkoutSQL;

public class InitializePersistence {

    private static String dbName = "SC";

    private static ExercisesPersistence exercisesPersistence = null;
    private static ExerciseTutorialPersistence exerciseTutorialPersistence = null;
    private static WorkoutPersistence workoutPersistence = null;
    private static LikedPersistence likedPersistence = null;
    private static WorkoutExercisePersistence workoutExercisePersistence = null;

    public static synchronized ExerciseTutorialPersistence getExerciseTutorialPersistence() {
        if (exerciseTutorialPersistence == null) {
            exerciseTutorialPersistence = new ExerciseTutorialSQL(getDbName());
        }

        return exerciseTutorialPersistence;
    }

    public static synchronized ExercisesPersistence getExercisesPersistence() {
        if (exercisesPersistence == null) {
            exercisesPersistence = new ExercisesSQL(getDbName());
        }

        return exercisesPersistence;
    }

    public static synchronized WorkoutPersistence getWorkoutPersistence() {
        if (workoutPersistence == null) {
            workoutPersistence = new WorkoutSQL(getDbName());
        }

        return workoutPersistence;
    }

    public static synchronized LikedPersistence getLikedPersistence() {
        if (likedPersistence == null) {
            likedPersistence = new LikedExercisesSQL(getDbName());
        }

        return likedPersistence;
    }

    public static synchronized WorkoutExercisePersistence getWorkoutExercisePersistence() {
        if (workoutExercisePersistence == null) {
            workoutExercisePersistence = new WorkoutExerciseSQL(getDbName());
        }

        return workoutExercisePersistence;
    }

    public static void setDBPathName(String newName) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbName = newName;
    }

    public static String getDbName() {
        return dbName;
    }
}
