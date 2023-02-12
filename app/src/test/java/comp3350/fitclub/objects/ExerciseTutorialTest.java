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
        exerciseTutorial = new ExerciseTutorial("Deadlift","This is how you deadlift",
                "https://exrx.net/WeightExercises/ErectorSpinae/BBDeadlift");

        assertNotNull(exerciseTutorial);
        assertEquals("Deadlift", exerciseTutorial.getExerciseName());
        assertEquals("This is how you deadlift",exerciseTutorial.getBody());
        assertEquals("https://exrx.net/WeightExercises/ErectorSpinae/BBDeadlift",exerciseTutorial.getLink());

        System.out.println("\nFinished testExerciseTutorialAllParameters");
    }

    @Test
    public void testExerciseTutorialJustNameParameter()
    {
        ExerciseTutorial exerciseTutorial;
        System.out.println("\nStarting testExerciseTutorialJustNameParameter\n");

        //description copied from wikipedia
        exerciseTutorial = new ExerciseTutorial("Deadlift");

        assertNotNull(exerciseTutorial);
        assertEquals("Deadlift", exerciseTutorial.getExerciseName());
        assertEquals("The instructions for this exercise will be coming in a future release." +
                "Please check one of the back exercises to view an exercise that has instructions.",
                exerciseTutorial.getBody());
        assertEquals(null,exerciseTutorial.getLink());

        System.out.println("\nFinished testExerciseTutorialJustNameParameter");
    }

}
