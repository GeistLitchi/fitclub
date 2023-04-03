package comp3350.fitclub.objects;

public class WorkoutExercise
{
    private final String exerciseName;
    private final String workoutName;

    public WorkoutExercise(final String workoutName, final String exerciseName)
    {
        this.workoutName = workoutName;
        this.exerciseName = exerciseName;
    }

    public String getWorkoutName() {return workoutName;}

    public String getExerciseName() {return exerciseName;}
}
