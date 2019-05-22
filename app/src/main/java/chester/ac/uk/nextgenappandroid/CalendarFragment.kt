package chester.ac.uk.nextgenappandroid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalendarFragment : Fragment() {

    var sdf = SimpleDateFormat("EEEE,d MMM,yyyy")
    var cells = ArrayList<Date>()
    var calendar : Calendar = Calendar.getInstance()
    var daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        updateCalendar(sdf.format(calendar.time))
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    fun updateCalendar(events: String) {


        calendar.set(Calendar.DAY_OF_MONTH, 1)
        var monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 2


        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)


        while (cells.size < daysInMonth ){
            cells.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        //calendar_grid.adapter = CalendarAdapter( getContext(), cells, events)

        //date_display_date.setText(sdf.format(calendar.time))


    }


}
