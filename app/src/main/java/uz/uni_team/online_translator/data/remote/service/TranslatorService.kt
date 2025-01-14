package uz.uni_team.online_translator.data.remote.service

import retrofit2.http.Body
import retrofit2.http.POST
import uz.uni_team.online_translator.data.remote.request.TranslateTextBody
import uz.uni_team.online_translator.data.remote.response.TranslateTextResponse

interface TranslatorService {

    @POST(TRANSLATE_PATH)
    suspend fun translateText(@Body translateTextBody: TranslateTextBody):TranslateTextResponse

    companion object {
        const val TRANSLATE_PATH = "api/v1/translate"
    }

}