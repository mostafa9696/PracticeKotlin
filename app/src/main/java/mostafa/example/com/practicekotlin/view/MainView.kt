package mostafa.example.com.practicekotlin.view

import mostafa.example.com.practicekotlin.Model.Reporistory

interface MainView {

    fun setSearchData(repos : List<Reporistory>)
    fun onErrr(msg : String)
    fun hideProgress()
    fun showProgress()
}