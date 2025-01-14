package uz.uni_team.online_translator.presentation.main.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.data.repository.LanguageRepository
import uz.uni_team.online_translator.data.repository.TranslatorRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val translatorRepository: TranslatorRepository,
    private val languageRepository: LanguageRepository,
) : ViewModel() {

    val text = MutableLiveData<String>()

    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String> get() = _translatedText


    private val _fromLanguageText: MutableLiveData<LanguageEntity> = MutableLiveData()
    val fromLanguageText: LiveData<LanguageEntity> get() = _fromLanguageText

    private val _toLanguageText: MutableLiveData<LanguageEntity> = MutableLiveData()
    val toLanguageText: LiveData<LanguageEntity> get() = _toLanguageText

    private val _languages = MutableLiveData<List<LanguageEntity>>()
    val languages: LiveData<List<LanguageEntity>> get() = _languages

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean> = _loadingState

    init {
        onClearText()
    }

    private fun onClearText() {
        text.value = ""
    }

    fun translateText(text: String) {
        viewModelScope.launch {
            _loadingState.value = true
            val fromLanguage = fromLanguageText.value?.languageCode ?: "rus_Cyrl"
            val toLanguage = toLanguageText.value?.languageCode ?: "kaa"

            val result = translatorRepository.translate(
                langFrom = fromLanguage,
                langTo = toLanguage,
                resultCase = "kaa",
                text = text
            )

            _translatedText.value = result
            _loadingState.value = false
            println("Result:$result")
        }
    }

    fun getLanguages() {
        viewModelScope.launch {
            _loadingState.value = true
            val languages = languageRepository.getLanguages()
            setFromLanguage(languages.component1())
            setToLanguage(languages.component2())
            _languages.value = languages
            _loadingState.value = false
        }
    }

    fun setFromLanguage(language: LanguageEntity) {
        _fromLanguageText.value = language
    }

    fun setToLanguage(language: LanguageEntity) {
        _toLanguageText.value = language
    }

    //from
    //to
    //loading
    //result
}