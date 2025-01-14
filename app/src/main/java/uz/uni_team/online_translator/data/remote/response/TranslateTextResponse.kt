package uz.uni_team.online_translator.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateTextResponse(
    @SerialName("result")
    val result: String?,
)
