package comp3350.fitclub.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExerciseTutorialStub;
import comp3350.fitclub.persistence.ExercisesDataStub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLogicTest
{
    ExerciseLogic logicTest;

    @Before
    public void initializeData() {
        logicTest = new ExerciseLogic(new ExercisesDataStub(), new ExerciseTutorialStub());
    }

    @After
    public void tearDown() {
        logicTest = null;
    }

    @Test
    public void testGetExercises()
    {
        List<Exercise> list = logicTest.getExercises();
        assertEquals(30, list.size());
    }

    @Test
    public void testAddingExercise()
    {
        System.out.println("\nStarting testAddingExercise");

        logicTest.addExercise(new Exercise("test","back",2));
        List<Exercise> list = logicTest.getExercises();
        assertEquals(31, list.size());

        System.out.println("Finished testAddingExercise");
    }

    @Test
    public void testSortByDifficulty()
    {
        logicTest.sortByDifficulty();

        //indexes 0-11 have difficulty of 1
        assertEquals(1, logicTest.getExercises().get(0).getDifficulty());
        //indexes 12-22 have difficulty of 2
        assertEquals(2, logicTest.getExercises().get(12).getDifficulty());
        //indexes 23-29 have difficulty of 3
        assertEquals(3, logicTest.getExercises().get(23).getDifficulty());
    }

    @Test
    public void testSearchExerciseValid() {

        ArrayList<Exercise> result = logicTest.searchExercise("Deadlift");

        //search Deadlift
        assertNotNull(result);
        assertEquals("Deadlift", result.get(0).getExerciseName());
        assertEquals(1, result.size());

        //search keyword: Incline
        result = logicTest.searchExercise("Incline");
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void testSearchExerciseInvalid()
    {
        ArrayList<Exercise> result = logicTest.searchExercise("pizza");
        assertNotNull(result);
        assertEquals(0,result.size());
    }

    @Test
    public void testSearchExerciseByDifficultyValid()
    {
        //search difficulty 1
        ArrayList<Exercise> result = logicTest.searchExerciseByDifficulty(1);
        assertEquals(12, result.size());
    }

    @Test
    public void testSearchExerciseByDifficultyInvalid()
    {
        ArrayList<Exercise> result = logicTest.searchExerciseByDifficulty(9);
        assertEquals(0,result.size());
    }

    @Test
    public void testSearchByMuscleGroupValid()
    {
        ArrayList<Exercise> result = logicTest.searchExerciseByMuscleGroup("back");
        assertEquals(5, result.size());
    }

    @Test
    public void testSearchByMuscleGroupInvalid()
    {
        ArrayList<Exercise> result = logicTest.searchExerciseByMuscleGroup("feet");
        assertEquals(0,result.size());
    }

}
