package chester.ac.uk.nextgenappandroid.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CalendarEventEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int
)

@Entity
data class CalendarEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int
)