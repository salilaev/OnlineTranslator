package uz.uni_team.online_translator.data.repository

import uz.uni_team.online_translator.data.local.dao.TranslatorDao
import uz.uni_team.online_translator.data.local.entity.TranslatorEntity
import uz.uni_team.online_translator.data.remote.request.TranslateTextBody
import uz.uni_team.online_translator.data.remote.request.TranslateTextRequest
import uz.uni_team.online_translator.data.remote.service.TranslatorService
import javax.inject.Inject

class TranslatorRepositoryImpl @Inject constructor(
    private val dao: TranslatorDao,
    private val translatorService: TranslatorService,
) : TranslatorRepository {

    override suspend fun translate(
        langFrom: String,
        langTo: String,
        resultCase: String,
        text: String,
    ): String {

        val data = dao.getTranslatedText(langFrom, langTo, text, resultCase)
        if (data != null) return data.translatedText

        val request = TranslateTextRequest(
            langFrom = langFrom,
            langTo = langTo,
            resultCase = resultCase,
            text = text
        )

        val translateTextBody = TranslateTextBody(request)
        val response = translatorService.translateText(translateTextBody)

        dao.insertText(
            TranslatorEntity(
                id = 0,
                fromLanguage = langFrom,
                toLanguage = langTo,
                unTranslatedText = text,
                resultCase = resultCase,
                translatedText = response.result ?: throw NullPointerException()
            )
        )

        return dao.getTranslatedText(langFrom, langTo, text, resultCase)?.translatedText ?: throw NullPointerException()
    }

    override suspend fun updateFavouriteStatus(isFavourite: Boolean, id: Int) {
        dao.updateFavouriteStatus(isFavourite, id)
    }
}