package uz.uni_team.online_translator.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translate_words")
data class TranslatorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "from_language") val fromLanguage: String,
    @ColumnInfo(name = "to_language") val toLanguage: String,
    @ColumnInfo(name = "un_translated_text") val unTranslatedText: String,
    @ColumnInfo(name = "translated_text") val translatedText: String,
    @ColumnInfo(name = "result_case") val resultCase: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean = false,
)
