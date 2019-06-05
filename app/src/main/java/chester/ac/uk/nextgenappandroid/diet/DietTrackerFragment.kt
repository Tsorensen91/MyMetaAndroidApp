package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_diet_tracker.*


class DietTrackerFragment : Fragment() {

    private lateinit var dietLayoutManager: RecyclerView.LayoutManager

    private lateinit var dietAdapter: DietTrackerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_diet_tracker, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dietLayoutManager = LinearLayoutManager(context)

        rvDietTracker.layoutManager = dietLayoutManager

        dietAdapter = DietTrackerAdapter()
        rvDietTracker.adapter = dietAdapter

        addDietButton.setOnClickListener {
//            (activity as MainActivity).fragmentSwap(getString(R.string.diettrackeradd), "20/20/2020, breakfast, 10:02pm, 200, 500")
            (activity as MainActivity).showFragment(FragmentType.DIET_TRACKER_ADD_ITEM)
        }
    }

    fun updateList(list: List<DietTrackerDay> ){
        var mutable = mutableListOf<DietTrackerDay>()
        for (x in 0 until list.size) {
            mutable.add(list[x])
        }
        dietAdapter.list = mutable
        dietAdapter.notifyDataSetChanged()
    }




}
