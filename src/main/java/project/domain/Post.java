package project.domain;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

enum PostType {
    TEXT,
    AUDIO,
    IMAGE,
    VIDEO
}

public class Post implements Serializable {
    private String username;
    private PostType postType;
    private String postName;
    private String timestamp;

    public Post() {
        timestamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
    }

    public Post(String username, PostType postType, String postName) {
        this.username = username;
        this.postType = postType;
        this.postName = postName;
        timestamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
    }

    public void setUsername(User username) {
        this.username = username.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostName() {
        return postName;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return ", Timestamp: " + timestamp;
    }
}