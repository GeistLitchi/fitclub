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
    private final String description;

    /*
    If more information is needed, this will be a link to a webpage with a more detailed description,
    images and/or a video.
     */
    private final String link;

    public ExerciseTutorial(final String newExerciseName)
    {
        exerciseName = newExerciseName;
        description = null;
        link = null;
    }

    public ExerciseTutorial(final String newExerciseName, final String newDescription, final String newLink)
    {
        exerciseName = newExerciseName;
        description = newDescription;
        link = newLink;
    }

    public String getExerciseName()
    {
        return (exerciseName);
    }

    public String getDescription()
    {
        return (description);
    }

    public String getLink()
    {
        return (link);
    }

}
