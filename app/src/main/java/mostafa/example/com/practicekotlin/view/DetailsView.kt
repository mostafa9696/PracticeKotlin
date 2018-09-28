package mostafa.example.com.practicekotlin.view

import mostafa.example.com.practicekotlin.Model.RepositoryReadme

interface DetailsView {
    fun onSuccessLoadDetails(repositoryReadme: RepositoryReadme)
    fun onError(msg : String)

}