package chester.ac.uk.nextgenappandroid.transition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_transition_tracker_add.*


class TransitionTrackerAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        layoutManager = LinearLayoutManager(context)
        rvTransitionProcess.layoutManager = layoutManager
        adapter = TransitionAddRecyclerAdapter()
        rvTransitionProcess.adapter = adapter



        return inflater.inflate(R.layout.fragment_transition_tracker_add, container, false)
    }

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: TransitionAddRecyclerAdapter


}
