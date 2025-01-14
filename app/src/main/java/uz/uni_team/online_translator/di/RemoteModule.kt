package uz.uni_team.online_translator.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import uz.uni_team.online_translator.data.remote.service.LanguageService
import uz.uni_team.online_translator.data.remote.service.TranslatorService
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object RemoteModule {
    private val json = Json {
        ignoreUnknownKeys = true
        this.isLenient = true
    }

    @Provides
    @Singleton
    @Named("translator")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.from-to.uz/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    @Named("language")
    fun provideLanguageRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cdn.from-to.uz/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    //schot 15000
    //+3000 = 18 000
    //-2000 = 13 000


    //race condition

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(30_000, TimeUnit.SECONDS)
            .writeTimeout(30_000, TimeUnit.SECONDS)
            .connectTimeout(30_000, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @[Provides Singleton]
    fun provideTranslatorService(@Named("translator") retrofit: Retrofit): TranslatorService = retrofit.create(TranslatorService::class.java)

    @[Provides Singleton]
    fun provideLanguageService(@Named("language") languageRetrofit: Retrofit): LanguageService = languageRetrofit.create(LanguageService::class.java)

}