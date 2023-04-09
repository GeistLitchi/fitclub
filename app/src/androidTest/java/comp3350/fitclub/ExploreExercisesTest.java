package comp3350.fitclub;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
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
public class ExploreExercisesTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void exploreTest(){
        // nevigating to muscle groups and exploring
        onView(withId(R.id.btn_go_to_muscle_group)).perform(click());
        onView(withId(R.id.arms_btn)).perform(click());
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(0, click()));
        pressBack();
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(1, click()));
        pressBack();
        pressBack();
        onView(withId(R.id.shoulder_btn)).perform(click());
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(1, click()));
        pressBack();
        pressBack();
        pressBack();
    }

    @After
    public void tearDown() throws Exception {
    }
}