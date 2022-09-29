import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.data.models.Post
import com.example.postsapp.data.models.User
import com.example.postsapp.databinding.PostItemBinding

class PostListAdapter :
    RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder(
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private val posts = ArrayList<Post>()
    private val users = mutableMapOf<Int, User>()

    fun setPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun addUser(user: User) {
        this.users[user.id] = user
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val post = posts[position]
        holder.postTitle.text = post.title
        holder.authorName.text = users[post.userId]?.name ?: "Loading..."
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class PostListViewHolder(itemView: PostItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var postTitle: TextView
        var authorName: TextView

        init {
            postTitle = itemView.postTitle
            authorName = itemView.authorName
        }
    }
}