package chester.ac.uk.nextgenappandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import chester.ac.uk.nextgenappandroid.calendar.CalendarFragment
import chester.ac.uk.nextgenappandroid.condition.*
import chester.ac.uk.nextgenappandroid.diet.*
import chester.ac.uk.nextgenappandroid.mail.MailTrackerAddItem
import chester.ac.uk.nextgenappandroid.mail.MailTrackerFragment
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerAddFragment
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

class MainActivity : AppCompatActivity() {

    var dayList = mutableListOf<DietTrackerDay>()

    var activeFragment:Fragment = CalendarFragment()
    var previousFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        registerFragment(FragmentType.CALENDAR, CalendarFragment(), false)

        registerFragment(FragmentType.TRANSITION_TRACKER, TransitionTrackerFragment(), false)
        registerFragment(FragmentType.TRANSITION_TRACKER_ADD, TransitionTrackerAddFragment(), true)

        registerFragment(FragmentType.CONDITION_HUB, ConditionFragment(), false)
        registerFragment(FragmentType.CONDITION_HUB_EDIT_ABOUT, ConditionEditAbout(), true)
        registerFragment(FragmentType.CONDITION_HUB_MY_CONDITION, MyConditionEditFragment(), true)
        registerFragment(FragmentType.CONDITION_HUB_MY_MEDICATION, MyMedicationEditFragment(), true)
        registerFragment(FragmentType.CONDITION_HUB_MY_PICTURE, MyPictureEditFragment(), true)

        registerFragment(FragmentType.MAIL_TRACKER, MailTrackerFragment(), false)
        registerFragment(FragmentType.MAIL_TRACKER_ADD_ITEM, MailTrackerAddItem(), true)


        registerFragment(FragmentType.DIET_TRACKER, DietTrackerFragment(), false)
        registerFragment(FragmentType.DIET_TRACKER_ADD_ITEM, DietTrackerAddItem(), true)

        registerFragment(FragmentType.PREFERENCES, FragmentPreferences(), true)

        showFragment(FragmentType.CALENDAR)

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    showFragment(FragmentType.TRANSITION_TRACKER)
                    backbutton.visibility = View.INVISIBLE
                }

                R.id.conditionButton -> {
                    showFragment(FragmentType.CONDITION_HUB)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.calendarButton -> {
                    showFragment(FragmentType.CALENDAR)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.mailtrackerButton -> {
                    showFragment(FragmentType.MAIL_TRACKER)
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.diettrackerButton -> {
                    showFragment(FragmentType.DIET_TRACKER)
                    backbutton.visibility = View.INVISIBLE
                }
            }
            true
        }

        backbutton.setOnClickListener {
            onBackPress()
        }

        preferencesButton.setOnClickListener {
            showFragment(FragmentType.PREFERENCES)
        }

    }

    private fun registerFragment(type: FragmentType, fragment: Fragment, showBackButton: Boolean){
        val bundle = Bundle()
        bundle.putBoolean("showBackButton", showBackButton)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, type.desc).hide(fragment).commit();
    }

    fun showFragment(type: FragmentType, bundle: Bundle) {
        val fragment = supportFragmentManager.findFragmentByTag(type.desc)

        if (fragment != null){
            //Set date to be transferred

            bundle.putBoolean("showBackButton", (fragment.arguments!!.get("showBackButton") as Boolean))
            fragment.arguments = bundle

            backbutton.visibility = View.INVISIBLE
            if((fragment.arguments!!.get("showBackButton") as Boolean)) {
                bundle.putBoolean("showBackButton", (fragment.arguments!!.get("showBackButton") as Boolean))
                backbutton.visibility = View.VISIBLE
            }

            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            previousFragment = activeFragment
            activeFragment = fragment


        }

    }

    fun showFragment(type: FragmentType) {
        val fragment = supportFragmentManager.findFragmentByTag(type.desc)

        if (fragment != null){

            backbutton.visibility = View.INVISIBLE
            if((fragment.arguments!!.get("showBackButton") as Boolean)) {
                backbutton.visibility = View.VISIBLE
            }

            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            previousFragment = activeFragment
            activeFragment = fragment

        }

    }

    fun onBackPress() {

        if(previousFragment != null) {
            supportFragmentManager.beginTransaction().hide(activeFragment).show(previousFragment!!).commit()
            val temp = activeFragment
            activeFragment = previousFragment!!
            previousFragment = temp
            backbutton.visibility = View.INVISIBLE //TODO: check if we're in a secondary page, if the next fragment requires a back button then show one
        }
    }

    /*
    override fun onBackPressed() {
        super.onBackPressed()
        when (activeFragment) {
            transitionTrackerFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
            }
            conditionFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
            }
            calendarFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
            }
            mailTrackerFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
            }
            dietTrackerFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
            }
            conditionEditAbout -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionFragment).commit()
                activeFragment = conditionFragment
                backbutton.visibility = View.INVISIBLE
            }
            myConditionEditFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionFragment).commit()
                activeFragment = conditionFragment
                backbutton.visibility = View.INVISIBLE
            }
            myMedicationEditFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionFragment).commit()
                activeFragment = conditionFragment
                backbutton.visibility = View.INVISIBLE
            }
            myPictureEditFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionFragment).commit()
                activeFragment = conditionFragment
                backbutton.visibility = View.INVISIBLE
            }
            mailTrackerAddFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(mailTrackerFragment).commit()
                activeFragment = mailTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            dietTrackerAddItemFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(dietTrackerFragment).commit()
                activeFragment = dietTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            transitionTrackerAddFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(transitionTrackerFragment).commit()
                activeFragment = transitionTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            preferencesFragment -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(previousFragment ?: calendarFragment).commit()
                activeFragment = previousFragment ?: calendarFragment
                preferencesButton.visibility = View.VISIBLE
                backbutton.visibility = View.INVISIBLE
            }
        }
    }

    //swaps visible fragment
    fun fragmentSwap (fragment: String, info: String) {
        when (fragment) {
            getString(R.string.transition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(transitionTrackerFragment).commit()
                activeFragment = transitionTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.condition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(conditionFragment).commit()
                backbutton.visibility = View.INVISIBLE

                when (activeFragment){
                    conditionEditAbout -> {
                        tvAbout.text = info
                    }
                    myConditionEditFragment -> {
                        tvSymptoms.text = info
                    }
                    myMedicationEditFragment -> {
                        tvMedication.text = info
                    }
                    myPictureEditFragment-> {
                        val parcelFileDescriptor: ParcelFileDescriptor = contentResolver.openFileDescriptor(Uri.parse(info), "r")
                        val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
                        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                        parcelFileDescriptor.close()

                        profilePicture.setImageBitmap(image)
                    }
                }

                activeFragment = conditionFragment
            }
            getString(R.string.calendar) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit()
                activeFragment = calendarFragment
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.mailtracker) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(mailTrackerFragment).commit()
                activeFragment = mailTrackerFragment
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.diettracker) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(dietTrackerFragment).commit()

                if (activeFragment == dietTrackerAddItemFragment) {
                    var mealInfo = info.split(",").map { it.trim() }
                    var nutrition = ""
                    var dayExists = false

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
