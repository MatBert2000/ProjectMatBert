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

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(int repostCount) {
        this.repostCount = repostCount;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
