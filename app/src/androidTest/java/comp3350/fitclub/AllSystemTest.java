package comp3350.fitclub;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ExploreExercisesTest.class,
                FavFunctionTest.class,
                CreateWokoutTest.class
        })

public class AllSystemTest {
}
