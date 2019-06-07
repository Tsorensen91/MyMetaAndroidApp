package chester.ac.uk.nextgenappandroid.calendar

import android.app.Notification
import java.util.*

enum class EventType {
    APPOINTMENT,
    NUTRITION,
    TRANSPORT,
    WORK
}

class CalendarEvent(val title: String, val startTime: Date, val endTime: Date, val type: EventType, sendNotification: Boolean, location: String) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        return startTime.compareTo(other)
    }
}

class CalendarEntry(val date: Date) {

    val events = mutableListOf<CalendarEvent>()

    fun addEvent(event: CalendarEvent) {
        events.add(event)
        events.sortBy { it.startTime }
    }
}