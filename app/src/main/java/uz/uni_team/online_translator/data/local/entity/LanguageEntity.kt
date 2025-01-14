package uz.uni_team.online_translator.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey @ColumnInfo(name = "language_code") val languageCode: String,
    @ColumnInfo(name = "language_name") val languageName: String,
)
