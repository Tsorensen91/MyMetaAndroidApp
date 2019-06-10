package chester.ac.uk.nextgenappandroid.calendar

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

object CalendarData {

    val calendarEntries = mutableListOf<CalendarEntry>()

    fun addEvent(date: Date, event: CalendarEvent) {
        val temp = mutableListOf<CalendarEntry>()

        if (calendarEntries.size > 0) {

            val iterator = calendarEntries.iterator()

            while (iterator.hasNext()) {
                val entry = iterator.next()
                if (entry.date.date == date.date && entry.date.month == date.month && entry.date.year == date.year) {
                    entry.addEvent(event)
                    calendarEntries.addAll(temp)
                    return
                }
            }

            val e = CalendarEntry(date)
            e.addEvent(event)
            temp.add(e)

        } else {
            val e = CalendarEntry(date)
            e.addEvent(event)

            temp.add(e)
        }

        calendarEntries.addAll(temp)
    }

}