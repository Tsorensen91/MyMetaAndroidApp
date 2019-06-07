package chester.ac.uk.nextgenappandroid.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import chester.ac.uk.nextgenappandroid.data.dao.*
import chester.ac.uk.nextgenappandroid.data.entity.*

@Database(entities = [
    CalendarEntity::class,
    ConditionEntity::class,
    DietEntity::class,
    MailEntity::class,
    TransitionEntity::class],
        version = 1)
abstract class MetaDatabase : RoomDatabase() {

    abstract fun calendarDao(): CalendarDao
    abstract fun conditionDao(): ConditionDao
    abstract fun dietDao(): DietDao
    abstract fun mailDao(): MailDao
    abstract fun transitionDao(): TransitionDao

    companion object {
        var INSTANCE: MetaDatabase? = null

        fun getAppDataBase(context: Context): MetaDatabase? {
            if (INSTANCE == null) {
                synchronized(MetaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MetaDatabase::class.java, "metaDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

}