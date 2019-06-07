package chester.ac.uk.nextgenappandroid

import android.app.ActionBar
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenuView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import chester.ac.uk.nextgenappandroid.calendar.CalendarAdd
import chester.ac.uk.nextgenappandroid.calendar.CalendarExpandFragment
import chester.ac.uk.nextgenappandroid.calendar.CalendarFragment
import chester.ac.uk.nextgenappandroid.condition.*
import chester.ac.uk.nextgenappandroid.diet.DietTrackerAddItem
import chester.ac.uk.nextgenappandroid.diet.DietTrackerFragment
import chester.ac.uk.nextgenappandroid.mail.MailTrackerAddItem
import chester.ac.uk.nextgenappandroid.mail.MailTrackerFragment
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerAddFragment
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

enum class FragmentType(val desc: String) {
    CALENDAR("FragmentCalendar"),
    CALENDAR_EXPANDED("FragmentCalendarExpand"),
    CALENDAR_ADD("FragmentCalendarAdd"),
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

    val prevStack = Stack<Fragment>()

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

        //changes bottomnavbar iconsize to wrap_content.
        var menuView : BottomNavigationMenuView = navigation.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount ) {
            val iconView = menuView.getChildAt(i).findViewById<View>(android.support.design.R.id.icon)
            val layoutParams = iconView.layoutParams
            val displayMetrics = resources.displayMetrics
            layoutParams.height = ActionBar.LayoutParams.WRAP_CONTENT
            layoutParams.width = ActionBar.LayoutParams.WRAP_CONTENT
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
        if (!prevStack.isEmpty()) {
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
        prevStack.push(activeFragment)
        activeFragment = fragment


    }

    fun showFragment(type: FragmentType, showBackButton: Boolean) {
        val fragment = getFragmentClass(type)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, type.desc).commit()

        backbutton.visibility = View.INVISIBLE
        if (!prevStack.isEmpty()) {
            backbutton.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        prevStack.push(activeFragment)
        activeFragment = fragment


    }

    private fun onBackPress() {

        if (!prevStack.isEmpty()) {
            val frag = prevStack.pop()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, frag).commit()
            activeFragment = frag


            if (prevStack.isEmpty())
                backbutton.visibility = View.INVISIBLE //TODO: check if we're in a secondary page, if the next fragment requires a back button then show one
        }
    }

    private fun getFragmentClass(type: FragmentType): Fragment {
        return when (type) {
            FragmentType.CALENDAR -> CalendarFragment()
            FragmentType.CALENDAR_EXPANDED -> CalendarExpandFragment()
            FragmentType.CALENDAR_ADD -> CalendarAdd()
            FragmentType.TRANSITION_TRACKER -> TransitionTrackerFragment()
            FragmentType.TRANSITION_TRACKER_ADD -> TransitionTrackerAddFragment()
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
}
