package chester.ac.uk.nextgenappandroid.calendar

import android.app.Notification
import java.util.*

enum class EventType {
    APPOINTMENT,
    NUTRITION,
    TRANSPORT,
    WORK
}

class CalendarEvent(title: String, startTime: Date, endTime: Date, type: EventType, sendNotification: Boolean, location: String)

class CalendarEntry(date: Date) {

    val events = listOf<CalendarEvent>()

}