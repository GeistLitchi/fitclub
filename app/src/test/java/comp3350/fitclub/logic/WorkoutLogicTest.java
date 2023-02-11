package comp3350.fitclub.logic;

import org.junit.Test;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.logic.WorkoutLogic;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class WorkoutLogicTest {

    private final WorkoutLogic logicTest = new WorkoutLogic();
    private Workout w1;

    @Test
    public void testCalcDifficultyEven() {
        //Test for easy divisible number
        Workout w1 = new Workout("Test", "UPPER");
        ArrayList<Exercise> list = w1.getWorkoutExercises();

        list.add(new Exercise("e1", "upper", 3));
        list.add(new Exercise("e2", "upper", 2));
        list.add(new Exercise("e3", "upper", 1));

        assertEquals(2, logicTest.calcDifficulty(w1));
    }

    @Test
    public void testCalcDifficultyOdd() {
        //Test for not evenly divisible number
        Workout w1 = new Workout("Test", "UPPER");
        ArrayList<Exercise> list = w1.getWorkoutExercises();

        list.add(new Exercise("e1", "upper", 2));
        list.add(new Exercise("e2", "upper", 2));
        list.add(new Exercise("e3", "upper", 1));

        assertEquals(1, logicTest.calcDifficulty(w1));
    }
}
