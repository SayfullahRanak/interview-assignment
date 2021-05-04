package com.sevenpeakssoftware.sayfullah.ui.carlist.view


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sayfullah.assignment.R

import org.junit.Test


class CarListActivityTest{

    // check if activity visible
    @Test
    fun isActivityOnView(){
        val scenario = ActivityScenario.launch(CarListActivity::class.java)
        Espresso.onView(withId(R.id.carListrv)).check(matches(isDisplayed()))
    }

    @Test
    fun populateRecycleview(){
        val scenario = ActivityScenario.launch(CarListActivity::class.java)

         scenario.onActivity{activity->
            val recyclerView : RecyclerView = activity.findViewById(R.id.carListrv)
             val itemCount = recyclerView.adapter?.itemCount

        }
    }




}