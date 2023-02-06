package comp3350.fitclub.objects;

import java.util.ArrayList;

public class Workout
{
    private final ArrayList<Exercise> workout;
    private int exerciseNum;

    public Workout()
    {
        workout = new ArrayList<>();
        exerciseNum = 0;
    }

    public ArrayList<Exercise> getWorkout()
    {
        return workout;
    }

    public int getExerciseNum()
    {
        return exerciseNum;
    }

    public void addExercise(Exercise exercise)
    {
        workout.add(exercise);
        exerciseNum++;
    }

    public void removeExercise(Exercise exercise)
    {
        if(!workout.isEmpty())
        {
            workout.remove(exercise);
            exerciseNum--;
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Exercise exercise : workout)
        {
            sb.append(exercise.getExerciseName()).append(", ")
                    .append(exercise.getBodyPart()).append(", ")
                    .append(exercise.getDifficulty()).append(".\n")
                    .append(exercise.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
