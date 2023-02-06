package comp3350.fitclub.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.fitclub.objects.Exercise;

public class ExerciseList
{
    private ArrayList<Exercise> exercises;

    public ExerciseList()
    {
        exercises = new ArrayList<Exercise>();
    }

    public ArrayList<Exercise> getExercises()
    {
        return exercises;
    }

    public void addExercise(Exercise exercise)
    {
        exercises.add(exercise);
    }

    //Search for exercises if name and description of exercise contains key word;
    public ArrayList<Exercise> searchExercise(String keyword)
    {
        ArrayList<Exercise> results = new ArrayList<Exercise>();
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

    public void sortByDifficulty()
    {
        Collections.sort(exercises,new DifficultyLevelComparator());
    }

    private static class DifficultyLevelComparator implements Comparator<Exercise> {
        @Override
        public int compare(Exercise exercise1, Exercise exercise2) {
            return Integer.compare(exercise1.getDifficulty(), exercise2.getDifficulty());
        }
    }
}
