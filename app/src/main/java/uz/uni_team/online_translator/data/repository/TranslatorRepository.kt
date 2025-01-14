package uz.uni_team.online_translator.data.repository

interface TranslatorRepository {

    suspend fun translate(langFrom: String, langTo: String, resultCase: String, text: String): String

    suspend fun updateFavouriteStatus(isFavourite: Boolean, id: Int)

}