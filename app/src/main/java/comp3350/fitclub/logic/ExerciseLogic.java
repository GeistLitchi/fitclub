package comp3350.fitclub.logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExercisesPersistence;

/**
 * ExerciseList class
 * contains an ArrayList called exercises.
 * this class provides search and sort by difficulty functions
 */
public class ExerciseLogic
{
    private final List<Exercise> exercises;
    private ExercisesPersistence exercisesPersistence;

    public ExerciseLogic()
    {
        exercisesPersistence = InitializePersistence.getExercisesPersistence();
        exercises = exercisesPersistence.getExercises();
    }

    public List<Exercise> getExercises()
    {
        return exercises;
    }

    public void addExercise(Exercise exercise)
    {
        exercises.add(exercise);
    }

    /**
     * This method returns a result Arraylist of all exercises
     * that have keyword in name or description.
     * @parm string the keyword
     * @return list of search result
     * */
    //Search for exercises if name and description of exercise contains key word;
    public ArrayList<Exercise> searchExercise(String keyword)
    {
        ArrayList<Exercise> results = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseName().toLowerCase().contains(keyword.toLowerCase())
                    || exercise.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(exercise);
            }
        }
        return results;
    }

    //Search for exercises by difficulty level
    public ArrayList<Exercise> searchExerciseByDifficulty(int difficultyLevel)
    {
        ArrayList<Exercise> result = new ArrayList<>();
        for(Exercise exercise : exercises)
        {
            if(exercise.getDifficulty() == difficultyLevel)
                result.add(exercise);
        }
        return result;
    }

    //Search for exercises by muscle group
    public ArrayList<Exercise> searchExerciseByMuscleGroup(String muscleGroup)
    {
        ArrayList<Exercise> result = new ArrayList<>();
        for(Exercise exercise : exercises)
        {
            if(exercise.getBodyPart().equalsIgnoreCase( muscleGroup))
                result.add(exercise);
        }
        return result;
    }

    //This will search for exercises by upper or lower body
    public ArrayList<Exercise> searchExerciseByBodyGroup(String bodyGroup) {
        String[] validStrings;

        if (bodyGroup.equalsIgnoreCase("upper body")) {
            validStrings = new String[] {"Arms", "Chest", "Back", "Shoulders"};
        } else if (bodyGroup.equalsIgnoreCase("lower body")) {
            validStrings = new String[] {"Legs", "Core"};
        } else {
            validStrings = new String[] {};
        }

        ArrayList<Exercise> result = new ArrayList<>();
        for (String group : validStrings) {
            result.addAll(searchExerciseByMuscleGroup(group));
        }
        return result;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Exercise exercise : exercises)
        {
            sb.append(exercise.getExerciseName()).append(", ")
                    .append(exercise.getBodyPart()).append(", ")
                    .append(exercise.getDifficulty()).append(".\n")
                    .append(exercise.getDescription()).append("\n");
        }
        return sb.toString();
    }

    /**
     * this method directly sort the exercises list based on difficulty number(1 - 3)
     * */
    public void sortByDifficulty()
    {
        Collections.sort(exercises,new DifficultyLevelComparator());
    }

    /*
    Used for testing
     */
    public void clearList() {
        exercises.clear();
    }

    private static class DifficultyLevelComparator implements Comparator<Exercise> {
        @Override
        public int compare(Exercise exercise1, Exercise exercise2) {
            return Integer.compare(exercise1.getDifficulty(), exercise2.getDifficulty());
        }
    }
}
