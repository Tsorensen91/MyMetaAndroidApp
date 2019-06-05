package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar_expand.*


class CalendarExpandFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        layoutManager = LinearLayoutManager(context)

        rvCalendarHourSlots.layoutManager = layoutManager

        adapter = CalendarAdapter()
        rvCalendarHourSlots.adapter = adapter

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_expand, container, false)
    }

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var adapter: CalendarAdapter


}
