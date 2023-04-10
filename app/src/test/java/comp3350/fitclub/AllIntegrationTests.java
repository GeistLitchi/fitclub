package comp3350.fitclub;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.fitclub.logic.WorkoutIntegrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                WorkoutIntegrationTests.class,
        })

public class AllIntegrationTests {
}
