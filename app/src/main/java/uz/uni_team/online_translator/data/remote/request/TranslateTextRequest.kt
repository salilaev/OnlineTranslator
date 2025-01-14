package uz.uni_team.online_translator.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateTextRequest(
    @SerialName("lang_from") val langFrom: String,
    @SerialName("lang_to") val langTo: String,
    @SerialName("resultCase") val resultCase: String,
    @SerialName("text") val text: String,
)

@Serializable
data class TranslateTextBody(
    @SerialName("body") val body: TranslateTextRequest,
)


//{
//  "body": {
//    "lang_from": "uz",
//    "lang_to": "kaa",
//    "resultCase": "cyrill",
//    "text": "Salom"
//  }
//}
