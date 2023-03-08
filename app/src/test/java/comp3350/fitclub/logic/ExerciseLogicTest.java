package comp3350.fitclub.logic;

import org.junit.Before;
import org.junit.Test;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExerciseTutorialStub;
import comp3350.fitclub.persistence.ExercisesDataStub;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ExerciseLogicTest
{
    ExerciseLogic logic;

    @Before
    public void initializeData() {
        logic = new ExerciseLogic(new ExercisesDataStub(), new ExerciseTutorialStub());
    }

    @Test
    public void testExerciseList()
    {
        System.out.println("\nStarting testExerciseList");

        Exercise e1 = new Exercise("deadlift","back",2);
        Exercise e2 = new Exercise("squat","leg", 1);
        Exercise e3 = new Exercise("plank","core",3);

        logic.addExercise(e1);
        logic.addExercise(e2);
        logic.addExercise(e3);

        assertNotNull(logic);

        System.out.println("Finished testExerciseList");
    }

    @Test
    public void testExerciseListSort()
    {
        Exercise e1 = new Exercise("deadlift","back",2);
        Exercise e2 = new Exercise("squat","leg", 1);
        Exercise e3 = new Exercise("plank","core",3);

        logic.addExercise(e1);
        logic.addExercise(e2);
        logic.addExercise(e3);

        //difficulty in current order is 2 - 1 - 3
        //after sorting by difficulty, it should be 1 - 2 - 3
        logic.sortByDifficulty();
        System.out.println(logic.toString());
        assertEquals(1,logic.getExercises().get(0).getDifficulty());
        assertEquals(2,logic.getExercises().get(1).getDifficulty());
        assertEquals(3,logic.getExercises().get(2).getDifficulty());

        //test case that there are multiple exercises with same difficulty
        Exercise e4 = new Exercise("dumbbell curls","arm",1);
        Exercise e5 = new Exercise("dumbbell lateral raises", "shoulder",2);
        logic.addExercise(e4);
        logic.addExercise(e5);

        assertEquals(1,logic.getExercises().get(3).getDifficulty());
        assertEquals(2,logic.getExercises().get(4).getDifficulty());

        //the order should be 1-1-2-2-3 after sorting
        logic.sortByDifficulty();
        assertEquals(1,logic.getExercises().get(1).getDifficulty());

        System.out.println(logic.toString());
    }

    @Test
    public void testExerciseListSearch()
    {
        Exercise e1 = new Exercise("deadlift","back",2);
        Exercise e2 = new Exercise("squat","leg", 1);
        Exercise e3 = new Exercise("plank","core",3);
        Exercise e4 = new Exercise("dumbbell curls","arm",1);
        Exercise e5 = new Exercise("dumbbell lateral raises", "shoulder",2);

        logic.addExercise(e1);
        logic.addExercise(e2);
        logic.addExercise(e3);
        logic.addExercise(e4);
        logic.addExercise(e5);

        ArrayList<Exercise> searchResult1 = logic.searchExercise("deadlift");

        //search deadlift
        //only one exercise should be found, that is deadlift
        assertNotNull(searchResult1);
        assertEquals("deadlift", searchResult1.get(0).getExerciseName());
        assertEquals(1, searchResult1.size());
        System.out.println(searchResult1.toString());

        //search keyword: dumbbell
        //three exercises should be found: dumbbell curls and dumbbell lateral raises,
        searchResult1 = logic.searchExercise("dumbbell");
        assertNotNull(searchResult1);
        assertEquals(2, searchResult1.size());
        System.out.println(searchResult1.toString());

        //search difficulty 2
        //two exercises should be found
        searchResult1 = logic.searchExerciseByDifficulty(2);
        assertNotNull(searchResult1);
        assertEquals(2,searchResult1.size());
        System.out.println(searchResult1.toString());

        //search muscle group back
        //1 exercise should be found
        searchResult1 = logic.searchExerciseByMuscleGroup("back");
        assertNotNull(searchResult1);
        assertEquals(1,searchResult1.size());
        System.out.println(searchResult1.toString());

        //search "pizza", difficulty 9 and feet
        //there is no result expected
        searchResult1 = logic.searchExercise("pizza");
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());
        searchResult1 = logic.searchExerciseByDifficulty(9);
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());
        searchResult1 = logic.searchExerciseByMuscleGroup("feet");
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());


    }

}
