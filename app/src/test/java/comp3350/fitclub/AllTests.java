package comp3350.fitclub;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.fitclub.logic.WorkoutLogicTest;
import comp3350.fitclub.objects.ExerciseTest;
import comp3350.fitclub.logic.ExerciseLogicTest;
import comp3350.fitclub.objects.ExerciseTutorialTest;
import comp3350.fitclub.objects.WorkoutTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ExerciseTest.class,
                ExerciseTutorialTest.class,
                ExerciseLogicTest.class,
                WorkoutTest.class,
                WorkoutLogicTest.class
        })
public class AllTests {
}
