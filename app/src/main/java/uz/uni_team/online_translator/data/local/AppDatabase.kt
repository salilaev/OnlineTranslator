package uz.uni_team.online_translator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.uni_team.online_translator.data.local.dao.LanguageDao
import uz.uni_team.online_translator.data.local.dao.TranslatorDao
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.data.local.entity.TranslatorEntity

@Database(entities = [TranslatorEntity::class, LanguageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTranslatorDao(): TranslatorDao
    abstract fun getLanguageDao(): LanguageDao

    companion object {
        private lateinit var database: AppDatabase

        @Synchronized
        fun getAppDatabase(context: Context): AppDatabase {
            if (!Companion::database.isInitialized) {
                database = Room.databaseBuilder(context, AppDatabase::class.java, "translator_master.db")
                    .build()
            }
            return database
        }
    }
}