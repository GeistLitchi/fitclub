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
    private ExerciseTutorialLogic exerciseTutorialLogic;

    public ExerciseLogic()
    {
        exercisesPersistence = InitializePersistence.getExercisesPersistence();
        exerciseTutorialLogic = new ExerciseTutorialLogic();
    }

    public ExerciseLogic(ExercisesPersistence exercisesPersistence, ExerciseTutorialPersistence exerciseTutorialPersistence) {
        this.exercisesPersistence = exercisesPersistence;
        exerciseTutorialLogic = new ExerciseTutorialLogic(exerciseTutorialPersistence);
    }

    public List<Exercise> getExercises()
    {
        return exercisesPersistence.getExercises();
    }

    public void addExercise(Exercise exercise)
    {
        exercisesPersistence.insertExercise(exercise);
        exerciseTutorialLogic.insertExerciseTutorial(exercise.getExerciseName());
    }

    /**
     * This method returns a result Arraylist of all exercises
     * that have keyword in name.
     * @parm string the keyword
     * @return list of search result
     * */
    //Search for exercises if name and description of exercise contains key word;
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

    //Search for exercises by difficulty level
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

    //Search for exercises by muscle group
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

    //This will search for exercises by upper or lower body
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

    private static class DifficultyLevelComparator implements Comparator<Exercise> {
        @Override
        public int compare(Exercise exercise1, Exercise exercise2) {
            return Integer.compare(exercise1.getDifficulty(), exercise2.getDifficulty());
        }
    }
}
