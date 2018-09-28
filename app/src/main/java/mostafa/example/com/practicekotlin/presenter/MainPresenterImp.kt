package mostafa.example.com.practicekotlin.presenter

import android.content.Context
import android.widget.EditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.trello.rxlifecycle.ActivityLifecycleProvider
import kotlinx.android.synthetic.main.activity_main.*
import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.R
import mostafa.example.com.practicekotlin.extension.hide
import mostafa.example.com.practicekotlin.extension.show
import mostafa.example.com.practicekotlin.extension.subscribeOnIo
import mostafa.example.com.practicekotlin.extension.subscribeUntilDestroy
import mostafa.example.com.practicekotlin.service.GithubService
import mostafa.example.com.practicekotlin.view.MainView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenterImp : MainPresenter {
    lateinit var mainView: MainView
    lateinit var context: ActivityLifecycleProvider
    @Inject
    lateinit var gitHubService: GithubService

    constructor(mainView: MainView, context: ActivityLifecycleProvider){
        this.mainView=mainView
        this.context=context
        MainApplication.app.inject(this)
    }

    override fun searchRepo(searchText: EditText) {
        RxTextView.textChanges(searchText)
                .doOnNext { mainView.showProgress() }
                .sample(1, TimeUnit.SECONDS)
                .switchMap { gitHubService.searchRepo(it.toString(),"stars").subscribeOnIo() }
                .subscribeUntilDestroy(context) {
                    onNext {
                        mainView.hideProgress()
                        mainView.setSearchData(it)
                    }
                    onError {
                        mainView.hideProgress()
                        mainView.onErrr(R.string.error.toString())
                    }
                }
    }
}