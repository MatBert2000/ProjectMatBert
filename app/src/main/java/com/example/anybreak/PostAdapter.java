package com.example.anybreak;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsibbold.zoomage.ZoomageView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private ArrayList<Post> postList;

    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.username.setText(post.getUsername());
        holder.content.setText(post.getContent());
        holder.likeCount.setText(String.valueOf(post.getLikeCount()));
        holder.commentCount.setText(String.valueOf(post.getCommentCount()));
        holder.repostCount.setText(String.valueOf(post.getRepostCount()));

        Uri imageUri = post.getImageUri();
        if (imageUri != null) {
            holder.postImage.setImageURI(imageUri);
        }

        // Лайк
        holder.likeButton.setOnClickListener(v -> {
            post.incrementLike();
            holder.likeCount.setText(String.valueOf(post.getLikeCount()));
        });

        // Комментарий
        holder.commentButton.setOnClickListener(v -> {
            post.incrementComment();
            holder.commentCount.setText(String.valueOf(post.getCommentCount()));
        });

        // Репост
        holder.repostButton.setOnClickListener(v -> {
            post.incrementRepost();
            holder.repostCount.setText(String.valueOf(post.getRepostCount()));
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView username, content, likeCount, commentCount, repostCount;
        ZoomageView postImage;
        ImageButton likeButton, commentButton, repostButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.postContent);
            likeCount = itemView.findViewById(R.id.likeCount);
            commentCount = itemView.findViewById(R.id.commentCount);
            repostCount = itemView.findViewById(R.id.repostCount);
            postImage = itemView.findViewById(R.id.postImage);

            likeButton = itemView.findViewById(R.id.likeButton);
            commentButton = itemView.findViewById(R.id.commentButton);
            repostButton = itemView.findViewById(R.id.repostButton);
        }
    }
}
