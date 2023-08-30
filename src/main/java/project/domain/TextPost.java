package project.domain;

public class TextPost extends Post {
    static private String postTitle;
    static private String postContent;
    private static int count;

    public TextPost() {
        super();
    }

    public TextPost(String username, PostType postType, String postName, String postTitle,
            String postContent) {
        super(username, postType, postName);
        TextPost.postTitle = postTitle;
        TextPost.postContent = postContent;
        count++;
    }

    public void setPostTitle(String postTitle) {
        TextPost.postTitle = postTitle;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostContent(String postContent) {
        TextPost.postContent = postContent;
    }

    public String getPostContent() {
        return postContent;
    }

    public static int TextPostCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Username: " + super.getUsername() + ", Post Type: " + super.getPostType() + ", Post Name: "
                + super.getPostName() + ", Text Title: " + postTitle + ", Post Content: " +
                postContent + super.toString();
    }
}