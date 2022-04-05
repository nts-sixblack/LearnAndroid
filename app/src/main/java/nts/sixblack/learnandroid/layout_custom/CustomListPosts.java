package nts.sixblack.learnandroid.layout_custom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import nts.sixblack.learnandroid.R;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.model.Posts;

import java.util.List;

public class CustomListPosts extends RecyclerView.Adapter<CustomListPosts.PostsHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Posts> list;

    public CustomListPosts(Context context, List<Posts> list){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public CustomListPosts.PostsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_posts_item, parent, false);

        return new PostsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomListPosts.PostsHolder holder, int position) {
        Posts posts = list.get(position);
        holder.user.setText(posts.getPostsUser().toString());
        holder.caption.setText(posts.getCaption().toString());

        ImageView imageView = holder.imageView;
        Glide.with(context)
                .load(posts.getPostsImage())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PostsHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public ImageView imageView;
        public TextView caption;
        public TextView user;

        public PostsHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutItemPosts);
            imageView = itemView.findViewById(R.id.imgPosts);
            caption = itemView.findViewById(R.id.txtCaption);
            user = itemView.findViewById(R.id.txtPostsUser);
        }
    }



}
