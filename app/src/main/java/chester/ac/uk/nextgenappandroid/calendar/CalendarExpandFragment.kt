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
import chester.ac.uk.nextgenappandroid.OnShowFragment
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar_expand.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarExpandFragment : Fragment(), OnShowFragment {

    private lateinit var cExpandLayout: RecyclerView.LayoutManager

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private var date = Date()

    private val list = mutableListOf<CalendarEvent>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_expand, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cExpandLayout = LinearLayoutManager(context)

        val entries = CalendarData.calendarEntries
        for(entry in entries) {
            if(entry.date.date == date.date && entry.date.month == date.month && entry.date.year == date.year) {
                list.addAll(entry.events)
            }
        }
        rvCalendarHourSlots.layoutManager = cExpandLayout
        rvCalendarHourSlots.adapter = CalendarAdapter(list)

        addCalendarEventButton.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("calendarExpandDate", formatter.format(date))

            (activity as MainActivity).showFragment(FragmentType.CALENDAR_ADD, bundle, FragmentType.CALENDAR_EXPANDED, true)
        }

    }

    override fun onShow(bundle: Bundle) {
        if (bundle.getString("from") == FragmentType.CALENDAR.desc) {

            date = formatter.parse(bundle.getString("calendarDate"))

        }
    }

}
