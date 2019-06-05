package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar_expand.*
import java.util.*


class CalendarFragment : Fragment() {

    val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }



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
