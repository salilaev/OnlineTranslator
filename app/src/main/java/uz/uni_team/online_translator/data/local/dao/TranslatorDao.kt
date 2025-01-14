package uz.uni_team.online_translator.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.uni_team.online_translator.data.local.entity.TranslatorEntity

@Dao
interface TranslatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertText(translatorEntity: TranslatorEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateText(translatorEntity: TranslatorEntity)

    @Delete
    suspend fun deleteText(translatorEntity: TranslatorEntity)

    @Query("SELECT * FROM translate_words")
    fun getAllTranslations(): LiveData<List<TranslatorEntity>>

    @Query(
        """
            SELECT * 
            FROM translate_words
            WHERE from_language=:langFrom
            AND to_language =:langTo
            AND un_translated_text =:text
            AND result_case =:resultCase
        """
    )
    suspend fun getTranslatedText(langFrom: String, langTo: String, text: String, resultCase: String): TranslatorEntity?


    @Query(
        """
            UPDATE translate_words
            SET is_favourite =:favourite
            WHERE id =:id
        """
    )
    suspend fun updateFavouriteStatus(favourite: Boolean, id: Int)

}