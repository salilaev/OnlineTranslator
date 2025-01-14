package uz.uni_team.online_translator.data.repository


import uz.uni_team.online_translator.data.local.dao.LanguageDao
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.data.remote.service.LanguageService
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val dao: LanguageDao,
    private val languageService: LanguageService,
) : LanguageRepository {

    override suspend fun getLanguages(): List<LanguageEntity> {

        val cachedLanguages = dao.getAllLanguages()

        if (cachedLanguages.isNotEmpty()) return cachedLanguages

        val remoteLanguages = languageService.getLanguages()

        val entities = remoteLanguages.map { response ->
            LanguageEntity(
                languageCode = response.code,
                languageName = response.name
            )
        }

        dao.insertLanguages(entities)

        return dao.getAllLanguages()
    }


}