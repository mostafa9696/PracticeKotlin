package mostafa.example.com.practicekotlin.Model

data class RepositorySearchResults(
        val total_count: Long,
        val incomplete_results: Boolean,
        val items: List<Reporistory>
)