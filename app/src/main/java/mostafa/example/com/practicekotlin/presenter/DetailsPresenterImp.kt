package mostafa.example.com.practicekotlin.presenter


import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.service.GithubService
import mostafa.example.com.practicekotlin.view.DetailsView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class DetailsPresenterImp : DetailsPresenter {
    lateinit var detailsView: DetailsView

    @Inject
    lateinit var githubService: GithubService

    constructor(detailsView: DetailsView){
        this.detailsView=detailsView
        MainApplication.app.inject(this)
    }

    override fun getRepoDetails(ownerName: String, repoName: String) {
        githubService.getRepositoryReadme(ownerName,repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            detailsView.onSuccessLoadDetails(it)
                        }, {
                            detailsView.onError("Failed, try again !")
                           }
                )
    }
}