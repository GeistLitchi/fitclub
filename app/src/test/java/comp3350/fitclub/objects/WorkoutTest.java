package comp3350.fitclub.objects;

import org.junit.Test;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.logic.WorkoutLogic;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class WorkoutTest {

    @Test
    public void testEqualsCorrect() {
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Arm Workout", "UPPER");

        assertTrue(w1.equals(w2));
    }

    @Test
    public void testEqualsInCorrect() {
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Back Workout", "UPPER");

        assertFalse(w1.equals(w2));
    }

    @Test
    public void testGetters() {
        Workout w1 = new Workout("Arm Workout", "UPPER");
        w1.setDifficulty(3);

        assertEquals("Arm Workout", w1.getName());
        assertEquals("UPPER", w1.getType());
        assertEquals(3, w1.getDifficulty());
        assertEquals(0, w1.getSize());
    }

    @Test
    public void testCompare() {
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Back Workout", "UPPER");
        w1.setDifficulty(3);
        w2.setDifficulty(3);

        assertEquals(0, w1.compareTo(w2));

        w2.setDifficulty(2);
        assertEquals(-1, w1.compareTo(w2));
    }
}
