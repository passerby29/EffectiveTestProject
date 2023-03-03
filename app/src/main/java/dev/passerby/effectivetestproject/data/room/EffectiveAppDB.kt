package dev.passerby.effectivetestproject.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("unused")
@Database(
    entities = [UserEntity::class, SearchWordsEntity::class],
    exportSchema = false,
    version = 2
)
abstract class EffectiveAppDB : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getSearchWordsDao(): SearchWordsDao

    companion object {
        @Volatile
        private var INSTANCE: EffectiveAppDB? = null
        fun getDB(context: Context): EffectiveAppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EffectiveAppDB::class.java,
                    "effective_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}