package com.example.deric.photogallery2;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


import static com.example.deric.photogallery2.EspressoTestsMatchers.withDrawable;
import static com.example.deric.photogallery2.EspressoTestsMatchers.hasDrawable;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.deric.photogallery2", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<GalleryActivity> mActivityRule = new ActivityTestRule(GalleryActivity.class);


    @Before
    public void grantPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + InstrumentationRegistry.getTargetContext().getPackageName()
                            + " android.permission.READ_EXTERNAL_STORAGE");
        }
    }



    @Test
    public void checkLocationFilter() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        onView(withId(R.id.filterButton))
                .perform(click());
       // onView(withId(R.id.FromDate)).perform(typeText("01/01/2016"), closeSoftKeyboard());
       // onView(withId(R.id.ToDate)).perform(typeText("01/01/2017"), closeSoftKeyboard());
        onView(withId(R.id.Location))
                .perform(typeText("vancouver"), closeSoftKeyboard());
        onView(withId(R.id.save))
                .perform(click());

        onData(hasDrawable())
                .inAdapterView(withId(R.id.gridview))
                //.atPosition(0)
                .check(matches(withDrawable(R.raw.amazed)));
    }


    @After
    public void cleanUp() {
        Log.e("XXXXXXXXXXXXXXXXX", "Test");
    }
}





