package chester.ac.uk.nextgenappandroid.calendar

import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

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

object CalendarData {

    val calendarEntries = CopyOnWriteArrayList<CalendarEntry>()

    private fun addEntry(entry: CalendarEntry) {
        calendarEntries.add(entry)
    }

    fun addEvent(date: Date, event: CalendarEvent) {

        if (calendarEntries.size > 0)
            for (entry in calendarEntries) {
                if (entry.date.date == date.date && entry.date.month == date.month && entry.date.year == date.year) {
                    entry.addEvent(event)
                } else {
                    val e = CalendarEntry(date)
                    e.addEvent(event)

                    addEntry(e)

                }
            } else {
            val e = CalendarEntry(date)
            e.addEvent(event)

            addEntry(e)
        }

    }

}