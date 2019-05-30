package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chester.ac.uk.nextgenappandroid.R


class DietTrackerAddItem : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_tracker_add_item, container, false)
    }


}
