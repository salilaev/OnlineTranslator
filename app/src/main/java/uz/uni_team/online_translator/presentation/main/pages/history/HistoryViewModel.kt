package uz.uni_team.online_translator.presentation.main.pages.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.uni_team.online_translator.data.local.dao.TranslatorDao
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.data.local.entity.TranslatorEntity
import uz.uni_team.online_translator.data.repository.TranslatorRepository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val translatorDao: TranslatorDao,
    private val repository: TranslatorRepository
) : ViewModel() {
    val translatorList = translatorDao.getAllTranslations()

    fun updateFavouriteStatus(word:TranslatorEntity){
        viewModelScope.launch {
            repository.updateFavouriteStatus(!word.isFavourite,word.id)
        }
    }
}