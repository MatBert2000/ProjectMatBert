package com.example.anybreak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
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

        // Загрузка изображения
        Glide.with(context).load(post.getImageUri()).into(holder.postImage);

        // Обработчики нажатий для кнопок
        holder.likeButton.setOnClickListener(v -> {
            post.setLikeCount(post.getLikeCount() + 1);
            holder.likeCount.setText(String.valueOf(post.getLikeCount()));
        });

        holder.commentButton.setOnClickListener(v -> {
            post.setCommentCount(post.getCommentCount() + 1);
            holder.commentCount.setText(String.valueOf(post.getCommentCount()));
        });

        holder.repostButton.setOnClickListener(v -> {
            post.setRepostCount(post.getRepostCount() + 1);
            holder.repostCount.setText(String.valueOf(post.getRepostCount()));
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView username, content, likeCount, commentCount, repostCount;
        ImageView postImage;
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
