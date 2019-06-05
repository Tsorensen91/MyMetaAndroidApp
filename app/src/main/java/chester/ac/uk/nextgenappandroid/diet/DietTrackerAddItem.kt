package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_diet_tracker_add_item.*


class DietTrackerAddItem : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
        return inflater.inflate(chester.ac.uk.nextgenappandroid.R.layout.fragment_diet_tracker_add_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submitButton.setOnClickListener {
//            (activity as MainActivity).fragmentSwap(getString(R.string.diettracker), "food, drink")
            (activity as MainActivity).showFragment(FragmentType.DIET_TRACKER)

//            val mealName = etMealName.text
//            (activity as MainActivity).fragmentSwap(getString(R.string.diettracker), ""+mealName+", 10:02pm, 200, 500")
        }
    }

}

