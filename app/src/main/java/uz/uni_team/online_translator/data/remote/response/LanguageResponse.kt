package uz.uni_team.online_translator.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LanguageResponse(
    val name: String,
    val code: String
)