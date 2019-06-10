package chester.ac.uk.nextgenappandroid.calendar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chester.ac.uk.nextgenappandroid.FragmentType
import chester.ac.uk.nextgenappandroid.MainActivity
import chester.ac.uk.nextgenappandroid.OnShowFragment
import chester.ac.uk.nextgenappandroid.R
import kotlinx.android.synthetic.main.fragment_calendar_add.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarAdd : Fragment(), OnShowFragment {

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private var date = Date()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarAddSubmit.setOnClickListener {

            val title = etAppointmentTitle.text.toString()
            val startTimeH = appointmentStartHourSpinner.selectedItem
            val startTimeM = appointmentStartMinuteSpinner.selectedItem
            val endTimeH = appointmentEndHourSpinner.selectedItem
            val endTimeM = appointmentEndMinuteSpinner.selectedItem
            val appointmentType = when (chooseEventTypeSpinner.selectedItem) {
                "Appointment" -> EventType.APPOINTMENT
                "Nutrition" -> EventType.NUTRITION
                "Transport" -> EventType.TRANSPORT
                "Work" -> EventType.WORK
                else -> EventType.APPOINTMENT
            }
            val pushNotifications = switch1.isChecked
            val location = etEnterLocation.text.toString()

            val cal = Calendar.getInstance()
            cal.set(Calendar.DATE, date.date)
            cal.set(Calendar.MONTH, date.month)
            cal.set(Calendar.YEAR, date.year)
            cal.set(Calendar.HOUR_OF_DAY, startTimeH.toString().toInt())
            cal.set(Calendar.MINUTE, startTimeM.toString().toInt())
            val startTimeDate = cal.time

            cal.set(Calendar.HOUR_OF_DAY, endTimeH.toString().toInt())
            cal.set(Calendar.MINUTE, endTimeM.toString().toInt())
            val endTimeDate = cal.time

            CalendarData.addEvent(date, CalendarEvent(title, startTimeDate, endTimeDate, appointmentType, pushNotifications, location))

            val bundle = Bundle()
            bundle.putString("calendarDate", formatter.format(date))
            (activity as MainActivity).showFragment(FragmentType.CALENDAR_EXPANDED, bundle, FragmentType.CALENDAR_ADD, true)
        }
    }

    override fun onShow(bundle: Bundle) {
        if(bundle.getString("from") == FragmentType.CALENDAR_EXPANDED.desc) {

            date = formatter.parse(bundle.getString("calendarDate"))

        }
    }


}
