package comp3350.fitclub;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.fitclub.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
public class LikedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddFavorite()
    {
        //check and click the button to go to muscle group page
        onView(withId(R.id.btn_go_to_muscle_group)).check(matches(isDisplayed())).perform(click());

        //check and click the button to go to chest exercises
        onView(withId(R.id.chest_btn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.workout_name)).check(matches(withText("CHEST")));

        //click the first item(bench press) in the page
        onView(withId(R.id.recycleView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,longClick()));

        //check the favorite icon is displayed
        onView(withId(R.id.recycleView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,scrollTo()))
                .check(matches(hasDescendant(withId(R.id.image_favorite)))).check(matches(isDisplayed()));

        //todo
        //check the Toast message is correct

        //long click again to cancel the favorite
        onView(withId(R.id.recycleView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,longClick()));


    }
}
