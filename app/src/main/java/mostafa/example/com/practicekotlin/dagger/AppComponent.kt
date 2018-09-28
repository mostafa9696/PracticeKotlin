package mostafa.example.com.practicekotlin.dagger

import dagger.Component
import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.presenter.MainPresenterImp
import mostafa.example.com.practicekotlin.service.GithubService
import mostafa.example.com.practicekotlin.activity.RepositoryDetailActivity
import mostafa.example.com.practicekotlin.presenter.DetailsPresenterImp
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(mainApp: MainApplication)

    fun inject(mainPresenterImp: MainPresenterImp)

    fun inject(gitHubService:GithubService)

    fun inject(detailsPresenterImp: DetailsPresenterImp)
}