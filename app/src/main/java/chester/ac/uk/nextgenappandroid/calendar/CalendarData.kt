package chester.ac.uk.nextgenappandroid.calendar

import java.util.*

object CalendarData {

    val calendarEntries = mutableListOf<CalendarEntry>()

    fun addEntry(entry: CalendarEntry) {
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