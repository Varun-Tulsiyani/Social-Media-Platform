package project;

import java.util.ArrayList;

public interface InterfacePostMgmt {
    void createPost(String username, PostType postType, String postName, String postTitle, String postContent);

    void deletePost(Post post);

    ArrayList<Post> getPost(String username);

    Post PostByPostName(String postName);

    void writeToFile(ArrayList<Post> postList);

    ArrayList<Post> readFromFile();
}