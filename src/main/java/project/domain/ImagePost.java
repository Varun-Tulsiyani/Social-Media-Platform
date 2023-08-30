package project.domain;

public class ImagePost extends Post {
    static private String imageTitle;
    static private String imageDescription;
    private static int count;

    public ImagePost() {
        super();
    }

    public ImagePost(String username, PostType postType, String postName, String imageTitle,
            String imageDescription) {
        super(username, postType, postName);
        ImagePost.imageTitle = imageTitle;
        ImagePost.imageDescription = imageDescription;
        count++;
    }

    public void setImageTitle(String imageTitle) {
        ImagePost.imageTitle = imageTitle;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageDescription(String imageDescription) {
        ImagePost.imageDescription = imageDescription;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public static int ImagePostCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Username: " + super.getUsername() + ", Post Type: " + super.getPostType() + ", Post Name: "
                + super.getPostName() + ", Image Title: " + imageTitle + ", Image Description: " +
                imageDescription + super.toString();
    }
}