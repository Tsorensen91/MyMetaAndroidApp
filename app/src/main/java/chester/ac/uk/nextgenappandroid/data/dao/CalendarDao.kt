package chester.ac.uk.nextgenappandroid.data.dao

import android.arch.persistence.room.*
import chester.ac.uk.nextgenappandroid.data.entity.CalendarEntity

@Dao
interface CalendarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCalendar(calendar: CalendarEntity)

    @Update
    fun updateCalendar(calendar: CalendarEntity)

    @Delete
    fun deleteCalendar(calendar: CalendarEntity)
}