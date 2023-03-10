package comp3350.fitclub.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class WorkoutTest {

    @Test
    public void testCalcDifficultyEven() {
        //Test for easy divisible number
        System.out.println("Beginning test: Calculating even difficulty...");
        Workout w1 = new Workout("Test", "UPPER");

        w1.addExercise(new Exercise("e1", "upper", 3));
        w1.addExercise(new Exercise("e2", "upper", 2));
        w1.addExercise(new Exercise("e3", "upper", 1));

        assertEquals(2, w1.getDifficulty());
        System.out.println("End test");
    }

    @Test
    public void testCalcDifficultyOdd() {
        System.out.println("Beginning test: Calculating odd difficulty...");
        //Test for not evenly divisible number
        Workout w1 = new Workout("Test", "UPPER");

        w1.addExercise(new Exercise("e1", "upper", 2));
        w1.addExercise(new Exercise("e2", "upper", 2));
        w1.addExercise(new Exercise("e3", "upper", 1));

        assertEquals(1, w1.getDifficulty());
        System.out.println("End test");
    }

    @Test
    public void testEqualsCorrect() {
        System.out.println("Beginning test: Correct Equals..");
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Arm Workout", "UPPER");

        assertTrue(w1.equals(w2));
        System.out.println("End test");
    }

    @Test
    public void testEqualsInCorrect() {
        System.out.println("Beginning test: Incorrect Equals..");
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Back Workout", "UPPER");

        assertFalse(w1.equals(w2));
        System.out.println("End test");
    }

    @Test
    public void testAddExercise() {
        Workout w1 = new Workout("Arm Workout", "UPPER");
        List<Exercise> list = w1.getWorkoutExercises();
        assertEquals(0, list.size());
        w1.addExercise(new Exercise("e1","back", 1));

        list = w1.getWorkoutExercises();
        assertEquals(1, list.size());
    }

    @Test
    public void testGetters() {
        System.out.println("Beginning test: Workout getters..");
        Workout w1 = new Workout("Arm Workout", "UPPER");
        w1.addExercise(new Exercise("e1","back", 1));
        w1.addExercise(new Exercise("e2","back", 1));
        w1.addExercise(new Exercise("e3","back",2));
        w1.addExercise(new Exercise("e4","back", 1));

        assertEquals("Arm Workout", w1.getName());
        assertEquals("UPPER", w1.getType());
        assertEquals(1, w1.getDifficulty());
        assertEquals(4, w1.getSize());
        System.out.println("End test");
    }

    @Test
    public void testCompare() {
        System.out.println("Beginning test: Workout compare..");
        Workout w1 = new Workout("Arm Workout", "UPPER");
        Workout w2 = new Workout("Back Workout", "UPPER");

        w1.addExercise(new Exercise("e1","arms", 1));
        w1.addExercise(new Exercise("e2","arms", 2));
        w2.addExercise(new Exercise("e3","back",2));
        w2.addExercise(new Exercise("e4","back", 1));

        assertEquals(0, w1.compareTo(w2));

        w1.addExercise(new Exercise("e1","arms", 3));

        assertEquals(-1, w1.compareTo(w2));
        System.out.println("End test");
    }
}
