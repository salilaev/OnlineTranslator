package uz.uni_team.online_translator.data.remote.service
import retrofit2.http.GET
import retrofit2.http.POST
import uz.uni_team.online_translator.data.remote.response.LanguageResponse

interface LanguageService {

   @GET(LANGUAGE_PATH)
   suspend fun getLanguages(): List<LanguageResponse>

    companion object{
        const val LANGUAGE_PATH = "languages.json"
    }
}