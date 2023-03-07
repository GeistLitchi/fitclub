package comp3350.fitclub.logic;

import org.junit.Test;

import comp3350.fitclub.objects.Exercise;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ExerciseLogicTest
{
    @Test
    public void testExerciseList()
    {
        ExerciseLogic l1;

        System.out.println("\nStarting testExerciseList");

        Exercise e1 = new Exercise("deadlift","back",2, "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands");
        Exercise e2 = new Exercise("squat","leg", 1,"Description of squat");
        Exercise e3 = new Exercise("plank","core",3,"Description of plank");

        l1 = new ExerciseLogic();
        l1.addExercise(e1);
        l1.addExercise(e2);
        l1.addExercise(e3);

        assertNotNull(l1);
        System.out.println(l1.toString());



        System.out.println("Finished testExerciseList");
    }

    @Test
    public void testExerciseListSort()
    {
        ExerciseLogic list;
        Exercise e1 = new Exercise("deadlift","back",2, "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands");
        Exercise e2 = new Exercise("squat","leg", 1,"Description of squat");
        Exercise e3 = new Exercise("plank","core",3,"Description of plank");
        list = new ExerciseLogic();

        list.addExercise(e1);
        list.addExercise(e2);
        list.addExercise(e3);

        System.out.println(list.toString());
        //difficulty in current order is 2 - 1 - 3
        //after sorting by difficulty, it should be 1 - 2 - 3
        list.sortByDifficulty();
        System.out.println(list.toString());
        assertEquals(1,list.getExercises().get(0).getDifficulty());
        assertEquals(2,list.getExercises().get(1).getDifficulty());
        assertEquals(3,list.getExercises().get(2).getDifficulty());

        //test case that there are multiple exercises with same difficulty
        Exercise e4 = new Exercise("dumbbell curls","arm",1,"Description of dumbbell curl");
        Exercise e5 = new Exercise("dumbbell lateral raises", "shoulder",2, "Description of dumbbell lateral raises");
        list.addExercise(e4);
        list.addExercise(e5);

        assertEquals(1,list.getExercises().get(3).getDifficulty());
        assertEquals(2,list.getExercises().get(4).getDifficulty());

        //the order should be 1-1-2-2-3 after sorting
        list.sortByDifficulty();
        assertEquals(1,list.getExercises().get(1).getDifficulty());

        System.out.println(list.toString());



    }

    @Test
    public void testExerciseListSearch()
    {
        ExerciseLogic list;
        Exercise e1 = new Exercise("deadlift","back",2, "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands");
        Exercise e2 = new Exercise("squat","leg", 1,"Description of squat");
        Exercise e3 = new Exercise("plank","core",3,"Description of plank");
        Exercise e4 = new Exercise("dumbbell curls","arm",1,"Description of dumbbell curl");
        Exercise e5 = new Exercise("dumbbell lateral raises", "shoulder",2, "Description of dumbbell lateral raises");

        list = new ExerciseLogic();

        list.addExercise(e1);
        list.addExercise(e2);
        list.addExercise(e3);
        list.addExercise(e4);
        list.addExercise(e5);

        ArrayList<Exercise> searchResult1 = new ArrayList<>();
        searchResult1 = list.searchExercise("deadlift");

        //search deadlift
        //only one exercise should be found, that is deadlift
        assertNotNull(searchResult1);
        assertEquals("deadlift", searchResult1.get(0).getExerciseName());
        assertEquals(1, searchResult1.size());
        System.out.println(searchResult1.toString());

        //search keyword: dumbbell
        //three exercises should be found: dumbbell curls and dumbbell lateral raises,
        //also deadlift, since the dumbbell is mentioned in description of deadlift
        searchResult1 = list.searchExercise("dumbbell");
        assertNotNull(searchResult1);
        assertEquals(3, searchResult1.size());
        System.out.println(searchResult1.toString());

        //search difficulty 2
        //two exercises should be found
        searchResult1 = list.searchExerciseByDifficulty(2);
        assertNotNull(searchResult1);
        assertEquals(2,searchResult1.size());
        System.out.println(searchResult1.toString());

        //search muscle group back
        //1 exercise should be found
        searchResult1 = list.searchExerciseByMuscleGroup("back");
        assertNotNull(searchResult1);
        assertEquals(1,searchResult1.size());
        System.out.println(searchResult1.toString());

        //search "pizza", difficulty 9 and feet
        //there is no result expected
        searchResult1 = list.searchExercise("pizza");
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());
        searchResult1 = list.searchExerciseByDifficulty(9);
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());
        searchResult1 = list.searchExerciseByMuscleGroup("feet");
        assertNotNull(searchResult1);
        assertEquals(0,searchResult1.size());


    }

}
