package chester.ac.uk.nextgenappandroid

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.View
import chester.ac.uk.nextgenappandroid.calendar.CalendarFragment
import chester.ac.uk.nextgenappandroid.condition.*
import chester.ac.uk.nextgenappandroid.diet.DietTrackerFragment
import chester.ac.uk.nextgenappandroid.mail.MailTrackerAddItem
import chester.ac.uk.nextgenappandroid.mail.MailTrackerFragment
import chester.ac.uk.nextgenappandroid.transition.TransitionTrackerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_condition.*


class MainActivity : AppCompatActivity() {

    var list = mutableListOf<Fragment>()
    var activeFragment:Fragment = CalendarFragment()
    val transitionTrackerFragment = TransitionTrackerFragment()
    val conditionFragment = ConditionFragment()
    val calendarFragment = CalendarFragment()
    val mailTrackerFragment = MailTrackerFragment()
    val dietTrackerFragment = DietTrackerFragment()
    val conditionEditAbout = ConditionEditAbout()
    val myConditionEditFragment = MyConditionEditFragment()
    val myMedicationEditFragment = MyMedicationEditFragment()
    val myPictureEditFragment = MyPictureEditFragment()
    val mailTrackerAddFragment = MailTrackerAddItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        list.add(transitionTrackerFragment)
        list.add(conditionFragment)
        list.add(calendarFragment)
        list.add(mailTrackerFragment)
        list.add(dietTrackerFragment)
        list.add(conditionEditAbout)
        list.add(myConditionEditFragment)
        list.add(myMedicationEditFragment)
        list.add(myPictureEditFragment)
        list.add(mailTrackerAddFragment)

        for (x in 0 until list.size) {
            fragmentHide(list[x])
        }

        backbutton.visibility = View.INVISIBLE

        fragmentSwap(getString(R.string.calendar),"")

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    fragmentSwap(getString(R.string.transition),"")
                    backbutton.visibility = View.INVISIBLE
                }

                R.id.conditionButton -> {
                    fragmentSwap(getString(R.string.condition),"")
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.calendarButton -> {
                    fragmentSwap(getString(R.string.calendar),"")
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.mailtrackerButton -> {
                    fragmentSwap(getString(R.string.mailtracker),"")
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.diettrackerButton -> {
                    fragmentSwap(getString(R.string.diettracker),"")
                    backbutton.visibility = View.INVISIBLE
                }
            }
            true
        }

        backbutton.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

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
        }

    }

    fun fragmentHide (fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment).hide(fragment).commit()
    }
    

}
