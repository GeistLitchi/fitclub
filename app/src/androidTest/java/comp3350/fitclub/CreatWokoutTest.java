package comp3350.fitclub;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.fitclub.presentation.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreatWokoutTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void favFunctionTest() throws InterruptedException {
        String WORKOUT_TEST_NAME = "Workout 1";

        //creating new workout
        onView(withId(R.id.btn_go_to_find_workout)).perform(click());
        onView(withId(R.id.createWorkout_btn)).perform(click());
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(6, click()));
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(7, click()));
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(4, click()));

        //trying to creat workout without name
        //checking if we can creat workout without name
        onView(withId(R.id.submit_btn)).perform(click());
        //we cannot do that
        onView(withId(R.id.your_workout_name)).perform(typeText(WORKOUT_TEST_NAME));
        closeSoftKeyboard();
        onView(withId(R.id.submit_btn)).perform(click());

        //checking if our workout was created
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.workout_name)).check(matches(withText(WORKOUT_TEST_NAME)));
        Thread.sleep(20);
        pressBack();
        pressBack();


    }

    @After
    public void tearDown() throws Exception {
    }

}
