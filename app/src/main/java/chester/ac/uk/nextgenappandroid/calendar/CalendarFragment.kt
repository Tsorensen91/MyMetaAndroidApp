package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.design.R.attr.layoutManager
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar_expand.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment : Fragment() {

    val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        layoutManager = LinearLayoutManager(context)

        rvCalendarHourSlots.layoutManager = layoutManager

        adapter = CalendarAapter()
        rvCalendarHourSlots.adapter = adapter

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var adapter: CalendarAapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val month = my_meta_calendar_view.currentDate.get(Calendar.MONTH)
        val year = my_meta_calendar_view.currentDate.get(Calendar.YEAR)
        tvDate.text = "${months[month]} $year"

        imPrevMonth.setOnClickListener {
            my_meta_calendar_view.decrementMonth()

            val month = my_meta_calendar_view.currentDate.get(Calendar.MONTH)
            val year = my_meta_calendar_view.currentDate.get(Calendar.YEAR)
            tvDate.text = "${months[month]} $year"
        }

        imNextMonth.setOnClickListener {
            my_meta_calendar_view.incrementMonth()

            val month = my_meta_calendar_view.currentDate.get(Calendar.MONTH)
            val year = my_meta_calendar_view.currentDate.get(Calendar.YEAR)
            tvDate.text = "${months[month]} $year"
        }
    }


}
