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

    val fragment1 = TransitionTrackerFragment()
    val fragment2 = ConditionFragment()
    val fragment3 = CalendarFragment()
    val fragment4 = MailtrackerFragment()
    val fragment5 = DiettrackerFragment()
    var activeFragment:Fragment = fragment3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment1).hide(fragment1).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment2).hide(fragment2).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment4).hide(fragment4).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment5).hide(fragment5).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment3).commit()

        navigation.selectedItemId = R.id.calendarButton
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.transitionButton -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit()
                    activeFragment = fragment1
                }

                R.id.conditionButton -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                    activeFragment = fragment2
                }
                R.id.calendarButton -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit()
                    activeFragment = fragment3
                }
                R.id.mailtrackerButton -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment4).commit()
                    activeFragment = fragment4
                }
                R.id.diettrackerButton -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment5).commit()
                    activeFragment = fragment5
                }

            }
            true

        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
