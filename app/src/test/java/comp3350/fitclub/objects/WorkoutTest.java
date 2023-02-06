package comp3350.fitclub.objects;

import org.junit.Test;
import static org.junit.Assert.*;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;

public class WorkoutTest
{

    @Test
    public void testWorkout1()
    {
        Workout w1;
        System.out.println("\nStarting testWorkout\n");

        w1 = new Workout();
        Exercise e1 = new Exercise("deadlift","back",2,
                "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands");
        Exercise e2 = new Exercise("squat","leg", 1,"Description of squat");
        Exercise e3 = new Exercise("plank","core",2,"Description of plank");

        //test basic function and attributes in workout class
        assertNotNull(w1);
        assertTrue(w1.getWorkout().isEmpty());
        assertEquals(0,w1.getExerciseNum() );

        w1.addExercise(e1);
        assertEquals(1, w1.getExerciseNum());
        w1.addExercise(e2);
        assertEquals(2,w1.getExerciseNum());
        System.out.println(w1.toString());

        w1.removeExercise(e2);
        assertEquals(1, w1.getExerciseNum());
        w1.removeExercise(e1);
        assertEquals(0, w1.getExerciseNum());




        System.out.println("\nFinished testWorkout");
    }
}
