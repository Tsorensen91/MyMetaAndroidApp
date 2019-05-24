package chester.ac.uk.nextgenappandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
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
    val fragment7 = myConditionFragment()
    val fragment8 = myMedicationFragment()

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

        for (x in 0 until list.size) {
            fragmentHide(list[x])
        }

        fragmentSwap(fragment3)

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    fragmentSwap(fragment1)
                }

                R.id.conditionButton -> {
                    fragmentSwap(fragment2)
                }
                R.id.calendarButton -> {
                    fragmentSwap(fragment3)
                }
                R.id.mailtrackerButton -> {
                    fragmentSwap(fragment4)
                }
                R.id.diettrackerButton -> {
                    fragmentSwap(fragment5)
                }
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun fragmentSwap (fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

    fun fragmentHide (fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment).hide(fragment).commit()
    }


}
