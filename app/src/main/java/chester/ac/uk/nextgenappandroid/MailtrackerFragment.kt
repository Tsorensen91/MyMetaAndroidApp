package chester.ac.uk.nextgenappandroid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mailtracker.*
import kotlinx.android.synthetic.main.fragment_transition_tracker.*

class MailtrackerFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: MailRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_mailtracker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        rvMailTracker.layoutManager = layoutManager
        adapter = MailRecyclerAdapter()
        rvMailTracker.adapter = adapter

    }


}
