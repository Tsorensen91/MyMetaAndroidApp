package chester.ac.uk.nextgenappandroid.calendar


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
import kotlinx.android.synthetic.main.fragment_calendar_expand.*


class CalendarExpandFragment : Fragment() {

    private lateinit var cExpandLayout: RecyclerView.LayoutManager
    private var expandAdapter = CalendarAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_expand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cExpandLayout = LinearLayoutManager(context)

        rvCalendarHourSlots.layoutManager = cExpandLayout
        rvCalendarHourSlots.adapter = expandAdapter

        addCalendarEventButton.setOnClickListener {
            (activity as MainActivity).showFragment(FragmentType.CALENDAR_ADD, true)
        }

    }

}
