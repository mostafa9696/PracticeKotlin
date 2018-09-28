package mostafa.example.com.practicekotlin.dagger

import dagger.Module
import dagger.Provides
import mostafa.example.com.practicekotlin.BuildConfig
import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.service.GitHubApiService
import mostafa.example.com.practicekotlin.service.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val mainApp: MainApplication) {

    @Provides
    @Singleton
    fun provideMainApp()=mainApp

    @Provides
    @Singleton
    fun provideGithubAPIservice():GitHubApiService{
        val interceptor=HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val client=OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesGitHubService() = GithubService()
}