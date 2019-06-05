package chester.ac.uk.nextgenappandroid.condition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity

import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_my_condition.*

class MyConditionEditFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_condition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submitButton.setOnClickListener{

//            (activity as MainActivity).fragmentSwap(getString(R.string.condition), etCondition.text.toString())
            (activity as MainActivity).showFragment(FragmentType.CONDITION_HUB)
        }
    }


}
