package mostafa.example.com.practicekotlin.service

import mostafa.example.com.practicekotlin.MainApplication
import mostafa.example.com.practicekotlin.Model.Reporistory
import rx.Observable
import java.lang.reflect.Array
import javax.inject.Inject
import kotlin.collections.ArrayList

class GithubService {
    @Inject
    lateinit var gitHubApiService: GitHubApiService

    constructor(){
        MainApplication.app.inject(this)
    }

    fun searchRepo(query :String , sortBy : String): Observable<List<Reporistory>>{
        if(query.isBlank())
            return Observable.just(ArrayList())
        else {
            return gitHubApiService.searchRepos(query,sortBy).map { it.items }
        }
    }

    fun getRepositoryReadme(owner: String, repository: String) = gitHubApiService.getRepositoryReadme(owner, repository)

}