package comp3350.fitclub;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class FavFunctionTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void favFunctionTest(){
        String EXE_TEST_NAME = "Barbell Curl";
        // adding exercice named EXE_TEST_NAME to fav
        onView(withId(R.id.btn_go_to_muscle_group)).perform(click());
        onView(withId(R.id.arms_btn)).perform(click());
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(0, click()));
        //checking if we clciked onn right exercise
        onView(withId(R.id.tutorial_exercise_name)).check(matches(withText(EXE_TEST_NAME)));
        pressBack();
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(0, longClick()));

        //checking weither exercice added to fav
        pressBack();
        pressBack();
        onView(withId(R.id.btn_liked)).perform(click());
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(0, click()));
        //checking if we clciked onn right exercise
        onView(withId(R.id.tutorial_exercise_name)).check(matches(withText(EXE_TEST_NAME)));

        // delecting it from the fav again
        pressBack();
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition(0, longClick()));

        //checking fav exercise has been deleted
        pressBack();
        onView(withId(R.id.btn_liked)).perform(click());
        onView(withId(R.id.recycleView)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }


}






