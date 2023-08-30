package project;

public class AudioPost extends Post {
    static private String audioTitle;
    static private String audioDescription;
    private static int count;

    public AudioPost() {
        super();
    }

    public AudioPost(String username, PostType postType, String postName, String audioTitle,
            String audioDescription) {
        super(username, postType, postName);
        AudioPost.audioTitle = audioTitle;
        AudioPost.audioDescription = audioDescription;
        count++;
    }

    public void setAudioTitle(String audioTitle) {
        AudioPost.audioTitle = audioTitle;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public void setAudioDescription(String audioDescription) {
        AudioPost.audioDescription = audioDescription;
    }

    public String getAudioDescription() {
        return audioDescription;
    }

    public static int AudioPostCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Username: " + super.getUsername() + ", Post Type: " + super.getPostType() + ", Post Name: "
                + super.getPostName() + ", Audio Title: " + audioTitle + ", Audio Description: " +
                audioDescription + super.toString();
    }
}