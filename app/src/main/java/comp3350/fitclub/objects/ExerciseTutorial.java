package comp3350.fitclub.objects;

public class ExerciseTutorial {

    /*
    This will identify what exercise this tutorial is for. We might want to consider adding an id
    field to exercises in the future and link that way, but I will just use the name for now
     */
    private final String exerciseName;

    /*
    this will be the meat of the tutorial. A text tutorial (Likely a paragraph or 2) on how to
    perform the exercise
     */
    private final String body;

    public ExerciseTutorial(final String newExerciseName)
    {
        exerciseName = newExerciseName;
        body = null;
    }

    public ExerciseTutorial(final String newExerciseName, final String newBody)
    {
        exerciseName = newExerciseName;
        body = newBody;
    }

    public String getExerciseName()
    {
        return (exerciseName);
    }

    public String getBody()
    {
        if (body == null) {
            return "The instructions for this exercise will be coming in a future release. " +
                    "Please check the deadlift tutorial to view an exercise that has instructions.";
        }
        return (body);
    }

}
