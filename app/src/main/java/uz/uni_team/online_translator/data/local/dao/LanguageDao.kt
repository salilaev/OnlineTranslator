package uz.uni_team.online_translator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.uni_team.online_translator.data.local.entity.LanguageEntity

@Dao
interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguages(languages: List<LanguageEntity>)

    @Query("SELECT * FROM languages")
    suspend fun getAllLanguages(): List<LanguageEntity>

    @Query("DELETE FROM languages")
    suspend fun deleteAllLanguages()


}