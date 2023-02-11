package comp3350.fitclub.objects;

import org.junit.Test;
import comp3350.fitclub.objects.Exercise;
import static org.junit.Assert.*;

public class ExerciseTest
{
    @Test
    public void testExercise1()
    {
        Exercise exercise1;
        System.out.println("\nStarting testExercise\n");

        //description copied from wikipedia
        exercise1 = new Exercise("deadlift","back",2,
                        "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands");

        assertNotNull(exercise1);
        assertEquals("deadlift", exercise1.getExerciseName());
        assertEquals("back",exercise1.getBodyPart());
        assertEquals(2,exercise1.getDifficulty());

        System.out.println(exercise1.toString());




        System.out.println("\nFinished testCourse");
    }

}
