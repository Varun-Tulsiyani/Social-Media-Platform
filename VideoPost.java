package project;

public class VideoPost extends Post {
    static private String videoTitle;
    static private String videoDescription;
    private static int count;

    public VideoPost() {
        super();
        count++;
    }

    public VideoPost(String username, PostType postType, String postName, String videoTitle, String videoDescription) {
        super(username, postType, postName);
        VideoPost.videoTitle = videoTitle;
        VideoPost.videoDescription = videoDescription;
        count++;
    }

    public void setVideoTitle(String videoTitle) {
        VideoPost.videoTitle = videoTitle;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoDescription(String videoDescription) {
        VideoPost.videoDescription = videoDescription;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public static int VideoPostCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Username: " + super.getUsername() + ", Post Type: " + super.getPostType() + ", Post Name: "
                + super.getPostName() + ", Video Title: " + videoTitle + ", Video Description: " +
                videoDescription + super.toString();
    }

}