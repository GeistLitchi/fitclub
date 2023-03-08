package comp3350.fitclub.objects;

import org.junit.Test;
import comp3350.fitclub.objects.ExerciseTutorial;
import static org.junit.Assert.*;

public class ExerciseTutorialTest
{
    @Test
    public void testExerciseTutorialAllParameters()
    {
        ExerciseTutorial exerciseTutorial;
        System.out.println("\nStarting testExerciseTutorialAllParameters\n");

        //description copied from wikipedia
        exerciseTutorial = new ExerciseTutorial("Deadlift","This is how you deadlift");

        assertNotNull(exerciseTutorial);
        assertEquals("Deadlift", exerciseTutorial.getExerciseName());
        assertEquals("This is how you deadlift",exerciseTutorial.getBody());

        System.out.println("\nFinished testExerciseTutorialAllParameters");
    }

    //Check that other 2 parameters get defaulted correctly
    @Test
    public void testExerciseTutorialJustNameParameter()
    {
        ExerciseTutorial exerciseTutorial;
        System.out.println("\nStarting testExerciseTutorialJustNameParameter\n");

        //description copied from wikipedia
        exerciseTutorial = new ExerciseTutorial("Deadlift");

        assertNotNull(exerciseTutorial);
        assertEquals("Deadlift", exerciseTutorial.getExerciseName());
        assertEquals("The instructions for this exercise will be coming in a future release. " +
                        "Please check the deadlift tutorial to view an exercise that has instructions.",
                exerciseTutorial.getBody());

        System.out.println("\nFinished testExerciseTutorialJustNameParameter");
    }

}
