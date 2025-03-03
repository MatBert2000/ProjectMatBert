package com.example.anybreak;

import android.net.Uri;

public class Post {
    private String username;
    private String content;
    private int likeCount;
    private int commentCount;
    private int repostCount;
    private Uri imageUri;

    public Post(String username, String content, int likeCount, int commentCount, int repostCount, Uri imageUri) {
        this.username = username;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.repostCount = repostCount;
        this.imageUri = imageUri;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getRepostCount() {
        return repostCount;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void incrementLike() {
        this.likeCount++;
    }

    public void incrementComment() {
        this.commentCount++;
    }

    public void incrementRepost() {
        this.repostCount++;
    }
}
