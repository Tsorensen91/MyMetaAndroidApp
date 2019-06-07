package chester.ac.uk.nextgenappandroid.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MailEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int
)