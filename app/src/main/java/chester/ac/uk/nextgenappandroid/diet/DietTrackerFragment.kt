package chester.ac.uk.nextgenappandroid.diet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_diet_tracker.*


class DietTrackerFragment : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        layoutManager = LinearLayoutManager(context)
        return inflater.inflate(R.layout.fragment_diet_tracker, container, false)

        rvDietTracker.layoutManager = layoutManager

        adapter = DietTrackerAdapter()
        rvDietTracker.adapter = adapter

    }

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var adapter: DietTrackerAdapter


}
