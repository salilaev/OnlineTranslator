package uz.uni_team.online_translator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.uni_team.online_translator.data.repository.LanguageRepository
import uz.uni_team.online_translator.data.repository.LanguageRepositoryImpl
import uz.uni_team.online_translator.data.repository.TranslatorRepository
import uz.uni_team.online_translator.data.repository.TranslatorRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTranslatorRepository(impl: TranslatorRepositoryImpl): TranslatorRepository

    @Binds
    fun bindLanguageRepository(impl: LanguageRepositoryImpl): LanguageRepository

}