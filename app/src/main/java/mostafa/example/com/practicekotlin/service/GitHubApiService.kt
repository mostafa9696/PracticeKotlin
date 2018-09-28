package mostafa.example.com.practicekotlin.service

import mostafa.example.com.practicekotlin.Model.Reporistory
import mostafa.example.com.practicekotlin.Model.RepositoryOwner
import mostafa.example.com.practicekotlin.Model.RepositoryReadme
import mostafa.example.com.practicekotlin.Model.RepositorySearchResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface GitHubApiService {

    @GET("search/repositories")                                                            // Field is requires a mandatory parameter, @Query is optional paramter wich can be null
    fun searchRepos(@Query("q") inpQuery: String, @Query("sort") sortBy : String): Observable<RepositorySearchResults>     // make query on returned repos by filter them to search input string with q = inpQuery

    @GET("/repos/{owner}/{repo}")
    fun getRepo(@Path("owner") owner: String, @Path("repo") repoName : String) : Observable<Reporistory>

    @GET("/repos/{owner}/{repo}/readme")
    fun getRepositoryReadme(@Path("owner") owner: String, @Path("repo") repository: String): Observable<RepositoryReadme>

}