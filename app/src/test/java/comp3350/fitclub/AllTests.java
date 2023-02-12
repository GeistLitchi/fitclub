package comp3350.fitclub;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.fitclub.logic.WorkoutLogicTest;
import comp3350.fitclub.objects.ExerciseTest;
import comp3350.fitclub.objects.WorkoutTest;
import comp3350.fitclub.logic.ExerciseListTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ExerciseTest.class,
                WorkoutTest.class,
                WorkoutLogicTest.class,
                ExerciseListTest.class
        })
public class AllTests {
}
