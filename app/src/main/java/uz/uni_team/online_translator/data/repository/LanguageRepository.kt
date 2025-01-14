package uz.uni_team.online_translator.data.repository

import uz.uni_team.online_translator.data.local.entity.LanguageEntity

interface LanguageRepository {
    suspend fun getLanguages(): List<LanguageEntity>
}