package comp3350.fitclub.logic;

import org.junit.Before;
import org.junit.Test;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.logic.WorkoutLogic;
import comp3350.fitclub.persistence.WorkoutDataStub;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class WorkoutLogicTest {

    private WorkoutLogic logicTest;
    private Workout w1;

    @Before
    public void initializeData() {
        logicTest = new WorkoutLogic(new WorkoutDataStub());
    }

    @Test
    public void testCalcDifficultyEven() {
        //Test for easy divisible number
        System.out.println("Beginning Workout Logic Tests...");
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

    @Test
    public void testSearchWorkoutType() {
        //test correct number of Workouts identified by type

        ArrayList<Workout> testArray = logicTest.searchWorkoutType("UPPER");
        assertEquals(2, testArray.size());
    }

    @Test
    public void testSearchWorkoutTypeReturn() {
        //test correct number of workouts with String type that isn't same case

        ArrayList<Workout> testArray = logicTest.searchWorkoutType("UPPER");

        assertTrue(testArray.get(0).getType().equals("UPPER") && testArray.get(1).getType().equals("UPPER"));
    }

    @Test
    public void testSearchWorkoutType2() {
        //test correct number of workouts with String type that isn't same case

        ArrayList<Workout> testArray = logicTest.searchWorkoutType("UpPeR");
        assertEquals(2, testArray.size());
    }

    @Test
    public void testSearchWorkoutType3() {
        //test for type not in the database;

        ArrayList<Workout> testArray = logicTest.searchWorkoutType("CORE");
        assertEquals(0, testArray.size());
    }

    @Test
    public void testSearchWorkoutDifficulty() {
        ArrayList<Workout> testArray = logicTest.searchByDifficulty(2);

        assertEquals(2, testArray.size());

        testArray = logicTest.searchByDifficulty(3);
        assertEquals(1, testArray.size());
    }

    @Test
    public void testSortByDifficulty() {
        ArrayList<Workout> testArray = logicTest.sortByDifficulty();

        assertEquals(3, testArray.get(0).getDifficulty());
        assertEquals(1, testArray.get(3).getDifficulty());
        System.out.println("Workout Logic Tests complete.");
    }
}
