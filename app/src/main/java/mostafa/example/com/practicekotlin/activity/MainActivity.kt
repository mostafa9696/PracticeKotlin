package mostafa.example.com.practicekotlin.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import mostafa.example.com.practicekotlin.Model.Reporistory
import mostafa.example.com.practicekotlin.R
import mostafa.example.com.practicekotlin.extension.*
import mostafa.example.com.practicekotlin.presenter.MainPresenter
import mostafa.example.com.practicekotlin.presenter.MainPresenterImp

import mostafa.example.com.practicekotlin.view.MainView
import mostafa.example.com.practicekotlin.view.RepoAdapter

class MainActivity : BaseActivity() , MainView {

    override fun showProgress() {
        mainResultsSpinner.show()
    }

    override fun hideProgress() {
        mainResultsSpinner.hide()
    }

    override fun onErrr(msg: String) {

        alert {
            setTitle(msg)
            setMessage(R.string.search_repositories_error)
            setPositiveButton(android.R.string.ok, null)
        }
    }
    override fun setSearchData(repos : List<Reporistory>) {
        repoAdapter.loadRepositories(repos)
    }


    lateinit var repoAdapter: RepoAdapter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter=MainPresenterImp(this, RxAppCompatActivity())
        setSupportActionBar(toolbar)
        setUpRecyclerView()
        setUpSearchView()
    }

    fun setUpRecyclerView(){
        repoAdapter= RepoAdapter(this)
        mainResultsRecycler.adapter=repoAdapter
        mainResultsRecycler.layoutManager=LinearLayoutManager(this)
    }

    fun setUpSearchView() {
        val searchText=mainSearchCardView.getEditText()
        searchText.setHint("Enter search repository")
        presenter.searchRepo(searchText)
    }


}
