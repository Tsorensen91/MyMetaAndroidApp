package chester.ac.uk.nextgenappandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import chester.ac.uk.nextgenappandroid.calendar.CalendarFragment
import chester.ac.uk.nextgenappandroid.condition.*
import chester.ac.uk.nextgenappandroid.diet.DietTrackerAddItem
import chester.ac.uk.nextgenappandroid.diet.DietTrackerDay
import chester.ac.uk.nextgenappandroid.diet.DietTrackerFragment
import chester.ac.uk.nextgenappandroid.mail.MailTrackerAddItem
import chester.ac.uk.nextgenappandroid.mail.MailTrackerFragment
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerAdd
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerFragment
import kotlinx.android.synthetic.main.activity_main.*

enum class FragmentType(val desc: String) {
    CALENDAR("FragmentCalendar"),
    TRANSITION_TRACKER("FragmentTransitionTracker"),
    TRANSITION_TRACKER_ADD("FragmentTransitionTrackerAdd"),
    CONDITION_HUB("FragmentConditionHub"),
    CONDITION_HUB_EDIT_ABOUT("FragmentConditionHubEditAbout"),
    CONDITION_HUB_MY_CONDITION("FragmentConditionHubMyCondition"),
    CONDITION_HUB_MY_MEDICATION("FragmentConditionHubMyMedication"),
    CONDITION_HUB_MY_PICTURE("ConditionHubMyPicture"),
    MAIL_TRACKER("FragmentMailTracker"),
    MAIL_TRACKER_ADD_ITEM("FragmentMailTrackerAddItem"),
    DIET_TRACKER("FragmentDietTracker"),
    DIET_TRACKER_ADD_ITEM("FragmentDietTrackerAddItem"),
    PREFERENCES("FragmentPreferences")
}

interface OnShowFragment {
    fun onShow(bundle: Bundle)
}

class MainActivity : AppCompatActivity() {

    var activeFragment: Fragment = CalendarFragment()
    var previousFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        showFragment(FragmentType.CALENDAR, false)

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    showFragment(FragmentType.TRANSITION_TRACKER, false)
                    backbutton.visibility = View.INVISIBLE
                }

                R.id.conditionButton -> {
                    showFragment(FragmentType.CONDITION_HUB, false)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.calendarButton -> {
                    showFragment(FragmentType.CALENDAR, false)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.mailtrackerButton -> {
                    showFragment(FragmentType.MAIL_TRACKER, false)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.diettrackerButton -> {
                    showFragment(FragmentType.DIET_TRACKER, false)
                    backbutton.visibility = View.INVISIBLE
                }
            }
            true
        }

        backbutton.setOnClickListener {
            onBackPress()
        }

        preferencesButton.setOnClickListener {
            showFragment(FragmentType.PREFERENCES, true)
        }

    }

    fun showFragment(type: FragmentType, bundle: Bundle, from: FragmentType, showBackButton: Boolean) {

        val fragment = getFragmentClass(type)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, type.desc).commit()

        backbutton.visibility = View.INVISIBLE
        if (showBackButton) {
            backbutton.visibility = View.VISIBLE
        }

        if (fragment is OnShowFragment) {
            bundle.putString("from", from.desc)
            fragment.onShow(bundle)
        }

        supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        previousFragment = activeFragment
        activeFragment = fragment


    }

    fun showFragment(type: FragmentType, showBackButton: Boolean) {
        val fragment = getFragmentClass(type)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, type.desc).commit()

        backbutton.visibility = View.INVISIBLE
        if (showBackButton) {
            backbutton.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        previousFragment = activeFragment
        activeFragment = fragment


    }

    private fun getFragmentClass(type: FragmentType): Fragment {
        return when (type) {
            FragmentType.CALENDAR -> CalendarFragment()
            FragmentType.TRANSITION_TRACKER -> TransitionTrackerFragment()
            FragmentType.TRANSITION_TRACKER_ADD -> TransitionTrackerAdd()
            FragmentType.CONDITION_HUB -> ConditionFragment()
            FragmentType.CONDITION_HUB_EDIT_ABOUT -> ConditionEditAbout()
            FragmentType.CONDITION_HUB_MY_CONDITION -> MyConditionEditFragment()
            FragmentType.CONDITION_HUB_MY_MEDICATION -> MyMedicationEditFragment()
            FragmentType.CONDITION_HUB_MY_PICTURE -> MyPictureEditFragment()
            FragmentType.MAIL_TRACKER -> MailTrackerFragment()
            FragmentType.MAIL_TRACKER_ADD_ITEM -> MailTrackerAddItem()
            FragmentType.DIET_TRACKER -> DietTrackerFragment()
            FragmentType.DIET_TRACKER_ADD_ITEM -> DietTrackerAddItem()
            FragmentType.PREFERENCES -> FragmentPreferences()
        }
    }

    private fun onBackPress() {

        if (previousFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, previousFragment!!).commit()
            val temp = activeFragment
            activeFragment = previousFragment!!
            previousFragment = temp
            backbutton.visibility = View.INVISIBLE //TODO: check if we're in a secondary page, if the next fragment requires a back button then show one
        }
    }

    /*

    //swaps visible fragment
    fun fragmentSwap (fragment: String, info: String) {
        when (fragment) {

            getString(R.string.diettracker) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(dietTrackerFragment).commit()

                if (activeFragment == dietTrackerAddItemFragment) {
                    var mealList = info.split(",").map { it.trim() }

                    for (x in 2 until mealInfo.size) {
                        nutrition += mealInfo[x]
                    }
                    val meal = Meals(mealInfo[0], mealInfo[1], nutrition)

                    for (x in 0 until dayList.size) {
                        if (dayList[x].mealDate.date == Calendar.getInstance().time.date && dayList[x].mealDate.month == Calendar.getInstance().time.month && dayList[x].mealDate.year == Calendar.getInstance().time.year ){
                            dayList[x].mealList.add(meal)
                            dayExists = true
                        }
                    }
                    if (dayExists == false) {
                        val day = DietTrackerDay(Calendar.getInstance().time)
                        day.mealList.add(meal)
                        dayList.add(day)
                    }
                    dietTrackerFragment.updateList(dayList)
                }
                activeFragment = dietTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.editaboutme) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionEditAbout).addToBackStack("tag").commit()
                activeFragment = conditionEditAbout
                backbutton.visibility = View.VISIBLE

            }
            getString(R.string.editmycondition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(myConditionEditFragment).addToBackStack("tag").commit()
                activeFragment = myConditionEditFragment
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.editmymedication) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(myMedicationEditFragment).addToBackStack("tag").commit()
                activeFragment = myMedicationEditFragment
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.editprofilepicture) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(myPictureEditFragment).addToBackStack("tag").commit()
                activeFragment = myPictureEditFragment
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.mailtrackeradd) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(mailTrackerAddFragment).addToBackStack("tag").commit()
                activeFragment = mailTrackerAddFragment
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.preferencesfragment) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(preferencesFragment).addToBackStack("tag").commit()
                previousFragment = activeFragment
                activeFragment = preferencesFragment
                preferencesButton.visibility = View.INVISIBLE
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.diettrackeradd) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(dietTrackerAddItemFragment).addToBackStack("tag").commit()
                activeFragment = dietTrackerAddItemFragment
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.transitiontrackeradd) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(transitionTrackerAddFragment).addToBackStack("tag").commit()
                activeFragment = transitionTrackerAddFragment
                backbutton.visibility = View.VISIBLE
            }
        }

    }
    */

}
