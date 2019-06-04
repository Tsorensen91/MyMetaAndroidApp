package chester.ac.uk.nextgenappandroid.transition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.firstcard_layout.*
import kotlinx.android.synthetic.main.fragment_transition_tracker.*

class TransitionTrackerFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: TransitionRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_tracker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        rvTransitionTracker.layoutManager = layoutManager
        adapter = TransitionRecyclerAdapter(activity as MainActivity)
        rvTransitionTracker.adapter = adapter

    }




}
