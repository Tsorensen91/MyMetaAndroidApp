package chester.ac.uk.nextgenappandroid


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_condition.*


class ConditionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_condition, container, false)

    }

    override fun onResume() {
        super.onResume()
        editAboutMe.setOnClickListener(){
            fragmentSwap(ConditionEditAbout())
        }
    }

    fun fragmentSwap (fragment: Fragment) {
        fragmentManager!!.beginTransaction().hide(ConditionFragment()).show(fragment).commit()

    }


}
