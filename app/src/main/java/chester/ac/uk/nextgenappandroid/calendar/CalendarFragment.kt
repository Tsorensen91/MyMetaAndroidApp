package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar.*


class CalendarFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imPrevMonth.setOnClickListener {
            my_meta_calendar_view.decrementMonth()

            tvDate.text = my_meta_calendar_view.calendarDate.toString()
        }

        imNextMonth.setOnClickListener {
            my_meta_calendar_view.incrementMonth()

            tvDate.text = my_meta_calendar_view.calendarDate.toString()
        }

    }


}
