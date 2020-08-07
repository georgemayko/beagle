/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.automated_tests.cucumber.robots

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.automated_tests.R
import com.example.automated_tests.cucumber.elements.ZIP_FIELD
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher


class ScreenRobot {

    fun checkViewContainsText(text: String?): ScreenRobot {
        Espresso.onView(Matchers.allOf(withText(text))).check(matches(isDisplayed()))
        return this
    }

    fun clickOnText(text: String?): ScreenRobot {
        Espresso.onView(Matchers.allOf(withText(text), isDisplayed())).perform(ViewActions.click())
        return this
    }

    fun typeIntoTextField(position1: Int, position2: Int, text: String?): ScreenRobot {
        Espresso.onView(childAtPosition(childAtPosition(withClassName(Matchers.`is`("br.com.zup.beagle.android.view.custom.BeagleFlexView")), position1), position2)).perform(scrollTo(), ViewActions.replaceText(text))
        Espresso.closeSoftKeyboard()
        return this
    }

    fun scrollViewDown(): ScreenRobot {
        Espresso.onView(withId(R.id.root_layout)).perform(ViewActions.swipeUp())
        return this
    }

    fun swipeLeftOnView(): ScreenRobot {
        Espresso.onView(Matchers.allOf(withId(R.id.root_layout))).perform(ViewActions.swipeLeft())
        return this
    }

    fun swipeRightOnView(): ScreenRobot {
        Espresso.onView(withId(R.id.root_layout)).perform(ViewActions.swipeRight())
        return this
    }

    fun scrollTo(text: String?): ScreenRobot {
        Espresso.onView(withText(text)).perform(scrollTo()).check(matches(isDisplayed()))
        return this
    }

    fun clickOnTouchableImage(): ScreenRobot {
        Espresso.onView(childAtPosition(childAtPosition(withClassName(Matchers.`is`("br.com.zup.beagle.android.view.custom.BeagleFlexView")), 1), 1)).perform(ViewActions.click())
        return this
    }


        @Throws(InterruptedException::class)
        fun sleep(seconds: Int): ScreenRobot {
            Thread.sleep(seconds * 1000L)
            return this
        }


        companion object {
            private fun childAtPosition(
                parentMatcher: Matcher<View>, position: Int): Matcher<View> {
                return object : TypeSafeMatcher<View>() {
                    override fun describeTo(description: Description) {
                        description.appendText("Child at position $position in parent ")
                        parentMatcher.describeTo(description)
                    }

                    public override fun matchesSafely(view: View): Boolean {
                        val parent = view.parent
                        return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position))
                    }
                }
            }
        }
    }