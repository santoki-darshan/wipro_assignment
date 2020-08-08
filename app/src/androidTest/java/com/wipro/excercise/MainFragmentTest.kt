package com.wipro.excercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wipro.excercise.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val ir = InstantTaskExecutorRule()

    @Test
    fun useAppContext() {
        onView(withId(R.id.main_list)).check(matches(isDisplayed()))
    }
}
