package mostafa.example.com.practicekotlin.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_repository_detail.*
import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.Model.Reporistory
import mostafa.example.com.practicekotlin.Model.RepositoryReadme
import mostafa.example.com.practicekotlin.R
import mostafa.example.com.practicekotlin.extension.*
import mostafa.example.com.practicekotlin.presenter.DetailsPresenter
import mostafa.example.com.practicekotlin.presenter.DetailsPresenterImp
import mostafa.example.com.practicekotlin.service.GithubService
import mostafa.example.com.practicekotlin.view.DetailsView

import timber.log.Timber

class RepositoryDetailActivity : AppCompatActivity(), DetailsView {

    lateinit var repo:Reporistory
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_detail)
        repo=intent.getParcelableExtra("repo")
        setSupportActionBar(detailsToolbar)
        supportActionBar?.title = repo.full_name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadRepositoryImage(repo.owner.avatar_url)
        repositoryDetailFab.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repo.html_url))) }
        repositoryDetailWebView.setProgressChangedListener { progress -> repositoryDetailProgressBar.showIf(progress < 100) }
        initPresenter()
    }

    private fun initPresenter() {
        detailsPresenter=DetailsPresenterImp(this)
        detailsPresenter.getRepoDetails(repo.owner.login, repo.name)
    }

    private fun loadRepositoryImage(avatar_url: String) {
        repositoryDetailImage.loadUrl(avatar_url) {
            onSuccess {
                setToolbarColorFromImage()
            }
            onError {
                Timber.e("Failed to load image")
                toolbarLayout.visibility = View.VISIBLE
            }
        }

    }

    private fun setToolbarColorFromImage() {
        repositoryDetailImage.generatePlatte gen@ {
            val swatch = it.mutedSwatch ?: it.vibrantSwatch ?: it.lightMutedSwatch ?: it.lightVibrantSwatch ?: return@gen
            val backgroundColor = swatch.rgb
            val titleTextColor = swatch.titleTextColor

            detailsToolbar.setTitleTextColor(titleTextColor)
            toolbarLayout.circularReveal(backgroundColor)
            toolbarLayout.setContentScrimColor(backgroundColor)
        }
    }

    override fun onSuccessLoadDetails(repositoryReadme: RepositoryReadme) {
        repositoryDetailWebView.loadUrl(repositoryReadme.html_url)
    }

    override fun onError(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        repositoryDetailProgressBar.hide()
    }
}
