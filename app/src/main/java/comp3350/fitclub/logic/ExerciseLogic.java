package comp3350.fitclub.logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;

/**
 * ExerciseList class
 * contains an ArrayList called exercises.
 * this class provides search and sort by difficulty functions
 */
public class ExerciseLogic
{
    private ExercisesPersistence exercisesPersistence;

    public ExerciseLogic()
    {
        exercisesPersistence = InitializePersistence.getExercisesPersistence();
    }

    /**
     * This constructor is used for testing
     * */
    public ExerciseLogic(ExercisesPersistence exercisesPersistence) {
        this.exercisesPersistence = exercisesPersistence;
    }

    /**
     * This method returns a result Arraylist of all exercises
     * */
    public List<Exercise> getExercises()
    {
        return exercisesPersistence.getExercises();
    }

    /**
     * Adds the specified exercise to the db
     * */
    public void addExercise(Exercise exercise)
    {
        exercisesPersistence.insertExercise(exercise);
    }

    /**
     * This method returns a result Arraylist of all exercises
     * that have keyword in name.
     * */
    public List<Exercise> searchExercise(String keyword)
    {
        List<Exercise> exercises = exercisesPersistence.getExercises();

        ArrayList<Exercise> results = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(exercise);
            }
        }
        return results;
    }

    /**
     * this method will search for all exercises in a given difficulty level
     * */
    public List<Exercise> searchExerciseByDifficulty(int difficultyLevel)
    {
        List<Exercise> exercises = exercisesPersistence.getExercises();

        ArrayList<Exercise> result = new ArrayList<>();
        for(Exercise exercise : exercises)
        {
            if(exercise.getDifficulty() == difficultyLevel)
                result.add(exercise);
        }
        return result;
    }

    /**
     * this method will search for all exercises in a given muscle group
     * */
    public List<Exercise> searchExerciseByMuscleGroup(String muscleGroup)
    {
        List<Exercise> exercises = exercisesPersistence.getExercises();

        ArrayList<Exercise> result = new ArrayList<>();
        for(Exercise exercise : exercises)
        {
            if(exercise.getBodyPart().equalsIgnoreCase( muscleGroup))
                result.add(exercise);
        }
        return result;
    }

    /**
     * this method will search for all exercises in a given workout
     * */
    public List<Exercise> searchExerciseByWorkout(String workoutName) {
        return exercisesPersistence.getExercisesInWorkout(workoutName);
    }

    /**
     * this method directly sort the exercises list based on difficulty number(1 - 3)
     * */
    public List<Exercise> sortByDifficulty()
    {
        List<Exercise> exercises = exercisesPersistence.getExercises();

        Collections.sort(exercises,new DifficultyLevelComparator());

        return exercises;
    }

    /**
     * comparator for sort function
     * */
    private static class DifficultyLevelComparator implements Comparator<Exercise> {
        @Override
        public int compare(Exercise exercise1, Exercise exercise2) {
            return Integer.compare(exercise1.getDifficulty(), exercise2.getDifficulty());
        }
    }
}
