package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment : Fragment() {

    private val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var month = calendarView.currentDate.get(Calendar.MONTH)
        var year = calendarView.currentDate.get(Calendar.YEAR)
        tvDate.text = "${months[month]} $year"

        imPrevMonth.setOnClickListener {
            calendarView.decrementMonth()

            month = calendarView.currentDate.get(Calendar.MONTH)
            year = calendarView.currentDate.get(Calendar.YEAR)
            tvDate.text = "${months[month]} $year"
        }

        imNextMonth.setOnClickListener {
            calendarView.incrementMonth()

            month = calendarView.currentDate.get(Calendar.MONTH)
            year = calendarView.currentDate.get(Calendar.YEAR)
            tvDate.text = "${months[month]} $year"
        }

        calendarView.setOnTouchListener { v, event ->

            if(event != null) {
                if (event.action == MotionEvent.ACTION_DOWN) {

                    val mX = event.x
                    val mY = event.y
                    for (cell in (v as MyMetaCalendarView).cells) {

                        if (cell != null) {
                            val rectF = cell.getRectF()
                            val isInside = mX >= rectF.left && mX <= rectF.right && mY >= rectF.top && mY <= rectF.bottom

                            if (isInside) {

                                val bundle = Bundle()
                                bundle.putString("calendarDate", formatter.format(cell.date))
                                (activity as MainActivity).showFragment(FragmentType.CALENDAR_EXPANDED, bundle, FragmentType.CALENDAR, true)
                            }
                        }

                    }

                }
            }

            true
        }
    }


}
