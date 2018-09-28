package mostafa.example.com.practicekotlin.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ocpsoft.pretty.time.PrettyTime
import kotlinx.android.synthetic.main.repository_item.view.*
import mostafa.example.com.practicekotlin.BR
import mostafa.example.com.practicekotlin.Model.Reporistory
import mostafa.example.com.practicekotlin.activity.RepositoryDetailActivity
import mostafa.example.com.practicekotlin.databinding.RepositoryItemBinding
import mostafa.example.com.practicekotlin.extension.format
import mostafa.example.com.practicekotlin.extension.loadUrl

class RepoAdapter(val context:Context): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    var repos:List<Reporistory> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return RepoViewHolder(RepositoryItemBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding(repos.get(position))
    }
    fun loadRepositories(repositories: List<Reporistory>) {
        this.repos = repositories
        notifyDataSetChanged()
    }
    class RepoViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val context:Context=binding.root.context

        fun binding(repo:Reporistory){
            binding.setVariable(BR.repo,repo)
            binding.setVariable(BR.pushedDate, PrettyTime().format(repo.pushed_at))
            itemView.repositoryItemImage.loadUrl(repo.owner.avatar_url)
            itemView.repositoryItemRootLayout.setOnClickListener {
                val intent=Intent(context, RepositoryDetailActivity::class.java)
                intent.putExtra("repo",repo)
                context.startActivity(intent)
            }

        }
    }
}


