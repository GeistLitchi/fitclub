package comp3350.fitclub.objects;

import android.annotation.SuppressLint;

import java.util.Objects;

public class Exercise
{
    private final String exerciseName;
    private final String muscleGroup;

    /*difficulty from 1-3,which means simple, medium and difficult,
    * Maybe later we can use different color images to represent different degrees of difficulty*/
    private final int difficulty;
    private final String description;

    public Exercise(final String name)
    {
        exerciseName = name;
        muscleGroup = null;
        difficulty = 0;
        description = null;
    }

    public Exercise(final String name, final String group, final int difficulty)
    {
        exerciseName = name;
        muscleGroup = group;
        this.difficulty = difficulty;
        description = null;
    }

    public Exercise(final String name, final String group, final int difficulty, final String description)
    {
        exerciseName = name;
        muscleGroup = group;
        this.difficulty = difficulty;
        this.description = description;
    }

    public String getExerciseName(){return exerciseName;}

    public String getBodyPart(){return muscleGroup;}

    public int getDifficulty(){return difficulty;}

    public String getDescription() {return description;}



    public String toString()
    {
        return String.format("Exercise: %s Muscle Group: %s Difficulty: %d\nDescription: %s",exerciseName,muscleGroup,difficulty,description);
    }

    public boolean equals(Object other)
    {
        boolean equals = false;
        if(other instanceof Exercise)
        {
            final Exercise otherExercise = (Exercise) other;
            equals = Objects.equals(this.exerciseName,otherExercise.exerciseName);
        }

        return equals;
    }

}
