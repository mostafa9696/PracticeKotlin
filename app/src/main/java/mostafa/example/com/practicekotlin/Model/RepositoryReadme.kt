package mostafa.example.com.practicekotlin.Model

data class RepositoryReadme(
        val type: String,
        val encoding: String,
        val size: Long,
        val name: String,
        val path: String,
        val sha: String,
        val url: String,
        val git_url: String,
        val html_url: String,
        val download_url: String
)