package comp3350.fitclub.logic;

import org.junit.Test;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import static org.junit.Assert.*;
import java.util.List;

public class WorkoutLogicTest {

    private WorkoutLogic logicTest = new WorkoutLogic();

    @Test
    public void testGetWorkouts() {
        //test logic returns the full list of workouts

        List<Workout> testArray = logicTest.getWorkouts();
        assertEquals(6, testArray.size());
    }

    @Test
    public void testSearchWorkoutType() {
        //test correct number of Workouts identified by type

        List<Workout> testArray = logicTest.searchWorkoutType("UPPER");
        assertEquals(5, testArray.size());
    }

    @Test
    public void testSearchWorkoutType2() {
        //test correct number of workouts with String type that isn't same case

        List<Workout> testArray = logicTest.searchWorkoutType("UpPeR");
        assertEquals(5, testArray.size());
    }

    @Test
    public void testSearchWorkoutType3() {
        //test for type not in the database;

        List<Workout> testArray = logicTest.searchWorkoutType("CORE");
        assertEquals(0, testArray.size());
    }

    @Test
    public void testSearchWorkoutDifficulty() {
        List<Workout> testArray = logicTest.searchByDifficulty(1);

        assertEquals(3, testArray.size());

        testArray = logicTest.searchByDifficulty(3);
        assertEquals(0, testArray.size());

        testArray = logicTest.searchByDifficulty(2);
        assertEquals(3, testArray.size());
    }

    @Test
    public void testSortByDifficulty() {
        //test workouts sorted by difficulty in descending order
        List<Workout> testArray = logicTest.sortByDifficulty();

        assertTrue("index 0 smaller or equal to index 1", (testArray.get(0).getDifficulty() >= testArray.get(1).getDifficulty()));
        assertTrue("index 1 smaller or equal to index 2", (testArray.get(1).getDifficulty() >= testArray.get(2).getDifficulty()));
        assertTrue("index 2 smaller or equal to index 3", (testArray.get(2).getDifficulty() >= testArray.get(3).getDifficulty()));
        assertTrue("index 3 smaller or equal to index 4", (testArray.get(3).getDifficulty() >= testArray.get(4).getDifficulty()));
        assertTrue("index 4 smaller or equal to index 5", (testArray.get(4).getDifficulty() >= testArray.get(5).getDifficulty()));

        System.out.println("Workout Logic Tests complete.");
    }
}
