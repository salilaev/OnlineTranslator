package uz.uni_team.online_translator.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.uni_team.online_translator.data.local.AppDatabase
import uz.uni_team.online_translator.data.local.dao.LanguageDao
import uz.uni_team.online_translator.data.local.dao.TranslatorDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @[Provides Singleton]
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getAppDatabase(context)

    @[Provides Singleton]
    fun provideTranslatorDao(appDatabase: AppDatabase): TranslatorDao = appDatabase.getTranslatorDao()

    @[Provides Singleton]
    fun provideLanguageDao(appDatabase: AppDatabase): LanguageDao = appDatabase.getLanguageDao()

}