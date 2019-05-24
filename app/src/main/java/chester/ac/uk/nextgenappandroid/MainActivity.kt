package chester.ac.uk.nextgenappandroid

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_transition_tracker.*

class MainActivity : AppCompatActivity() {

    var list = mutableListOf<Fragment>()
    var activeFragment:Fragment = CalendarFragment()
    val fragment1 = TransitionTrackerFragment()
    val fragment2 = ConditionFragment()
    val fragment3 = CalendarFragment()
    val fragment4 = MailtrackerFragment()
    val fragment5 = DiettrackerFragment()
    val fragment6 = ConditionEditAbout()
    val fragment7 = MyConditionEditFragment()
    val fragment8 = MyMedicationEditFragment()
    val fragment9 = MyPictureEditFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        list.add(fragment1)
        list.add(fragment2)
        list.add(fragment3)
        list.add(fragment4)
        list.add(fragment5)
        list.add(fragment6)
        list.add(fragment7)
        list.add(fragment8)
        list.add(fragment9)

        for (x in 0 until list.size) {
            fragmentHide(list[x])
        }

        backbutton.visibility = View.INVISIBLE

        fragmentSwap(getString(R.string.calendar))

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    fragmentSwap(getString(R.string.transition))
                    backbutton.visibility = View.INVISIBLE
                }

                R.id.conditionButton -> {
                    fragmentSwap(getString(R.string.condition))
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.calendarButton -> {
                    fragmentSwap(getString(R.string.calendar))
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.mailtrackerButton -> {
                    fragmentSwap(getString(R.string.mailtracker))
                    backbutton.visibility = View.INVISIBLE
                }
                R.id.diettrackerButton -> {
                    fragmentSwap(getString(R.string.diettracker))
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
            fragment1 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
            }
            fragment2 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
            }
            fragment3 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
            }
            fragment4 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
            }
            fragment5 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
            }
            fragment6 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                activeFragment = fragment2
                backbutton.visibility = View.INVISIBLE
            }
            fragment7 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                activeFragment = fragment2
                backbutton.visibility = View.INVISIBLE
            }
            fragment8 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                activeFragment = fragment2
                backbutton.visibility = View.INVISIBLE
            }
            fragment9 -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                activeFragment = fragment2
                backbutton.visibility = View.INVISIBLE
            }
        }
    }

    //swaps visible fragment
    fun fragmentSwap (fragment: String) {
        when (fragment) {
            getString(R.string.transition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit()
                activeFragment = fragment1
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.condition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                activeFragment = fragment2
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.calendar) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                activeFragment = fragment3
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.mailtracker) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment4).commit()
                activeFragment = fragment4
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.diettracker) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment5).commit()
                activeFragment = fragment5
                backbutton.visibility = View.INVISIBLE
            }
            getString(R.string.editaboutme) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment6).addToBackStack("tag").commit()
                activeFragment = fragment6
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.editmycondition) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment7).addToBackStack("tag").commit()
                activeFragment = fragment7
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.editmymedication) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment8).addToBackStack("tag").commit()
                activeFragment = fragment8
                backbutton.visibility = View.VISIBLE
            }
            getString(R.string.editprofilepicture) -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment9).addToBackStack("tag").commit()
                activeFragment = fragment9
                backbutton.visibility = View.VISIBLE
            }
        }

    }

    fun fragmentHide (fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment).hide(fragment).commit()
    }


}
