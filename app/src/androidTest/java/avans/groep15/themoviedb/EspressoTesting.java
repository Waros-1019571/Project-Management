package avans.groep15.themoviedb;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import avans.groep15.themoviedb.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
public class EspressoTesting {

    @Rule
    public ActivityTestRule<MainActivity> mConfigsTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSearchView() {
        // Type "Spider" in the search view
        Espresso.onView(ViewMatchers.withId(R.id.searchView))
                .perform(ViewActions.typeText("Spider"));

        // Check if the search view contains the typed text
        Espresso.onView(ViewMatchers.withId(R.id.searchView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Spider")));
    }


    @Test
    public void testRecyclerView() {
        // Check if the recycler view is displayed
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the recycler view has at least one item
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)));
    }
}
